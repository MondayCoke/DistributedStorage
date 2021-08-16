package com.example.springbootredisclientexample.client;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class CustomerRedisClient {

    private CustomerRedisClientSocket customerRedisClientSocket;

    public CustomerRedisClient(String host,int port) {
        customerRedisClientSocket=new CustomerRedisClientSocket(host,port);
    }

    public String set(String key,String value){
        customerRedisClientSocket.send(convertToCommand(CommandConstant.CommandEnum.SET,key.getBytes(),value.getBytes()));
        return customerRedisClientSocket.read(); //在等待返回结果的时候，是阻塞的
    }

    public String get(String key){
        customerRedisClientSocket.send(convertToCommand(CommandConstant.CommandEnum.GET,key.getBytes()));
        return customerRedisClientSocket.read();
    }

    public static String convertToCommand(CommandConstant.CommandEnum commandEnum,byte[]... bytes){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(CommandConstant.START).append(bytes.length+1).append(CommandConstant.LINE);
        stringBuilder.append(CommandConstant.LENGTH).append(commandEnum.toString().length()).append(CommandConstant.LINE);
        stringBuilder.append(commandEnum.toString()).append(CommandConstant.LINE);
        for (byte[] by:bytes){
            stringBuilder.append(CommandConstant.LENGTH).append(by.length).append(CommandConstant.LINE);
            stringBuilder.append(new String(by)).append(CommandConstant.LINE);
        }
        return stringBuilder.toString();
    }
}
