package com.milepost.authenticationExample.test.controller;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Ruifu Hua on 2020/2/28.
 */
@Controller
@RequestMapping("/testRedisTemplate")
public class TestRedisTemplateController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 测试键值对
     * @param key
     * @param value
     * @return
     */
    @ResponseBody
    @GetMapping("/testRedisTemplate/opsForValue")
    public String opsForValue(@RequestParam("key") String key, @RequestParam("value") String value){
        redisTemplate.opsForValue().set(key, value);
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * 测试map
     * @param key
     * @param value
     * @return
     */
    @ResponseBody
    @GetMapping("/testRedisTemplate/opsForHash")
    public String opsForHash(@RequestParam("mapKey") String mapKey, @RequestParam("key") String key, @RequestParam("value") String value){
        redisTemplate.opsForHash().put(mapKey, key, value);
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * 测试list
     * @param key
     * @param value
     * @return
     */
    @ResponseBody
    @GetMapping("/testRedisTemplate/opsForList")
    public String rightPush(@RequestParam("key") String key, @RequestParam("value") String value){
        redisTemplate.opsForList().rightPush(key, value);
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * 测试set
     * @param key
     * @param value
     * @return
     */
    @ResponseBody
    @GetMapping("/testRedisTemplate/opsForSet")
    public String opsForSet(@RequestParam("key") String key, @RequestParam("value") String value){
        redisTemplate.opsForSet().add(key, value);
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * 测试zset
     * @param key
     * @param value
     * @return
     */
    @ResponseBody
    @GetMapping("/testRedisTemplate/opsForZSet")
    public String opsForZSet(@RequestParam("key") String key, @RequestParam("value") String value){
        redisTemplate.opsForZSet().add(key, value, 1);
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }

    /**
     * 而是jedis
     * @param key
     * @param value
     * @return
     */
    @ResponseBody
    @GetMapping("/testJedis")
    public String testJedis(@RequestParam("key") String key, @RequestParam("value") String value){
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.set(key, value);
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }
}
