package com.gupao.lottery.lotteryredis.service.impl;

import com.gupao.lottery.lotteryredis.dal.model.LotteryItem;
import com.gupao.lottery.lotteryredis.dal.mapper.LotteryItemMapper;
import com.gupao.lottery.lotteryredis.service.ILotteryItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mic
 * @since 2021-07-01
 */
@Service
public class LotteryItemServiceImpl extends ServiceImpl<LotteryItemMapper, LotteryItem> implements ILotteryItemService {

}
