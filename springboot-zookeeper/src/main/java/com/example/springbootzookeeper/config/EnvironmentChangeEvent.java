package com.example.springbootzookeeper.config;

import org.springframework.context.ApplicationEvent;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class EnvironmentChangeEvent extends ApplicationEvent {

    public EnvironmentChangeEvent(Object source) {
        super(source);
    }
}
