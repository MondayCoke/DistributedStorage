package com.gupao.lottery.lotteryredis.controller;


import com.gupao.lottery.lotteryredis.constants.RedisKeyManager;
import com.gupao.lottery.lotteryredis.constants.ReturnCodeEnum;
import com.gupao.lottery.lotteryredis.controller.vo.LotteryItemVo;
import com.gupao.lottery.lotteryredis.converter.LotteryConverter;
import com.gupao.lottery.lotteryredis.exception.RewardException;
import com.gupao.lottery.lotteryredis.service.ILotteryService;
import com.gupao.lottery.lotteryredis.service.dto.DoDrawDto;
import com.gupao.lottery.lotteryredis.service.dto.ResultResp;
import com.gupao.lottery.lotteryredis.utils.CusAccessObjectUtil;
import com.gupao.lottery.lotteryredis.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mic
 * @since 2021-07-01
 */
@Slf4j
@RestController
@RequestMapping("/lottery")
public class LotteryController {
    @Autowired
    ILotteryService lotteryService;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Autowired
    LotteryConverter lotteryConverter;

    @GetMapping("/{id}")
    public ResultResp<LotteryItemVo> doDraw(@PathVariable("id")Integer id,HttpServletRequest request){
        String accountIp= CusAccessObjectUtil.getIpAddress(request);
        log.info("begin LotteryController.doDraw,access user {}, lotteryId,{}:",accountIp,id);
        ResultResp<LotteryItemVo> resultResp=new ResultResp<>();
        try {
            checkDrawParams(id, accountIp);
            DoDrawDto dto=new DoDrawDto();
            dto.setAccountIp(accountIp);
            dto.setLotteryId(id);
            lotteryService.doDraw(dto);
            resultResp.setCode(ReturnCodeEnum.SUCCESS.getCode());
            resultResp.setMsg(ReturnCodeEnum.SUCCESS.getMsg());
            resultResp.setResult(lotteryConverter.dto2LotteryItemVo(dto));
        }catch (Exception e){
            return ExceptionUtil.handlerException4biz(resultResp,e);
        }finally {
            //清除占位标记
            redisTemplate.delete(RedisKeyManager.getDrawingRedisKey(accountIp));
        }
        return resultResp;
    }
    private void checkDrawParams(Integer id,String accountIp){
        if(null==id){
            throw new RewardException(ReturnCodeEnum.REQUEST_PARAM_NOT_VALID.getCode(),ReturnCodeEnum.REQUEST_PARAM_NOT_VALID.getMsg());
        }
        //采用setNx命令，判断当前用户上一次抽奖是否结束
        Boolean result=redisTemplate.opsForValue().setIfAbsent(RedisKeyManager.getDrawingRedisKey(accountIp),"1", 60,TimeUnit.SECONDS);
        //如果为false，说明上一次抽奖还未结束
        if(!result){
            throw new RewardException(ReturnCodeEnum.LOTTER_DRAWING.getCode(),ReturnCodeEnum.LOTTER_DRAWING.getMsg());
        }
    }
}
