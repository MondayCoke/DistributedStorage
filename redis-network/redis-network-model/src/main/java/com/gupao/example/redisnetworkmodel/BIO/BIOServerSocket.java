package com.gupao.example.redisnetworkmodel.BIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class BIOServerSocket {

    public static void main(String[] args) {
        ServerSocket serverSocket=null;

        try {
            serverSocket=new ServerSocket(8080);
            System.out.println("启动服务：监听端口:8080");
            //表示阻塞等待监听一个客户端连接,返回的socket表示连接的客户端信息
            while(true) {//case1: 增加循环，允许循环接收请求
                Socket socket = serverSocket.accept(); //连接阻塞
                System.out.println("客户端：" + socket.getPort()+ "已连接");
                //inputstream是阻塞的(***)
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //表示获取客户端的请求报文
                String clientStr = bufferedReader.readLine();//读取一行信息
                System.out.println("收到客户端发送的消息：" + clientStr);
                Thread.sleep(20000); //case2: 修改：增加等待时间
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bufferedWriter.write("receive a message:" + clientStr + "\n");
                bufferedWriter.flush();
            }
        } catch (IOException | InterruptedException e) {
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
