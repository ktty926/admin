package com.jk.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.jk.ConstantConf;
import com.jk.bean.RegType;
import com.jk.bean.User;
import com.jk.dao.UserMapper;
import com.jk.utils.HttpClientUtil;
import com.jk.utils.Md5Util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
 * @Description //TODO 查询注册类型
 * @Date 17:43 2019/5/16
 * @Param
 * @return
 **/
    @Override
    public List<RegType> findRegType() {
        return userMapper.findRegType();
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
       /* Long llen = redis.llen(phoneNumber);*/
        String s2 = redis.get(phoneNumber);
        if(!StringUtils.isEmpty(s2)){
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

            redis.set(phoneNumber,s);
            /*redis.lpush(phoneNumber,s);*/
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

/**
 * @Author chh
 * @Description //TODO 用户注册
 * @Date 19:59 2019/5/16
 * @Param       type  1:发货方/  2:承运公司
 * @return
 **/
@Override
public HashMap<String, Object> saveUser(User user,String imgcode,String phonecode, HttpServletRequest request) {
    Jedis redis = jedisPool.getResource();
    HashMap<String, Object> hashMap= new HashMap<>();
    //验证码
  /*  HttpSession session = request.getSession();
    String imgcode1 = session.getAttribute("imgcode").toString();
    if(!imgcode1.equals(imgcode)){
        hashMap.put("code", 1);
        hashMap.put("msg", "验证码错误");
        return hashMap;
    }*/
    //手机验证码
    String s = redis.get(user.getPhoneNumber());
    if(!s.equals(phonecode)){
        hashMap.put("code", 1);
        hashMap.put("msg", "短信验证码错误");
        return hashMap;
    }
    //新增
    Integer type = user.getUsertype();
    user.setPhoneMember(user.getPhoneNumber());
    user.setId(UUID.randomUUID().toString());
    user.setCreateTime(new Date());
    user.setMoney(0.00);
    String md516 = Md5Util.getMd516(user.getPassword());
    user.setPassword(md516);
    if(user.getReferrer()==""){
        user.setReferrer(null);
    }
    if(type == 1){
        user.setSex(1);
        userMapper.saveOneUser(user);
        hashMap.put("type",1);
    }else{
        userMapper.saveCompany(user);
        hashMap.put("type",2);
    }
    return hashMap;
}













}

