package com.example.zookeepercuratorexample;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;

import java.awt.geom.CubicCurve2D;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ZookeeperWatchExample {

    /*
     * zookeeper 3.6之后，使用下面这个api实现watcher机制
     * CuratorCache
     */
    private final CuratorFramework curatorFramework;

    public ZookeeperWatchExample(){
        curatorFramework= CuratorFrameworkFactory
                .builder()
                .connectionTimeoutMs(20000)
                .connectString("192.168.221.128:2181") //读写分离(zookeeper-server)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .authorization("digest","mic:mic".getBytes())
                .sessionTimeoutMs(20000)
                .build();
        curatorFramework.start(); //启动
    }

    public void normalWatcher() throws Exception {
        CuratorWatcher curatorWatcher=new CuratorWatcher() {
            @Override
            public void process(WatchedEvent watchedEvent) throws Exception {
                System.out.println("监听到的事件"+watchedEvent.toString());
                //循环设置监听
                curatorFramework.checkExists().usingWatcher(this).forPath(watchedEvent.getPath());
            }
        };
        String node=curatorFramework.create().forPath("/watcher","Watcher String".getBytes());
        System.out.println("节点创建成功："+node);
        //设置一次普通的watcher监听
        String data=new String(curatorFramework.getData().usingWatcher(curatorWatcher).forPath(node));
        System.out.println("设置监听并获取节点数据："+data);
        //第一次操作
        curatorFramework.setData().forPath(node,"change data 0".getBytes());
        Thread.sleep(1000);
        curatorFramework.setData().forPath(node,"change data 1".getBytes());
    }

    public void persisWatcher(String node){
        // PathChildCache / NodeCache /TreeCache
        //CuratorCacheListener
        CuratorCache curatorCache=CuratorCache.
                build(curatorFramework,node, CuratorCache.Options.SINGLE_NODE_CACHE);
        CuratorCacheListener listener=CuratorCacheListener
                .builder()
                .forAll(new ZookeeperWatcherListener())
                .build();
        curatorCache.listenable().addListener(listener);
        curatorCache.start();
    }

    private void operation(String node) throws Exception {
        curatorFramework.create().forPath(node);
        curatorFramework.setData().forPath(node,"hello".getBytes());
        curatorFramework.delete().forPath(node);
    }

    public static void main(String[] args) throws Exception {
        ZookeeperWatchExample zookeeperWatchExample=new ZookeeperWatchExample();
//        zookeeperWatchExample.normalWatcher();
        String node="/persis-node";
        zookeeperWatchExample.persisWatcher(node);
        zookeeperWatchExample.operation(node);
        System.in.read();
    }
}
