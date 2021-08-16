package com.example.springbootredisexample.service.impl;

import com.example.springbootredisexample.dal.model.City;
import com.example.springbootredisexample.dal.mapper.CityMapper;
import com.example.springbootredisexample.service.ICityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-26
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

}
