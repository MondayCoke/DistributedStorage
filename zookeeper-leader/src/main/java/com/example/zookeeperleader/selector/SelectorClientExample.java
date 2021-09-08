package com.example.zookeeperleader.selector;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import sun.font.EAttribute;

import java.io.Closeable;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class SelectorClientExample extends LeaderSelectorListenerAdapter implements Closeable {


    private final String name;
    private final LeaderSelector leaderSelector;

    public SelectorClientExample(String path,String name) {
        leaderSelector=new LeaderSelector(getClient(),path,this);
        leaderSelector.autoRequeue();
        this.name=name;
    }

    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }

    public void start(){
        leaderSelector.start();
    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
        System.out.println(name+" 成为Leader");
        Thread.sleep(1000);
    }

    private CuratorFramework getClient(){
        CuratorFramework curatorFramework= CuratorFrameworkFactory
                .builder()
                .connectString("192.168.221.128:2181")
                .sessionTimeoutMs(15000)
                .connectionTimeoutMs(20000)
                .retryPolicy(new ExponentialBackoffRetry(1000,10))
                .build();
        curatorFramework.start();
        return curatorFramework;
    }

    public static void main(String[] args) throws IOException {
        String path="/leader";
        for (int i = 0; i < 10; i++) {
            SelectorClientExample selectorClientExample=
                    new SelectorClientExample(path,"Client:"+i);
            selectorClientExample.start();
        }
        System.in.read();
    }
}
