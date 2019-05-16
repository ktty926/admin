package com.jk.dao;

import com.jk.bean.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findUserByPhone(String phoneNumber);
}
