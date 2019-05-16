package com.jk.service;

import com.jk.bean.RegType;
import com.jk.bean.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface UserService {

    HashMap<String, Object> phoneTest(String phoneNumber);

    HashMap<String, Object> findUserByPhone(String phoneNumber);

    List<RegType> findRegType();

    HashMap<String, Object> saveUser(User user,String imgcode,String phonecode, HttpServletRequest request);
}
