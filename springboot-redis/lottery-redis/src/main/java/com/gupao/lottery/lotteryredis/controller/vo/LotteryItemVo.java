package com.gupao.lottery.lotteryredis.controller.vo;

import lombok.Data;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Data
public class LotteryItemVo {
    private Integer lotteryId;
    //中奖用户ip
    private String accountIp;
    //奖品名称
    private String prizeName;
    //中奖登记
    private Integer level;
    //奖品id
    private Integer prizeId;
}
