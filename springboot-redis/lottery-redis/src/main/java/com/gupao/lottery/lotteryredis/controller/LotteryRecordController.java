package com.gupao.lottery.lotteryredis.controller;


import com.gupao.lottery.lotteryredis.constants.ReturnCodeEnum;
import com.gupao.lottery.lotteryredis.dal.model.LotteryRecord;
import com.gupao.lottery.lotteryredis.service.ILotteryRecordService;
import com.gupao.lottery.lotteryredis.service.dto.ResultResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mic
 * @since 2021-07-01
 */
@RestController
@RequestMapping("/lottery-record")
public class LotteryRecordController {

    @Autowired
    ILotteryRecordService lotteryRecordService;

    @GetMapping
    public ResultResp<List<LotteryRecord>> records(){
        List<LotteryRecord> records=lotteryRecordService.list();
        ResultResp resultResp=new ResultResp();
        resultResp.setMsg(ReturnCodeEnum.SUCCESS.getMsg());
        resultResp.setCode(ReturnCodeEnum.SUCCESS.getCode());
        resultResp.setResult(records);
        return resultResp;
    }
}
