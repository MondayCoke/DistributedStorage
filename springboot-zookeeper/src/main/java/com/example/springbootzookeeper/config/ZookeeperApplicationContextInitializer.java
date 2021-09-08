package com.example.springbootzookeeper.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 * ApplicationContextInitializer:
 * ConfigurableApplicationContext 初始化容器之前，会执行回调的一个扩展方法。
 **/
public class ZookeeperApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    
    private final List<PropertySourceLocator> propertySourceLocators;
    
    public ZookeeperApplicationContextInitializer() {
        ClassLoader classLoader= ClassUtils.getDefaultClassLoader();
        //加载所有的PropertySourceLocator的扩展实现（SPI）
        //ZookeeperPropertySourceLocator
        propertySourceLocators=new ArrayList<>(SpringFactoriesLoader
                .loadFactories(PropertySourceLocator.class,classLoader));
        System.out.println("====");
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //去动态加载扩展的配置到Environment中
        ConfigurableEnvironment environment=applicationContext.getEnvironment();
        MutablePropertySources mutablePropertySources=environment.getPropertySources();
        for(PropertySourceLocator locator:this.propertySourceLocators){
           Collection<PropertySource<?>> sources=locator.locateCollection(environment,applicationContext);
           if(sources==null||sources.size()==0){
               continue;
           }
           for (PropertySource<?> p:sources){
               mutablePropertySources.addLast(p); //把属性源添加到Environment
           }
        }
    }
}
