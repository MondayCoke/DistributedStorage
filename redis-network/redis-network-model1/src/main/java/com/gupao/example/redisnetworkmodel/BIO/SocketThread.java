package com.gupao.example.redisnetworkmodel.BIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class SocketThread implements Runnable{

    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //inputstream是阻塞的(***)
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //表示获取客户端的请求报文
            String clientStr = bufferedReader.readLine();
            System.out.println("收到客户端发送的消息：" + clientStr);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("receive a message:" + clientStr + "\n");
            bufferedWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //TODO 关闭IO流
        }
    }
}
