package com.jk.controller;

import com.jk.service.FinancingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class FinancingController {
    @Autowired
    private FinancingService service;
    @RequestMapping("findWithdrawal")
    @ResponseBody
    public HashMap<String,Object> findWithdrawat(Integer page,Integer limit ,Integer shzt,String mindate,String maxdate){
        HashMap<String, Object> map = service.findWithdrawat((page-1)*limit,limit,shzt,mindate,maxdate);
        return map;
    }
    @RequestMapping("getUserMoney")
    @ResponseBody
    public Double getUserMoney(String userId){
        return service.getUserMoney(userId);
    }
    @RequestMapping("getSumExamine")
    @ResponseBody
    public Double getSumExamine(){
        return service.getSumExamine();
    }

}
