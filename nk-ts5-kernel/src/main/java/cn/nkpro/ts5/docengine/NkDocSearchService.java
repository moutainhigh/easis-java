package cn.nkpro.ts5.docengine;

import cn.nkpro.ts5.data.elasticearch.*;
import cn.nkpro.ts5.docengine.model.BpmTaskES;
import cn.nkpro.ts5.docengine.model.es.DocExtES;
import cn.nkpro.ts5.docengine.model.es.DocHES;
import cn.nkpro.ts5.docengine.service.NkDocPermService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilter;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class NkDocSearchService {

    @Autowired@SuppressWarnings("all")
    private SearchEngine searchEngine;
    @Autowired@SuppressWarnings("all")
    protected NkDocPermService docPermService;
    @Autowired@SuppressWarnings("all")
    private NkDocProperties docProperties;


    @SuppressWarnings("all")
    void init() throws IOException {

        searchEngine.createIndices(DocHES.class);
        searchEngine.createIndices(BpmTaskES.class);
        searchEngine.createIndices(DocExtES.class);

        if(docProperties.getIndices()!=null){
            for(Map.Entry<String, Class> e : docProperties.getIndices().entrySet()){
                searchEngine.createIndices(e.getValue(),e.getKey());
            }
        }
    }

    public void dropAndInit() throws IOException {

        searchEngine.deleteIndices(DocHES.class);
        searchEngine.deleteIndices(BpmTaskES.class);
        searchEngine.deleteIndices(DocExtES.class);

        this.init();
    }

    public boolean exists(String docId) throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("docId",docId));
        return searchEngine.exists(DocHES.class,sourceBuilder);
    }

    public ESPageList<JSONObject> queryList(
            String indexName,
            QueryBuilder preQueryBuilder,
            JSONObject params
    ){

        BoolQueryBuilder postQueryBuilder = QueryBuilders.boolQuery();

        if(preQueryBuilder!=null){
            postQueryBuilder.must(preQueryBuilder);
        }
        // 功能前置条件
        if(params.containsKey("postCondition")) {
            postQueryBuilder.must(
                new LimitQueryBuilder(params.getJSONObject("postCondition"))
                //QueryBuilders.wrapperQuery()
            );
        }

        QueryBuilder permQuery = docPermService.buildDocFilter(NkDocPermService.MODE_READ, null, null, false);
        if(permQuery!=null)
            postQueryBuilder.must(permQuery);

        // 构造检索语句
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .from(params.getInteger("from"))
                .size(params.getInteger("rows"));
        // 过滤权限
        if(!postQueryBuilder.must().isEmpty())
            sourceBuilder.postFilter(postQueryBuilder);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if(params.containsKey("conditions")){
            JSONObject filter = params.getJSONObject("conditions");
            if(filter!=null){
                filter.forEach((k,v)-> boolQueryBuilder.must(new LimitQueryBuilder(filter.getJSONObject(k))));
            }
        }

        if(!boolQueryBuilder.must().isEmpty())
            sourceBuilder.query(boolQueryBuilder);

        // 高亮
        JSONArray highlightField = params.getJSONArray("_highlight");
        if(highlightField!=null && !highlightField.isEmpty()){
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.preTags("<span class='highlight'>");
            highlightBuilder.postTags("</span>");
            for(Object field : highlightField){
                highlightBuilder.field(new HighlightBuilder.Field((String) field));
            }
            sourceBuilder.highlighter(highlightBuilder);
        }

        // 排序
        if(StringUtils.isNotBlank(params.getString("orderField"))){
            sourceBuilder.sort(SortBuilders
                .fieldSort(params.getString("orderField"))
                .order(SortOrder.fromString(StringUtils.defaultIfBlank(params.getString("order"),"desc")))
            );
            sourceBuilder.sort(SortBuilders.scoreSort());
        }else{
            sourceBuilder.sort(SortBuilders.scoreSort());
            sourceBuilder.sort(SortBuilders.fieldSort("updatedTime").order(SortOrder.DESC));
        }

        // 过滤字段
        if(params.containsKey("_source")){
            @SuppressWarnings("all")
            String[] fields = params.getJSONArray("_source").toArray(new String[0]);
            sourceBuilder.fetchSource(fields,null);
        }

        // 汇总数据
        if(params.containsKey("$aggs")) {
            FilterAggregationBuilder $aggs = AggregationBuilders
                    .filter("$aggs", postQueryBuilder);
            params.getJSONArray("$aggs")
                    .forEach(agg ->
                            $aggs.subAggregation(
                                    AggregationBuilders.terms((String) agg)
                                            .field((String) agg)
                                            .order(BucketOrder.key(true))
                                            .size(100)
                            )
                    );
            sourceBuilder.aggregation($aggs);
        }

        return searchPage(indexName,sourceBuilder);
    }

    private ESPageList<JSONObject> searchPage(String indexName, SearchSourceBuilder builder) {

        SearchResponse response = searchEngine.search(indexName, builder);

        List<JSONObject> collect = Arrays.stream(response.getHits().getHits())
                .map(hit -> {
                    JSONObject jsonObject = new JSONObject(hit.getSourceAsMap());

                    hit.getHighlightFields().forEach((k,v)-> {
                        if(v.getFragments()!=null && v.getFragments().length>0){
                            jsonObject.put(k,v.getFragments()[0].string());
                        }
                    });

                    return jsonObject;
                })
                .collect(Collectors.toList());

        Map<String, ESAgg> aggs = null;
        if(response.getAggregations()!=null){

            Map<String, Aggregation> aggregationMap = response.getAggregations()
                    .asMap();

            ParsedFilter $aggs = (ParsedFilter) aggregationMap.get("$aggs");
            aggs = $aggs.getAggregations()
                    .asList()
                    .stream()
                    .map(aggregation -> {
                        ESAgg agg = new ESAgg();
                        ParsedStringTerms parsedStringTerms = (ParsedStringTerms) aggregation;
                        agg.setName(parsedStringTerms.getName());
                        agg.setBuckets(
                                parsedStringTerms.getBuckets()
                                        .stream()
                                        .map(bucket->{
                                            ESBucket bt = new ESBucket();
                                            bt.setKey(bucket.getKeyAsString());
                                            bt.setDocCount(bucket.getDocCount());
                                            return bt;
                                        })
                                        .collect(Collectors.toList())
                        );
                        return agg;
                    })
                    .collect(Collectors.toMap(ESAgg::getName, Function.identity()));
        }

        return new ESPageList<>(
                collect,
                aggs,
                builder.from(),
                builder.size(),
                response.getHits().getTotalHits().value
        );
    }
}
