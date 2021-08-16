package com.gupao.sharding.example.controller;

import com.gupao.sharding.example.dal.model.TOrder;
import com.gupao.sharding.example.service.ITOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ITOrderService orderService;

    @GetMapping("/{id}")
    public TOrder order(@PathVariable("id")Long id){
        TOrder order=orderService.getById(id);
        return order;
    }
    @PostMapping
    public void order(@RequestBody TOrder order){
        orderService.save(order);
    }
}
