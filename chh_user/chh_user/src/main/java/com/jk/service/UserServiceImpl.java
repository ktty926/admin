package com.jk.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.jk.ConstantConf;
import com.jk.bean.User;
import com.jk.dao.UserMapper;
import com.jk.utils.HttpClientUtil;
import com.jk.utils.Md5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
     UserMapper userMapper;
    @Autowired
    private JedisPool jedisPool;
    /**
     * @Author chh
     * @Description //TODO 判断手机是否被注册
     * @Date 15:09 2019/5/16
     * @Param 
     * @return 
     **/

    @Override
    public HashMap<String, Object> findUserByPhone(String phoneNumber) {
        HashMap<String, Object> hash = new HashMap<>();
        //先判断 该手机是否注册
        User user = userMapper.findUserByPhone(phoneNumber);
        if(user != null){
            hash.put("code",1);
            hash.put("msg","该手机已被注册");
        }else{
            hash.put("code",0);
        }
        return hash;
    }
/**
 * @Author chh
 * @Description //TODO 短信验证码
 * @Date 14:24 2019/5/16
 * @Param 
 * @return 
 **/
    @Override
    public HashMap<String, Object> phoneTest(String phoneNumber) {
        Jedis redis = jedisPool.getResource();
        HashMap<String, Object> hash = new HashMap<>();
        Date date = new Date();
        //判断是否今天三次上限
        SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd");
        String format = si.format(date);
        Long llen1 = redis.llen(phoneNumber + format);
        if(llen1 > 3){
            hash.put("code",1);
            hash.put("msg","该手机号今天已经发送三次，请明天再试");
            return hash;
        }
        //判断是否1 分钟以内
        Long llen = redis.llen(phoneNumber);
        if(llen > 0){
            hash.put("code",1);
            hash.put("msg","一分钟内不能重复获取，请稍后重试");
            return hash;
        }
        Integer randomNumber= (int)(Math.random()*899999+100000);
      /*  HashMap<String, Object> params = new HashMap<>();

        params.put("accountSid",ConstantConf.ACCOUNTSID);
        params.put("to",phoneNumber);
        String timestamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        params.put("timestamp",timestamp);
        String sig= Md5Util.getMd532(ConstantConf.ACCOUNTSID+ConstantConf.AUTH_TOKEN+timestamp);
        params.put("sig",sig);
        params.put("templateid",ConstantConf.TEMPLATEID);
        params.put("param",randomNumber);
        String string = HttpClientUtil.post(ConstantConf.SMS_URL,params);

        System.out.println(string);
        JSONObject parseObject = JSON.parseObject(string);
        String string2 = parseObject.getString("respCode");
        if(ConstantConf.SMS_SUCCESS.equals(string2)) {*/
        String s = randomNumber.toString();

            redis.lpush(phoneNumber,s);
            redis.expire(phoneNumber,60);
            redis.lpush(phoneNumber+format,s);
            redis.expire(phoneNumber+format,86400);

            hash.put("yzm",randomNumber);
            hash.put("code",0);
            hash.put("msg","短信发送成功，一分钟内有效");

            return hash;
       /*}else {
            hash.put("code", 1);
            hash.put("msg", "发送失败");
            return hash;
        }*/

    }












}

