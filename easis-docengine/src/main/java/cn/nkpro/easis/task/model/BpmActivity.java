package cn.nkpro.easis.task.model;

import lombok.Data;

import java.util.List;

@Data
public class BpmActivity {
    private String taskId;
    private String activityName;
    private String assignee;
    private String user;

    private List<BpmComment> comments;
}
