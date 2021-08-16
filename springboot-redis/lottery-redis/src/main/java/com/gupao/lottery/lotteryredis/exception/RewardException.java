package com.gupao.lottery.lotteryredis.exception;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * Mic 老师
 * 业务层异常类
 */
public class RewardException extends BaseBusinessException {

    public RewardException() {
        super();
    }

    public RewardException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public RewardException(Throwable arg0) {
        super(arg0);
    }

    public RewardException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public RewardException(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public RewardException(String errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }
}
