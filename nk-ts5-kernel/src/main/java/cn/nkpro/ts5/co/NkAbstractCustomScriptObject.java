package cn.nkpro.ts5.co;

import cn.nkpro.ts5.basic.NkProperties;
import cn.nkpro.ts5.dataengine.meter.NkMeter;
import cn.nkpro.ts5.utils.ClassUtils;
import cn.nkpro.ts5.utils.GroovyUtils;
import cn.nkpro.ts5.utils.ResourceUtils;
import cn.nkpro.ts5.annotation.NkNote;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class NkAbstractCustomScriptObject implements NkCustomScriptObject, InitializingBean {

    @Autowired
    private NkProperties properties;

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    @Getter
    protected String beanName;

    @Setter@Getter
    protected PlatformScriptV scriptDef;

    public NkAbstractCustomScriptObject() {
        this.beanName = parseComponentName();
    }

    public final void afterPropertiesSet(){
        if(this.scriptDef==null){
            // 从classpath中加载资源
            this.scriptDef = loadScriptFromClassPath();
        }
        if(this.scriptDef==null){
            scriptDef = new PlatformScriptV();
            scriptDef.setScriptType("Unknown");
            scriptDef.setScriptName(beanName);
            scriptDef.setVersion("@");
            scriptDef.setGroovyMain(null);
            scriptDef.setVueMain(null);
            scriptDef.setVueDefs(null);
            scriptDef.setState("Native");
        }
    }


    protected PlatformScriptV scriptDefHV(){
        if(!scriptDef.isDebug() && properties.isComponentReloadClassPath()){
            PlatformScriptV defHV = loadScriptFromClassPath();
            if(defHV!=null){
                return defHV;
            }
        }
        return this.scriptDef;
    }

    protected PlatformScriptV loadScriptFromClassPath() {

        String className = getClass().getSimpleName();

        List<String> groovyCode = findResource(className + ".groovy");
        if (!groovyCode.isEmpty()) {
            List<String> vueMainCode = findResource(className + ".vue");
            List<String> vueDefsCode = findResource(className + "Def*.vue");
            PlatformScriptV scriptDefH = new PlatformScriptV();
            scriptDefH.setScriptName(beanName);
            scriptDefH.setVersion("@");
            scriptDefH.setGroovyMain(groovyCode.stream().findFirst().orElse(null));
            scriptDefH.setVueMain(vueMainCode.stream().findFirst().orElse(null));
            scriptDefH.setVueDefs(CollectionUtils.isEmpty(vueDefsCode)?null:JSON.toJSONString(vueDefsCode));
            scriptDefH.setState("Active");

            Class<?> groovy = GroovyUtils.compileGroovy(className, scriptDefH.getGroovyMain());
            List interfaces = org.apache.commons.lang3.ClassUtils.getAllInterfaces(groovy);

            if(interfaces.contains(NkScriptCard.class)){
                scriptDefH.setScriptType("Card");
            }else if(interfaces.contains(NkMeter.class)){
                scriptDefH.setScriptType("Meter");
            }else{
                scriptDefH.setScriptType("Service");
            }

            NkNote annotation = groovy.getAnnotation(NkNote.class);
            scriptDefH.setScriptDesc(annotation != null ? annotation.value() : beanName);
            return scriptDefH;
        }
        return null;
    }



    private String parseComponentName(){

        Class<?> clazz = getClass();

        Component component = clazz.getDeclaredAnnotation(Component.class);
        if(component!=null && StringUtils.isNotBlank(component.value()))
            return component.value();

        Service service = clazz.getDeclaredAnnotation(Service.class);
        if(service!=null && StringUtils.isNotBlank(service.value()))
            return service.value();

        return ClassUtils.decapitateClassName(clazz.getSimpleName());
    }

    private List<String> findResource(String resourceName){
        try {
            List<Resource> resources = new ArrayList<>();
            for (String path : properties.getComponentBasePackages()) {
                resources.addAll(Arrays.asList(resourcePatternResolver.getResources(String.format("classpath*:/%s/**/%s", packageToPath(path), resourceName))));
            }
            return resources.stream()
                    .map(ResourceUtils::readText)
                    .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private String packageToPath(String packageName){
        packageName = packageName.replaceAll("[.]","/");
        if(packageName.startsWith("/")){
            packageName = packageName.substring(1);
        }
        if(packageName.endsWith("/")){
            packageName = packageName.substring(0,packageName.length()-1);
        }
        return packageName;
    }
}
