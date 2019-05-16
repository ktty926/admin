package com.jk.controller;

import com.jk.bean.RegType;
import com.jk.bean.User;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
/**
 * @Author chh
 * @Description //TODO 判断手机是否被注册
 * @Date 15:10 2019/5/16
 * @Param
 * @return
 **/
@RequestMapping("findUserByPhone")
@ResponseBody
public HashMap<String,Object> findUserByPhone(String phoneNumber){
    return userService.findUserByPhone(phoneNumber);
}
/**
 * @Author chh
 * @Description //TODO  查询注册类型
 * @Date 17:41 2019/5/16
 * @Param 
 * @return 
 **/
@RequestMapping("findRegType")
@ResponseBody
public List<RegType> findRegType(){
    return userService.findRegType();
}
    /**
     * @Author chh
     * @Description //TODO 短信验证
     * @Date 11:51 2019/5/16
     * @Param 
     * @return 
     **/
    @RequestMapping("duanxin")
    @ResponseBody
    public HashMap<String, Object> phoneTest(String phoneNumber){
        return userService.phoneTest(phoneNumber);

    }




/**
 * @Author chh
 * @Description //TODO 注册
 * @Date 11:40 2019/5/16
 * @Param 
 * @return 
 **/
@RequestMapping("reg")
@ResponseBody
public  HashMap<String,Object> saveUser(User user, String imgcode, String phonecode, HttpServletRequest request){
    return userService.saveUser(user,imgcode,phonecode,request);
}




}
