package com.gupao.lottery.lotteryredis.events;

import com.gupao.lottery.lotteryredis.dal.model.LotteryItem;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class InitPrizeToRedisEvent extends ApplicationEvent {
    private Integer lotteryId;
    private CountDownLatch countDownLatch;
    public InitPrizeToRedisEvent(Object source, Integer lotteryId, CountDownLatch countDownLatch) {
        super(source);
        this.lotteryId=lotteryId;
        this.countDownLatch=countDownLatch;
    }

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

}
