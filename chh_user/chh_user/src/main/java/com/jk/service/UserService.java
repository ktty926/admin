package com.jk.service;

import java.util.HashMap;

public interface UserService {

    HashMap<String, Object> phoneTest(String phoneNumber);

    HashMap<String, Object> findUserByPhone(String phoneNumber);
}
