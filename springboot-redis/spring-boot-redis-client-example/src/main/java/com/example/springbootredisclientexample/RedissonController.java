package com.example.springbootredisclientexample;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@RestController
public class RedissonController {
    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/")
    public String get(){
        return redissonClient.getBucket("test").get().toString();
    }

}
