package com.jk.service;

import com.jk.bean.Order;
import com.jk.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public HashMap<String, Object> findOrder(Order order, int page, int limit) {

        HashMap<String, Object> hashMap = new HashMap<>();
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap.put("page", (page-1)*limit);
        hashMap.put("limit", limit);
        hashMap.put("order", order);

        long count = orderMapper.findOrderByCount(hashMap);
        List<Order> list = orderMapper.findOrder(hashMap);

        hashMap2.put("code", 0);
        hashMap2.put("msg", "");
        hashMap2.put("data", list);
        hashMap2.put("count", count);

        return hashMap2;
    }
}
