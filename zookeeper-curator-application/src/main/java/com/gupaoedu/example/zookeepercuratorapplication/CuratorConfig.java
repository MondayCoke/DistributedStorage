package com.gupaoedu.example.zookeepercuratorapplication;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/

@Configuration
public class CuratorConfig {

    @Bean
    public CuratorFramework curatorFramework(){
        CuratorFramework curatorFramework=CuratorFrameworkFactory
                .builder()
                .connectString("47.107.45.58:2181")
                .sessionTimeoutMs(15000)
                .connectionTimeoutMs(20000)
                .retryPolicy(new ExponentialBackoffRetry(1000,10))
                .build();
        curatorFramework.start();
        return curatorFramework;
    }
}
