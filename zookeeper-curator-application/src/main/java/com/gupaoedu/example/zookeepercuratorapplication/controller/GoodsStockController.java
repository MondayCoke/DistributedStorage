package com.gupaoedu.example.zookeepercuratorapplication.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gupaoedu.example.zookeepercuratorapplication.dal.model.GoodsStock;
import com.gupaoedu.example.zookeepercuratorapplication.service.IGoodsStockService;
import com.gupaoedu.example.zookeepercuratorapplication.service.impl.GoodsStockServiceImpl;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.Revoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mic
 * @since 2021-08-09
 */

@Scope(scopeName = "prototype")
@RestController
@RequestMapping("/goods-stock")
public class GoodsStockController {


    @Autowired
    IGoodsStockService goodsStockService;

    @Autowired
    CuratorFramework curatorFramework;

    @GetMapping("{goodsNo}")
    public String  purchase(@PathVariable("goodsNo")Integer goodsNo) throws Exception {
        QueryWrapper<GoodsStock> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("goods_no",goodsNo);
        //基于临时有序节点来实现的分布式锁.
        InterProcessMutex lock=new InterProcessMutex(curatorFramework,"/Locks");
        try {
            lock.acquire(); //抢占分布式锁资源（阻塞的）
            GoodsStock goodsStock = goodsStockService.getOne(queryWrapper);
            Thread.sleep(new Random().nextInt(1000));
            if (goodsStock == null) {
                return "指定商品不存在";
            }
            if (goodsStock.getStock().intValue() < 1) {
                return "库存不够";
            }
            goodsStock.setStock(goodsStock.getStock() - 1);
            boolean res = goodsStockService.updateById(goodsStock);
            if (res) {
                return "抢购书籍：" + goodsNo + "成功";
            }
        }finally {
            lock.release(); //释放锁
        }
        return "抢购失败";
    }
}
