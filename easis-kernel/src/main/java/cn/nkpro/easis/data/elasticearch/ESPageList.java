package cn.nkpro.easis.data.elasticearch;

import cn.nkpro.easis.annotation.Keep;
import cn.nkpro.easis.basic.PageList;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Keep
public class ESPageList<T> extends PageList<T> {

	@Getter
	private Map<String,ESAgg> aggs;
	@Getter
	private List<Object> suggests;

	public ESPageList(List<T> list, Map<String,ESAgg> aggs, List<Object> suggests, int from, int size, long total) {
		super(list,from,size,total);
		this.aggs = aggs;
		this.suggests = suggests;
	}
}


