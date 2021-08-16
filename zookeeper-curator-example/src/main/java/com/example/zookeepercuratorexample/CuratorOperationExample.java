package com.example.zookeepercuratorexample;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class CuratorOperationExample {

    private final CuratorFramework curatorFramework;

    public CuratorOperationExample(){
        curatorFramework= CuratorFrameworkFactory
                .builder()
                .connectionTimeoutMs(20000)
                .connectString("192.168.221.128:2181") //读写分离(zookeeper-server)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .sessionTimeoutMs(15000)
                .build();
        curatorFramework.start(); //启动
    }

    public void nodeCRUD() throws Exception {
        System.out.println("开始针对节点的CRUD操作");
        String value="Hello World";
        String node=curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/node",value.getBytes());
        System.out.println("节点创建成功："+node);
        Stat stat=new Stat(); //存储状态信息的对象
        //获取节点的value
        byte[] data=curatorFramework.getData().storingStatIn(stat).forPath(node);
        System.out.println("节点value值："+new String(data));
        stat=curatorFramework.setData()
                .withVersion(stat.getVersion())
                .forPath(node,"Update Date Result".getBytes());
        String result=new String(curatorFramework.getData().forPath(node));
        System.out.println("修改节点之后的数据："+result);
        System.out.println("开始删除节点");
        curatorFramework.delete().forPath(node);
        Stat existStat=curatorFramework.checkExists().forPath(node);
        if(existStat==null){
            System.out.println("节点删除成功");
        }
    }

    public void asyncCRUD() throws Exception {
        CountDownLatch countDownLatch=new CountDownLatch(1);
        String node=curatorFramework.create().withMode(CreateMode.PERSISTENT)
                .inBackground((session,event)->{
            System.out.println(Thread.currentThread().getName()+":执行创建节点："+event.getPath());
            countDownLatch.countDown(); //触发回调，递减计数器
        }).forPath("/async-node");
        countDownLatch.await();
        System.out.println("异步执行创建节点："+node);
    }

    public static void main(String[] args) throws Exception {
        CuratorOperationExample curatorOperationExample=new CuratorOperationExample();
        curatorOperationExample.asyncCRUD();
    }
}
