package com.example.zookeeperleader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 * FactoryBean
 **/
public class ZkSchedulerFactoryBean extends SchedulerFactoryBean {

    private LeaderLatch leaderLatch;

    private final String LEADER_PATH="/leader"; //namespace

    public ZkSchedulerFactoryBean() throws Exception {
        this.setAutoStartup(false); //应用启动的时候不自动开启定时任务

        leaderLatch=new LeaderLatch(getClient(),LEADER_PATH);
        leaderLatch.addListener(new GpLeaderLatchListener(this)); //当leader发生变化的时候，需要触发监听
        leaderLatch.start();
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

    @Override
    protected void startScheduler(Scheduler scheduler, int startupDelay) throws SchedulerException {

        if(this.isAutoStartup()) {
            super.startScheduler(scheduler, startupDelay);
        }
    }

    @Override
    public void destroy() throws SchedulerException {
        CloseableUtils.closeQuietly(leaderLatch);
        super.destroy();
    }
}
