package cn.nkpro.ts5.engine.bpm.model;

import lombok.Data;

import java.util.Date;

@Data
public class DeploymentV {
    private String id;
    private String name;
    private Date deploymentTime;
    private boolean isNew;
    private String source;
    private String tenantId;
}
