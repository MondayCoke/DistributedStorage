package com.example.zookeepercuratorexample;

import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ZookeeperWatcherListener implements CuratorCacheListener {

    @Override
    public void event(Type type, ChildData oldData, ChildData data) {
        System.out.println("事件类型："+type+":oldData:"+oldData+":data"+data);
    }
}
