package com.gupao.lottery.lotteryredis.service.impl.stock;

import com.gupao.lottery.lotteryredis.constants.LotteryConstants;
import com.gupao.lottery.lotteryredis.service.impl.AsyncLotteryRecordTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Service
public class NoneStockRewardProcessor extends AbstractRewardProcessor{
    @Autowired
    AsyncLotteryRecordTask asyncLotteryRecordTask;

    @Override
    protected void afterProcessor(RewardContext context) {
        asyncLotteryRecordTask.saveLotteryRecord(context.getAccountIp(),context.getLotteryItem(),context.getPrizeName());
    }

    @Override
    protected void processor(RewardContext context) {
        List<Object> propertys= Arrays.asList("id","prizeName");
        List<Object> prizes=redisTemplate.opsForHash().multiGet(context.getKey(),propertys);
        context.setPrizeId(Integer.parseInt(prizes.get(0).toString()));
        context.setPrizeName(prizes.get(1).toString());
    }

    @Override
    protected int getAwardType() {
        return LotteryConstants.PrizeTypeEnum.THANK.getValue();
    }
}
