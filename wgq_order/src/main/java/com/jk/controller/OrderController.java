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

    @RequestMapping("addOrder")
    @ResponseBody
    public HashMap<String, Object> addOrder(Order order){
        return orderService.addOrder(order);
    }

    @RequestMapping("updateOrderStatus")
    @ResponseBody
    public HashMap<String, Object> updateOrderStatus(String orderNum, String status){
        return orderService.updateOrderStatus(orderNum, status);
    }

    @RequestMapping("addComment")
    @ResponseBody
    public void addComment(String comment){
        orderService.addComment(comment);
    }

    @RequestMapping("findOrderByOrderNu")
    @ResponseBody
    public Order findOrderByOrderNu(String num){
        return orderService.findOrderByOrderNum(num);
    }

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