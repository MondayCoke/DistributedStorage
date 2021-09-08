package com.example.zookeeperleader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZookeeperLeaderApplication {

   /* @Value("${job-mechine}")
    private boolean jobMechine;*/


    public static void main(String[] args) {
        SpringApplication.run(ZookeeperLeaderApplication.class, args);
    }

}
