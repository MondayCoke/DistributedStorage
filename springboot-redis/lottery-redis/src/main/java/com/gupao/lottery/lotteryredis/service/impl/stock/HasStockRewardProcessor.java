package com.gupao.lottery.lotteryredis.service.impl.stock;

import com.gupao.lottery.lotteryredis.constants.LotteryConstants;
import com.gupao.lottery.lotteryredis.constants.ReturnCodeEnum;
import com.gupao.lottery.lotteryredis.dal.mapper.LotteryPrizeMapper;
import com.gupao.lottery.lotteryredis.dal.model.LotteryPrize;
import com.gupao.lottery.lotteryredis.exception.UnRewardException;
import com.gupao.lottery.lotteryredis.service.impl.AsyncLotteryRecordTask;
import org.mapstruct.ap.internal.model.assignment.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Service
public class HasStockRewardProcessor extends AbstractRewardProcessor{

    @Autowired
    AsyncLotteryRecordTask asyncLotteryRecordTask;

    @Autowired
    LotteryPrizeMapper lotteryPrizeMapper;

    @Override
    protected void afterProcessor(RewardContext context) {
        asyncLotteryRecordTask.saveLotteryRecord(context.getAccountIp(),context.getLotteryItem(),context.getPrizeName());
    }

    @Override
    protected void processor(RewardContext context) {
        //扣减库存（redis的更新）
        Long result=redisTemplate.opsForHash().increment(context.getKey(),"validStock",-1);
        //当前奖品库存不足，提示未中奖，或者返回一个兜底的奖品
        if(result.intValue()<0){
            throw new UnRewardException(ReturnCodeEnum.LOTTER_REPO_NOT_ENOUGHT.getCode(),ReturnCodeEnum.LOTTER_REPO_NOT_ENOUGHT.getMsg());
        }
        List<Object> propertys= Arrays.asList("id","prizeName");
        List<Object> prizes=redisTemplate.opsForHash().multiGet(context.getKey(),propertys);
        context.setPrizeId(Integer.parseInt(prizes.get(0).toString()));
        context.setPrizeName(prizes.get(1).toString());
        //更新库存（数据库的更新）
        lotteryPrizeMapper.updateValidStock(context.getPrizeId());
    }

    @Override
    protected int getAwardType() {
        return LotteryConstants.PrizeTypeEnum.NORMAL.getValue();
    }
}
