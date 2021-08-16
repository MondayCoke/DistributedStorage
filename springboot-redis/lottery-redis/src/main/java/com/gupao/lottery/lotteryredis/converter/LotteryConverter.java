package com.gupao.lottery.lotteryredis.converter;


import com.gupao.lottery.lotteryredis.controller.vo.LotteryItemVo;
import com.gupao.lottery.lotteryredis.service.dto.DoDrawDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Mapper(componentModel = "spring")
public interface LotteryConverter {


    LotteryItemVo dto2LotteryItemVo(DoDrawDto drawDto);

}
