package com.gupaoedu.example.zookeepercuratorapplication.service.impl;

import com.gupaoedu.example.zookeepercuratorapplication.dal.model.GoodsStock;
import com.gupaoedu.example.zookeepercuratorapplication.dal.mapper.GoodsStockMapper;
import com.gupaoedu.example.zookeepercuratorapplication.service.IGoodsStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-08-09
 */
@Service
public class GoodsStockServiceImpl extends ServiceImpl<GoodsStockMapper, GoodsStock> implements IGoodsStockService {

}
