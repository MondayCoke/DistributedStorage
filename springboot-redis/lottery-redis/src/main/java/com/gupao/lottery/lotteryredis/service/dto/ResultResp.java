package com.gupao.lottery.lotteryredis.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Data
public class ResultResp<T> implements Serializable {

    private static final long serialVersionUID = 8140875256110329513L;

    private String code;
    private String msg;
    private T result;
}
