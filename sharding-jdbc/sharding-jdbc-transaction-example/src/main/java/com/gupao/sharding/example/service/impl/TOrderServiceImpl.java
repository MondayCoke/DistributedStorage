package com.gupao.sharding.example.service.impl;

import com.gupao.sharding.example.dal.model.TOrder;
import com.gupao.sharding.example.dal.mapper.TOrderMapper;
import com.gupao.sharding.example.service.ITOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mic
 * @since 2021-07-19
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

    @Autowired
    TOrderMapper orderMapper;
    Random random=new Random();

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public TOrder addOrder() {
        for (int i = 0; i <10 ; i++) {
            TOrder tOrder=new TOrder();
            tOrder.setAddressId(1);
            tOrder.setStatus("GLOBAL_TRANSACTION");
            tOrder.setUserId(random.nextInt(1000));
            if(i==4){
                int ex=1/0;
            }
            orderMapper.insert(tOrder);
        }
        return new TOrder();
    }
}
