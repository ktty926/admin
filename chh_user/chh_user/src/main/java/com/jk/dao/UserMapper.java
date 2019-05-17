package com.jk.dao;

import com.jk.bean.RegType;
import com.jk.bean.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User findUserByPhone(String phoneNumber);

    List<RegType> findRegType();

    void saveOneUser(User user);

    void saveCompany(User user);

    User getUserByPasPhone(User user);
}
