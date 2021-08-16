package com.gupao.lottery.lotteryredis.exception;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * Mic 老师
 * 业务层异常类
 */
public class UnRewardException extends BaseBusinessException {

    public UnRewardException() {
        super();
    }

    public UnRewardException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public UnRewardException(Throwable arg0) {
        super(arg0);
    }

    public UnRewardException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public UnRewardException(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public UnRewardException(String errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }
}
