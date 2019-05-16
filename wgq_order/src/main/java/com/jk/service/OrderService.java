package com.jk.service;

import com.jk.bean.Order;

import java.util.HashMap;

public interface OrderService {

    HashMap<String, Object> findOrder(Order order, int page, int limit);
}
