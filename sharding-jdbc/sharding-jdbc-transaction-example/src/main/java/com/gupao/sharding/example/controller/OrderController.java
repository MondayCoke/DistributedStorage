package com.gupao.sharding.example.controller;

import com.gupao.sharding.example.dal.model.TOrder;
import com.gupao.sharding.example.service.ITOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ITOrderService orderService;

    @GetMapping
    public void order(){
        log.info("begin OrderController.order");
        orderService.addOrder();
    }
}
