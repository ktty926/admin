package com.jk.controller;

import com.jk.bean.RegType;
import com.jk.bean.User;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
/**
 * @Author chh
 * @Description //TODO 判断手机是否被注册
 * @Date 15:10 2019/5/16
 * @Param                 phoneNumber
 * @return               code    msg
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
 * @return               List<RegType>
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
     * @Param            phoneNumber
     * @return           code   msg    yzm
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
 * @Param               user   imgcode    phonecode
 * @return             type     code   msg
 **/
@RequestMapping("reg")
@ResponseBody
public  HashMap<String,Object> saveUser(User user, String imgcode, String phonecode){
    return userService.saveUser(user,imgcode,phonecode);
}


/**
 * @Author chh
 * @Description //TODO       前台项目  登录   (发货方、物流公司可登陆)
 * @Date 10:31 2019/5/17
 * @Param                 usertype    password   phoneNumber
 * @return                uuid   type   code  msg
 **/
    @RequestMapping("login")
    @ResponseBody
    public HashMap<String,Object> login(User user){
      return userService.login(user);
    }

/**
 * @Author chh
 * @Description //TODO      后台系统登录    （仅物流公司可登陆）
 * @Date 12:01 2019/5/17
 * @Param              usertype    password   phoneNumber
 * @return           uuid   type   code  msg
 **/
@RequestMapping("comLogin")
@ResponseBody
public HashMap<String,Object> comLogin(User user){
    HashMap<String, Object> hashMap= new HashMap<>();
if(user.getUsertype()==1){
    hashMap.put("code",1);
    hashMap.put("msg","您目前没有权限登录");
    return hashMap;
}
    return userService.login(user);
}

/**
 * @Author chh
 * @Description //TODO     查询密码  通过UUID
 * @Date 15:12 2019/5/17
 * @Param                       uuid
 * @return                      code   msg    on
 **/
@RequestMapping("findPassword")
@ResponseBody
public HashMap<String,Object> findUserFromRedis(String uuid){
    return userService.findUserFromRedis(uuid);
}








}
