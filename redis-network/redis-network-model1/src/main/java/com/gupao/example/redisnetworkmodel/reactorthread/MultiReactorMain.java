package com.gupao.example.redisnetworkmodel.reactorthread;

import com.gupao.example.redisnetworkmodel.Reactor.Reactor;

import java.io.IOException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class MultiReactorMain {
    public static void main(String[] args) throws IOException {
        new Thread(new MultiReactor(8080),"Main-Thread").start();
    }
}
