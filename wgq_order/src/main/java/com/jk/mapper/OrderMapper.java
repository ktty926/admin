package com.jk.mapper;

import com.jk.bean.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface OrderMapper {

    long findOrderByCount(HashMap<String, Object> hashMap);

    List<Order> findOrder(HashMap<String, Object> hashMap);
}
