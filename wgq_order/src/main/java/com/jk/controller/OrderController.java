package com.jk.controller;

import com.jk.bean.AreaBean;
import com.jk.bean.Order;
import com.jk.bean.TreeBean;
import com.jk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("findArea")
    @ResponseBody
    public List<AreaBean> findArea(Integer pid){
        return orderService.findArea(pid);
    }

    @RequestMapping("getTree")
    @ResponseBody
    public List<TreeBean> getTree(){
        return orderService.getTree();
    }

    @RequestMapping("findOrder")
    @ResponseBody
    public HashMap<String, Object> findOrder(@RequestBody Order order, HttpServletRequest request, Integer page, Integer limit){

        return orderService.findOrder(order, page, limit);
    }
}