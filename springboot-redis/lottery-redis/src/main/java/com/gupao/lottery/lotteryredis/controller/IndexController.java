package com.gupao.lottery.lotteryredis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        //  K  {V: String /HashTable(K,V) /ArrayList/ Set}
        // List<Prize>
        // JSON.toJSON(list);   string
        // HashTable<K,V>
        return "index";
    }
}
