package com.gupao.lottery.lotteryredis.service.impl.stock;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public interface RewardProcessor<T> {

    void doReward(RewardContext context);
}
