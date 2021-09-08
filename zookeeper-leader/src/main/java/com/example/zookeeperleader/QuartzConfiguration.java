package com.example.zookeeperleader;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Configuration
public class QuartzConfiguration {


    @Bean
    public ZkSchedulerFactoryBean schedulerFactoryBean(JobDetail jobDetail,Trigger trigger) throws Exception {
        ZkSchedulerFactoryBean zkSchedulerFactoryBean=new ZkSchedulerFactoryBean();
        zkSchedulerFactoryBean.setJobDetails(jobDetail);
        zkSchedulerFactoryBean.setTriggers(trigger);
        return zkSchedulerFactoryBean;
    }

    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(QuartzJob.class).storeDurably().build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail){
        SimpleScheduleBuilder simpleScheduleBuilder=
                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever();
        return TriggerBuilder.newTrigger().forJob(jobDetail).withSchedule(simpleScheduleBuilder).build();
    }
}
