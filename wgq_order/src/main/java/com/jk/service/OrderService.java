package com.jk.service;

import com.jk.bean.AreaBean;
import com.jk.bean.Order;
import com.jk.bean.TreeBean;

import java.util.HashMap;
import java.util.List;

public interface OrderService {

    HashMap<String, Object> findOrder(Order order, int page, int limit);

    List<TreeBean> getTree();

    List<AreaBean> findArea(Integer pid);

    Order findOrderByOrderNum(String num);
}
