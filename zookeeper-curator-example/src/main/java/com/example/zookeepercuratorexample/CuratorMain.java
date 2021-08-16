package com.example.zookeepercuratorexample;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class CuratorMain {

    public static void main(String[] args) {
        /**
         * * 建立连接（session）
         * * CRUD的操作命令
         * * 基于特性提供解决方案层面的封装
         */
        CuratorFramework curatorFramework=CuratorFrameworkFactory
                .builder()
                .connectionTimeoutMs(20000)
                .connectString("192.168.221.128:2181") //读写分离(zookeeper-server)
                // baseSleepTimeMs*Math.max(1,random.nextInt(1<<(maxRetries+1))
                /**
                 * RetryNTimes 指定最大重试次数
                 * RetryOneTimes
                 * RetryUntilElapsed 一直重试，直到达到规定时间
                 */
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .sessionTimeoutMs(15000)
                .build();
                curatorFramework.start(); //启动
        try {
            byte[] data=curatorFramework.getData().forPath("/seq");
            System.out.println(new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
