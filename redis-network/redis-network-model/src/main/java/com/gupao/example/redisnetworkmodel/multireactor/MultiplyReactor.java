package com.gupao.example.redisnetworkmodel.multireactor;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class MultiplyReactor {

    private int port;

    private Reactor mainReactor; //main Reactor

    Executor mainReactorExecutor= Executors.newFixedThreadPool(10);

    public MultiplyReactor(int port) throws IOException {
        this.port = port;
        mainReactor=new Reactor();
    }

    public void start() throws IOException {
        new Acceptor(mainReactor.getSelector(),port);
        mainReactorExecutor.execute(mainReactor);
    }

    public static void main(String[] args) throws IOException {
        new MultiplyReactor(8080).start();
    }
}
