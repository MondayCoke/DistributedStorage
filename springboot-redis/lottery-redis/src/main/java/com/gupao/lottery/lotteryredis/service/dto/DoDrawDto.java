package com.gupao.lottery.lotteryredis.service.dto;

import lombok.Data;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Data
public class DoDrawDto {
    //活动id
    private Integer lotteryId;

    private String accountIp;
    //奖品名称
    private String prizeName;
    //中奖登记
    private Integer level;
    //奖品id
    private Integer prizeId;
}
