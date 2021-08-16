package com.gupao.lottery.lotteryredis.service.impl;

import com.gupao.lottery.lotteryredis.dal.mapper.LotteryRecordMapper;
import com.gupao.lottery.lotteryredis.dal.model.LotteryItem;
import com.gupao.lottery.lotteryredis.dal.model.LotteryRecord;
import com.gupao.lottery.lotteryredis.service.dto.DoDrawDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Slf4j
@Component
public class AsyncLotteryRecordTask {

    @Autowired
    LotteryRecordMapper lotteryRecordMapper;

    @Async("lotteryServiceExecutor")
    public void saveLotteryRecord(String accountIp, LotteryItem lotteryItem, String prizeName){
        log.info(Thread.currentThread().getName()+"---saveLotteryRecord");
        //存储中奖信息
        LotteryRecord record=new LotteryRecord();
        record.setAccountIp(accountIp);
        record.setItemId(lotteryItem.getId());
        record.setPrizeName(prizeName);
        record.setCreateTime(LocalDateTime.now());
        lotteryRecordMapper.insert(record);
    }
}
