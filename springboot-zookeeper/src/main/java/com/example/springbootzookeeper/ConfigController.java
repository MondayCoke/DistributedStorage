package com.example.springbootzookeeper;

import com.example.springbootzookeeper.config.RefreshScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@RefreshScope  //表示当前bean中的属性需要动态刷新
@RestController
public class ConfigController {

    @Autowired
    Environment environment;

    //把这些属性放到zookeeper
    @Value("${name}")
    private String name;

    @Value("${job}")
    private String job;

    @Value("${zookeeper}")
    private String command;

    @GetMapping("/env")
    public String env(){
        return environment.getProperty("name")+"\n"+command;
    }
}

