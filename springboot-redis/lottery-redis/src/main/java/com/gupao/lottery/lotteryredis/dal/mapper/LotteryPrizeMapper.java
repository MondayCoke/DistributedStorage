package com.gupao.lottery.lotteryredis.dal.mapper;

import com.gupao.lottery.lotteryredis.dal.model.LotteryPrize;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mic
 * @since 2021-07-01
 */
public interface LotteryPrizeMapper extends BaseMapper<LotteryPrize> {

    @Update("update lottery_prize set valid_stock=valid_stock-1 where valid_stock>=1 and id=#{id}")
    void updateValidStock(@Param("id") Serializable id);
}
