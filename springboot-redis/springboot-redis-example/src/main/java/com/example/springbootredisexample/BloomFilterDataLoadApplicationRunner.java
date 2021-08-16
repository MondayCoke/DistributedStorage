package com.example.springbootredisexample;

import com.example.springbootredisexample.constants.BloomFilterCache;
import com.example.springbootredisexample.constants.RedisKeyConstants;
import com.example.springbootredisexample.dal.model.City;
import com.example.springbootredisexample.service.ICityService;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Slf4j
@Component
public class BloomFilterDataLoadApplicationRunner implements ApplicationRunner {

    @Autowired
    ICityService cityService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<City> cities=cityService.list();
        BloomFilter<String> bloomFilter=BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),100000000,0.03);
        cities.parallelStream().forEach(city->{
            bloomFilter.put(RedisKeyConstants.CITY_KEY+":"+city.getId());
        });
        BloomFilterCache.cityBloom=bloomFilter;
    }
}
