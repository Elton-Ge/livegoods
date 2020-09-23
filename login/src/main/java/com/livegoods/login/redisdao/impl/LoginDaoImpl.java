package com.livegoods.login.redisdao.impl;

import com.livegoods.login.pojo.LoginLog;
import com.livegoods.login.pojo.ValidateCode;
import com.livegoods.login.redisdao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Random;

/**
 * @Auther: Elton Ge
 * @Date: 21/9/20
 * @Description: com.livegoods.login.redisdao.impl
 * @version: 1.0
 */
@Repository
public class LoginDaoImpl implements LoginDao {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    //key是手机号 validatecode 是手机号+4位验证码  ,redis存储验证码
    @Override
    public void set(String key, Object validateCode) {
       
        redisTemplate.opsForValue().set(key, validateCode, Duration.ofMinutes(2L));
    }

    @Override
    public ValidateCode getCode(String key) {
        ValidateCode validateCode = (ValidateCode) redisTemplate.opsForValue().get(key);
        return validateCode;
    }

    @Override
    public Boolean deleteCode(String key) {
        return redisTemplate.delete(key);
    }
    //mongodb存储登录日志
    @Override
    public void insertLoginLog(LoginLog loginLog) {
        mongoTemplate.insert(loginLog,"loginLog");
    }
}
