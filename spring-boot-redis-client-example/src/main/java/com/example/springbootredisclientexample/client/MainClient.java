package com.example.springbootredisclientexample.client;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class MainClient {
    public static void main(String[] args) {
        CustomerRedisClient customerRedisClient=new CustomerRedisClient("192.168.221.128",6379);

        System.out.println(customerRedisClient.set("customer","Define"));
        System.out.println(customerRedisClient.get("customer"));
    }
}
