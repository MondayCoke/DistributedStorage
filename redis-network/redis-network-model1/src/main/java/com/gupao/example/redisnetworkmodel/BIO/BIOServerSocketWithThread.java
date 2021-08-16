package com.gupao.example.redisnetworkmodel.BIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class BIOServerSocketWithThread {

    static ExecutorService executorService= Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        ServerSocket serverSocket=null;
        try {
            serverSocket=new ServerSocket(8080);
            System.out.println("启动服务：监听端口:8080");
            //表示阻塞等待监听一个客户端连接,返回的socket表示连接的客户端信息
            while(true) {
                Socket socket = serverSocket.accept(); //连接阻塞
                System.out.println("客户端：" + socket.getPort());
                //IO变成了异步执行
                executorService.submit(new SocketThread(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
