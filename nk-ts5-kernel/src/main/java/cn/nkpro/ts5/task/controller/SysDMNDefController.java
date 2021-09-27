package cn.nkpro.ts5.task.controller;

import cn.nkpro.ts5.annotation.NkNote;
import cn.nkpro.ts5.basic.PageList;
import cn.nkpro.ts5.task.NkBpmDefService;
import cn.nkpro.ts5.task.model.BpmDeployment;
import cn.nkpro.ts5.task.model.BpmProcessDefinition;
import cn.nkpro.ts5.task.model.DmnDecisionDefinition;
import cn.nkpro.ts5.utils.BeanUtilz;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bean on 2020/7/17.
 */
@NkNote("36.[DevDef]决策配置")
@RestController
@RequestMapping("/def/dmn")
@PreAuthorize("hasAnyAuthority('*:*','DEF:*','DEF:DMN')")
public class SysDMNDefController {

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private NkBpmDefService defBpmService;

    @NkNote("1.拉取定义")
    @RequestMapping(value = "/definitions")
    public PageList<DmnDecisionDefinition> processDefinitions(
            @NkNote("查询条目")@RequestParam(value = "latest",       required = false, defaultValue = "false") Boolean latest,
            @NkNote("查询条目")@RequestParam(value = "keyword",      required = false) String key,
            @NkNote("查询条目")@RequestParam(value = "orderField",   required = false) String orderField,
            @NkNote("查询条目")@RequestParam(value = "order",        required = false) String order,
            @NkNote("起始条目")@RequestParam("from") Integer from,
            @NkNote("查询条目")@RequestParam("rows") Integer rows){

        DecisionDefinitionQuery query = processEngine.getRepositoryService()
                .createDecisionDefinitionQuery();

        if(latest)
            query.latestVersion();
        if(StringUtils.isNotBlank(key))
            query.decisionRequirementsDefinitionKey(String.format("%%%s%%",key));
        if(StringUtils.isNotBlank(orderField)){
            switch (orderField){
                case "key":
                    query.orderByDecisionDefinitionKey();
                    break;
                case "name":
                    query.orderByDecisionDefinitionName();
                    break;
                case "version":
                    query.orderByDecisionDefinitionVersion();
                    break;
            }
            if("desc".equalsIgnoreCase(order)){
                query.desc();
            }else {
                query.asc();
            }
        }

        return new PageList<>(
                BeanUtilz.copyFromList(query.listPage(from,rows), DmnDecisionDefinition.class),
                from,
                rows,
                query.count());
    }
    @NkNote("2.拉取定义详情")
    @RequestMapping(value = "/definition/detail")
    public BpmProcessDefinition processDefinitionDetail(String definitionId){
        return defBpmService.getDmnDefinition(definitionId);
    }

    @NkNote("3.部署流程定义")
    @RequestMapping(value = "/deploy",method = RequestMethod.POST)
    public BpmDeployment deploy(@RequestBody BpmProcessDefinition definition){
        return defBpmService.deploy(definition);
    }

//    @NkNote("4.拉取部署记录")
//    @RequestMapping(value = "/deployments")
//    public PageList<BpmDeployment> deployments(
//            @NkNote("起始条目")@RequestParam("from") Integer from,
//            @NkNote("查询条目")@RequestParam("rows") Integer rows){
//
//        DeploymentQuery query = processEngine.getRepositoryService()
//                .createDeploymentQuery();
//
//        return new PageList<>(
//                BeanUtilz.copyFromList(query.orderByDeploymentTime().desc()
//                        .orderByDeploymentName().desc()
//                        .listPage(from,rows), BpmDeployment.class),
//                from,
//                rows,
//                query.count());
//    }
}
