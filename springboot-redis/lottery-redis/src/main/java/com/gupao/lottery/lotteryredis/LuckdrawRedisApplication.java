package com.gupao.lottery.lotteryredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gupao.lottery.lotteryredis.dal.mapper")
public class LuckdrawRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckdrawRedisApplication.class, args);
    }
}
