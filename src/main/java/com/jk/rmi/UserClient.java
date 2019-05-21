package com.jk.rmi;

import com.jk.bean.PhoneCount;
import com.jk.bean.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@FeignClient("MQSEND")
public interface UserClient {

    @RequestMapping("findRegType")
    List<User> findRegType();

    @RequestMapping("findUserByPhone")
    HashMap<String, Object> findUserByPhone(@RequestParam("phoneNumber") String phoneNumber);

    @RequestMapping("login")
    User login(@RequestBody User user);

    @RequestMapping("duanxin")
    HashMap<String, Object> phoneTest(@RequestParam("phoneNumber")String phoneNumber);

    @RequestMapping("reg")
    HashMap<String, Object> saveUser(@RequestBody User user,@RequestParam("phonecode")  String phonecode);

    @RequestMapping("findPassword")
    HashMap<String, Object> findUserFromRedis(@RequestParam("uuid") String uuid);

    @RequestMapping("exportPoi")
    List<PhoneCount> findUserListByIds(@RequestParam("ids") String ids);

    @RequestMapping("enterPoi")
    void savePoi(@RequestBody PhoneCount phoneCounts);//TODO

    @RequestMapping("phoneLogin")
    HashMap<String, Object> phoneLogin(@RequestBody User user,@RequestParam("phonecode") String phonecode);






}
