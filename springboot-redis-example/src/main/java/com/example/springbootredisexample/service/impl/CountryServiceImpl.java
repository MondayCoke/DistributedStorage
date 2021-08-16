package com.example.springbootredisexample.service.impl;

import com.example.springbootredisexample.dal.model.Country;
import com.example.springbootredisexample.dal.mapper.CountryMapper;
import com.example.springbootredisexample.service.ICountryService;
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
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements ICountryService {

}
