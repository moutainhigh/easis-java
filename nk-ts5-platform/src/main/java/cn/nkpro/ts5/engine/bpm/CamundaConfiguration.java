package cn.nkpro.ts5.engine.bpm;

import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.camunda.bpm.spring.boot.starter.util.SpringBootProcessEnginePlugin;
import org.springframework.stereotype.Component;

@Component
public class CamundaConfiguration extends SpringBootProcessEnginePlugin {

    @Override
    public void preInit(SpringProcessEngineConfiguration processEngineConfiguration) {
        super.preInit(processEngineConfiguration);
    }

    @Override
    public void postInit(SpringProcessEngineConfiguration processEngineConfiguration) {
        super.postInit(processEngineConfiguration);
    }

    @Override
    public void postProcessEngineBuild(ProcessEngineImpl processEngine) {
        super.postProcessEngineBuild(processEngine);
    }
}
