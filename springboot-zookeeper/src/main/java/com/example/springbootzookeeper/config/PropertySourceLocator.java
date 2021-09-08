package com.example.springbootzookeeper.config;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 * 变成一个扩展点
 **/
public interface PropertySourceLocator {

    PropertySource<?> locate(Environment environment, ConfigurableApplicationContext applicationContext);

    default Collection<PropertySource<?>> locateCollection(Environment environment, ConfigurableApplicationContext applicationContext){
        return locateCollections(this,environment,applicationContext);
    }

    //收集属性源列表
    static Collection<PropertySource<?>> locateCollections(PropertySourceLocator locator,Environment environment, ConfigurableApplicationContext applicationContext) {
        PropertySource<?> propertySource=locator.locate(environment,applicationContext);
        if(propertySource==null){
            return Collections.emptyList();
        }
        return Arrays.asList(propertySource);
    }

}
