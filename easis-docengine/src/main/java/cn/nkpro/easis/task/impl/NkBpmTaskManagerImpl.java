package cn.nkpro.easis.task.impl;

import cn.nkpro.easis.basic.PageList;
import cn.nkpro.easis.docengine.model.DocHV;
import cn.nkpro.easis.docengine.service.NkDocEngineFrontService;
import cn.nkpro.easis.task.NkBpmTaskManager;
import cn.nkpro.easis.task.model.BpmInstance;
import cn.nkpro.easis.task.model.BpmTask;
import cn.nkpro.easis.utils.BeanUtilz;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.history.HistoricProcessInstanceQuery;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.impl.pvm.PvmActivity;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Comment;
import org.camunda.bpm.engine.task.IdentityLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NkBpmTaskManagerImpl extends AbstractNkBpmSupport implements NkBpmTaskManager {

    @Autowired
    private NkDocEngineFrontService docEngine;



    @Override
    public PageList<BpmInstance> processInstancePage(Integer from, Integer rows){

        HistoricProcessInstanceQuery query = processEngine.getHistoryService()
                .createHistoricProcessInstanceQuery()
                .orderByProcessInstanceStartTime()
                .desc();

        return new PageList<>(
                BeanUtilz.copyFromList(query.listPage(from, rows),BpmInstance.class),
                from,
                rows,
                query.count());
    }

    @Override
    public BpmInstance processInstanceDetail(String instanceId){

        BpmInstance processInstance = BeanUtilz.copyFromObject(
                processEngine.getHistoryService()
                        .createHistoricProcessInstanceQuery()
                        .processInstanceId(instanceId)
                        .singleResult(),BpmInstance.class);

        Assert.notNull(processInstance, "?????????????????????");

        // ????????????????????????
        List<HistoricVariableInstance> variables = getHistoricVariableInstances(processInstance.getId());

        // ??????????????????
        processInstance.setBpmVariables(
            variables
                .stream()
                .filter(instance->StringUtils.equals(instance.getExecutionId(),processInstance.getId()))
                .collect(Collectors.toMap(HistoricVariableInstance::getName,HistoricVariableInstance::getValue))
        );

        // ?????????????????????????????????
        List<? extends PvmActivity> activities = getProcessDefinitionActivities(processInstance.getProcessDefinitionId());

        // ??????????????????
        List<Comment> comments = processEngine.getTaskService()
                .getProcessInstanceComments(processInstance.getId());

        // ??????????????????????????????
        processInstance.setBpmTask(
            BeanUtilz.copyFromList(
                processEngine.getHistoryService()
                    .createHistoricTaskInstanceQuery()
                    .processInstanceId(instanceId)
                    .orderByHistoricActivityInstanceStartTime().asc()
                    .list(),
                BpmTask.class,
                (task)->{

                    // ?????????????????????
                    task.setComments(
                        comments.stream()
                                .filter(comment -> StringUtils.equals(comment.getTaskId(),task.getId()))
                                .map(Comment::getFullMessage)
                                .collect(Collectors.toList())
                    );

                    // ?????????????????????
                    task.setBpmVariables(
                        variables
                            .stream()
                            .filter(instance->StringUtils.equals(instance.getExecutionId(),task.getExecutionId()))
                            .collect(Collectors.toMap(HistoricVariableInstance::getName,HistoricVariableInstance::getValue))
                    );

                    // ???????????????????????????????????????????????????
                    if(task.getEndTime()==null){

                        // ?????????????????????
                        if(StringUtils.isBlank(task.getAssignee())){
                            task.setCandidate(
                                processEngine.getTaskService().getIdentityLinksForTask(task.getId())
                                    .stream()
                                    .filter(identityLink -> StringUtils.equals(identityLink.getType(),"candidate"))
                                    .map(IdentityLink::getUserId)
                                    .collect(Collectors.toList())
                            );
                        }

                        // ????????????????????????????????????
                        task.setTransitions(getTaskTransition(activities,task.getTaskDefinitionKey()));
                    }
                }
            )
        );

        return processInstance;
    }

    @Override
    @Transactional
    public void deleteProcessInstance(String instanceId, String deleteReason){
        ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery()
                .processInstanceId(instanceId)
                .singleResult();

        if(StringUtils.isNotBlank(processInstance.getBusinessKey())){
            docEngine.onBpmKilled(
                    processInstance.getBusinessKey(),
                    processInstance.getProcessDefinitionId().split(":")[0],
                    deleteReason);
        }

        processEngine.getRuntimeService().setVariable(instanceId,"NK$DELETE",deleteReason);
        processEngine.getRuntimeService().deleteProcessInstance(instanceId,deleteReason);
    }

    @Override
    public Boolean taskExists(String taskId) {
        return processEngine.getTaskService()
                .createTaskQuery()
                .taskId(taskId).count()>0;
    }

    @Override
    public void indexDocTask(DocHV doc){

        processEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .processInstanceBusinessKey(doc.getDocId())
                .list()
                .forEach(historicTaskInstance ->
                        super.indexDocTask(historicTaskInstance, doc)
                );
    }

    @Override
    public void revoke(String instanceId) {
        ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery()
                .processInstanceId(instanceId)
                .singleResult();

//        System.out.println(processInstance.getProcessVariables());
//
//        if(StringUtils.isNotBlank(processInstance.getBusinessKey())){
//            docService.onBpmKilled(processInstance.getBusinessKey());
//        }

        processEngine.getRuntimeService().deleteProcessInstance(instanceId,"????????????");
    }
}
