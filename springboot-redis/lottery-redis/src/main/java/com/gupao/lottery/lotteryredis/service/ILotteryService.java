package com.gupao.lottery.lotteryredis.service;

import com.gupao.lottery.lotteryredis.dal.model.Lottery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.lottery.lotteryredis.service.dto.DoDrawDto;
import com.gupao.lottery.lotteryredis.service.dto.ResultResp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mic
 * @since 2021-07-01
 */
public interface ILotteryService extends IService<Lottery> {

    void doDraw(DoDrawDto drawDto) throws Exception;
}
