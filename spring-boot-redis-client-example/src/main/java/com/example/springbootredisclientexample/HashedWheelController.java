package com.example.springbootredisclientexample;

import io.netty.util.HashedWheelTimer;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@RestController
@RequestMapping("/timer")
public class HashedWheelController {

    //时间轮的定义
    HashedWheelTimer hashedWheelTimer=new HashedWheelTimer(
            new DefaultThreadFactory("demo-timer"),
            100, TimeUnit.MILLISECONDS,1024,false);

    @GetMapping("/{delay}")
    public void tick(@PathVariable("delay")Long delay){
        //SCHEDULED(定时执行的线程）
        //Timer(Java原生定时任务执行）
        //订单关单
        System.out.println("CurrentTime:"+new Date());
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("Begin Execute:"+new Date());
        },delay,TimeUnit.SECONDS);
    }
}
