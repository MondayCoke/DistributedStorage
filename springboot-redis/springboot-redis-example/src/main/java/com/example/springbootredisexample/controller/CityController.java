package com.example.springbootredisexample.controller;


import com.alibaba.fastjson.JSON;
import com.example.springbootredisexample.constants.BloomFilterCache;
import com.example.springbootredisexample.constants.RedisKeyConstants;
import com.example.springbootredisexample.dal.model.City;
import com.example.springbootredisexample.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-26
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    ICityService cityService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/{id}")
    public ResponseEntity<City> city(@PathVariable("id")Integer id){
        City city = cityService.getById(id);
        return ResponseEntity.ok(city);
    }

    @GetMapping("/redis/{id}")
    public ResponseEntity<City> cityRedis(@PathVariable("id")Integer id){
        String city=(String)redisTemplate.opsForValue().get(RedisKeyConstants.CITY_KEY+":"+id);
        return ResponseEntity.ok(JSON.parseObject(city,City.class));
    }

    @GetMapping("/bloom/{id}")
    public String filter(@PathVariable("id")Integer id){
        String key=RedisKeyConstants.CITY_KEY+":"+id;
        if(BloomFilterCache.cityBloom.mightContain(key)){
            return redisTemplate.opsForValue().get(key).toString();
        }
        return "数据不存在";
    }
}
