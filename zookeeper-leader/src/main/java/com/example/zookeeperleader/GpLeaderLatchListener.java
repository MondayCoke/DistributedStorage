package com.example.zookeeperleader;

import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class GpLeaderLatchListener implements LeaderLatchListener {
    //控制定时任务启动和停止的方法
    private SchedulerFactoryBean schedulerFactoryBean;

    public GpLeaderLatchListener(SchedulerFactoryBean schedulerFactoryBean) {
        this.schedulerFactoryBean = schedulerFactoryBean;
    }

    @Override
    public void isLeader() {
        System.out.println(Thread.currentThread().getName()+"成为了leader");
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.start();
    }

    @Override
    public void notLeader() {
        System.out.println(Thread.currentThread().getName()+"抢占leader失败，不执行任务");
        schedulerFactoryBean.setAutoStartup(false);
        schedulerFactoryBean.stop();
    }
}
