package com.example.springbootzookeeper.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCacheListenerBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class NodeDataChangeCuratorCacheListener implements CuratorCacheListenerBuilder.ChangeListener {

    private Environment environment;
    private ConfigurableApplicationContext applicationContext;

    public NodeDataChangeCuratorCacheListener(Environment environment, ConfigurableApplicationContext applicationContext) {
        this.environment = environment;
        this.applicationContext = applicationContext;
    }

    @Override
    public void event(ChildData oldNode, ChildData node) {
        System.out.println("收到数据变更事件");
        String resultData=new String (node.getData());
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            //吧json格式的数据转换为map
            Map<String,Object> map=objectMapper.readValue(resultData, Map.class);
            //替换掉原来的PropertySource
            ConfigurableEnvironment cfe=(ConfigurableEnvironment)this.environment;
            MapPropertySource mapPropertySource=new MapPropertySource("configService",map);
            cfe.getPropertySources().replace("configService",mapPropertySource);
            //发送一个数据变更事件
            applicationContext.publishEvent(new EnvironmentChangeEvent(this));
            System.out.println("数据更新完成");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
