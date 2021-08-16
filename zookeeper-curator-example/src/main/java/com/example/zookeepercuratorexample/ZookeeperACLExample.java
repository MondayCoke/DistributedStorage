package com.example.zookeepercuratorexample;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ZookeeperACLExample {

    private final CuratorFramework curatorFramework;

    public ZookeeperACLExample(){
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

    public void aclOperation() throws Exception {
        Id id=new Id("digest", DigestAuthenticationProvider.generateDigest("mic:mic"));
        List<ACL> acls=new ArrayList<>();
        acls.add(new ACL(ZooDefs.Perms.ALL,id));
        String node=curatorFramework.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(acls,false).forPath("/curator-auth","Auth".getBytes());
        System.out.println("创建带有权限节点："+node);
        System.out.println("数据查询结果："+new String(curatorFramework.getData().forPath(node)));
    }

    public static void main(String[] args) throws Exception {
        ZookeeperACLExample curatorOperationExample=new ZookeeperACLExample();
        curatorOperationExample.aclOperation();
    }
}
