package com.example.springbootredisclientexample.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class CustomerRedisClientSocket {

    private Socket socket;

    private InputStream inputStream;

    private OutputStream outputStream;

    public CustomerRedisClientSocket(String ip,int port){
        try {
            socket=new Socket(ip,port);
            inputStream=socket.getInputStream();
            outputStream=socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String cmd){
        try {
            outputStream.write(cmd.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String read(){
        byte[] bytes=new byte[1024];
        int count=0;
        try {
            count=inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes,0,count);
    }
}
