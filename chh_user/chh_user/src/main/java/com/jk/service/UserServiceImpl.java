package com.jk.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.jk.ConstantConf;
import com.jk.bean.PhoneCount;
import com.jk.bean.RegType;
import com.jk.bean.User;
import com.jk.dao.UserMapper;
import com.jk.utils.HttpClientUtil;
import com.jk.utils.Md5Util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private MongoTemplate  mongoTemplate;
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
        //先判断是否在黑名单
        if(!StringUtils.isEmpty(phoneNumber)){
            Query query = new Query();
            query.addCriteria(Criteria.where("phoneNumber").is(phoneNumber));
            List<PhoneCount> phoneCounts = mongoTemplate.find(query, PhoneCount.class);
            if(phoneCounts.size() > 0){
                hash.put("code",1);
                hash.put("msg","该手机号被加入黑名单");
                return hash;
            }
        }else {
            hash.put("code",1);
            hash.put("msg","请输入手机号");
            return hash;
        }
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
        if(llen1 >= 3){

                PhoneCount phoneCount = new PhoneCount();
                phoneCount.setId(UUID.randomUUID().toString());
                phoneCount.setPhoneNumber(phoneNumber);
                phoneCount.setStatus(1);//状态1 为黑名单
                mongoTemplate.insert(phoneCount);

            hash.put("code",1);
            hash.put("msg","该手机号今天已经发送三次，超过上限次数");
            return hash;
        }
        //判断是否1 分钟以内
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
public HashMap<String, Object> saveUser(User user,String imgcode,String phonecode) {
    Jedis redis = jedisPool.getResource();
    HashMap<String, Object> hashMap= new HashMap<>();
    //先判断 该手机是否注册
    User user2 = userMapper.findUserByPhone(user.getPhoneNumber());
    if(user2 != null){
        hashMap.put("code",1);
        hashMap.put("msg","该手机已被注册");
        return hashMap;
    }
    //验证码 TODO
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
        hashMap.put("code", 0);
        hashMap.put("msg", "发货方注册成功");
    }else{
        userMapper.saveCompany(user);
        hashMap.put("code", 0);
        hashMap.put("msg", "公司注册成功");
        hashMap.put("type",2);
    }
    return hashMap;
}
/**
 * @Author chh
 * @Description //TODO   登录    type==1 时，是 发货方   type==2 时，是物流公司登录
 * @Date 10:46 2019/5/17
 * @Param   on
 * @return
 **/
    @Override
    public HashMap<String, Object> login(User user) {
        Jedis redis = jedisPool.getResource();
        HashMap<String, Object> hashMap= new HashMap<>();
        String uuid = UUID.randomUUID().toString();
        //判断用户名和密码是否正确
        String password = user.getPassword();
        String md516 = Md5Util.getMd516(password);
        user.setPassword(md516);
        User userFromDb = userMapper.getUserByPasPhone(user);

        if (userFromDb != null) {
            //如果密码正确判断是否选择了记住密码
            if (user.getRemPwd() != null) {
                //如果选择了记住密码  存入cookie中
                Cookie cookie = new Cookie(ConstantConf.cookieNamePaw, userFromDb.getPhoneNumber() + ConstantConf.splitC + userFromDb.getPassword());
                cookie.setMaxAge(604800);//过期时间为一周
              /*  response.addCookie(cookie);*/
                redis.set(ConstantConf.COOKIEUUID+uuid,cookie.toString());
                redis.expire(ConstantConf.COOKIEUUID+uuid,604800);
            } else {
                //如果没有勾选记住密码,清除cookie
                Cookie cookie = new Cookie(ConstantConf.cookieNamePaw, "");
                cookie.setMaxAge(0);//
                /*response.addCookie(cookie);*/
                redis.set(ConstantConf.COOKIEUUID+uuid,cookie.toString());
                redis.expire(ConstantConf.COOKIEUUID+uuid,0);
            }
        } else {
            Cookie cookie = new Cookie(ConstantConf.cookieNamePaw, "");
            cookie.setMaxAge(0);
            redis.set(ConstantConf.COOKIEUUID+uuid,cookie.toString());
            redis.expire(ConstantConf.COOKIEUUID+uuid,0);
            hashMap.put("code", 1);
            hashMap.put("msg", "密码或者账号输入错误");
            return hashMap;
        }
        hashMap.put("uuid",uuid);
        hashMap.put("type", userFromDb.getUsertype());
        hashMap.put("code", 0);
        hashMap.put("msg", "登录成功");
        return hashMap;

    }
/**
 * @Author chh
 * @Description //TODO         查询密码  通过UUIDs   返回on  或 null
 * @Date 15:14 2019/5/17
 * @Param
 * @return
 **/
    @Override
    public HashMap<String, Object> findUserFromRedis(String uuid) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Jedis redis = jedisPool.getResource();
        String s = redis.get(ConstantConf.COOKIEUUID + uuid);
        if(!StringUtils.isEmpty(s)){
            hashMap.put("code", 0);
            hashMap.put("msg", "可直接登陆");
            hashMap.put("on","on");
        }else{
            hashMap.put("code", 1);
            hashMap.put("msg", "已过期");
            hashMap.put("on",null);
        }
        return hashMap;
    }












}

