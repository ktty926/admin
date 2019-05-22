package com.jk.controller.yft;

import com.jk.bean.Echars;
import com.jk.bean.Order;
import com.jk.bean.Withdrawal;
import com.jk.service.yft.FinancingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class FinancingController {
    @Autowired
    private FinancingService service;
    //查询提现
    @RequestMapping("findWithdrawal")
    @ResponseBody
    public HashMap<String,Object> findWithdrawat(Integer page,Integer limit ,Integer shzt,String mindate,String maxdate,String userid){
        HashMap<String, Object> map = service.findWithdrawat((page-1)*limit,limit,shzt,mindate,maxdate,userid);
        return map;
    }
    //查询user余额
    @RequestMapping("getUserMoney")
    @ResponseBody
    public Double getUserMoney(String userId){

        return service.getUserMoney(userId);
    }
    //查询未审核提现总额
    @RequestMapping("getSumExamine")
    @ResponseBody
    public Double getSumExamine(String userid){

        return service.getSumExamine(userid);
    }
    @RequestMapping("addwithdrawal")
    @ResponseBody
    public HashMap<String,Object> addwithdrawal(@RequestBody Withdrawal withdrawal){
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        try {
            service.addwithdrawal(withdrawal);
            hashMap.put("code",0);
            hashMap.put("msg","成功");
        } catch (Exception e) {
            hashMap.put("code",1);
            hashMap.put("msg","失败  提现密码错误");
            e.printStackTrace();
        }

        return hashMap;
    }
    @RequestMapping("updatewith")
    @ResponseBody
    public HashMap<String,Object> updatewith(String id){
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        try {
            service.updatewith(id);
            hashMap.put("code",0);
            hashMap.put("msg","");
        } catch (Exception e) {
            hashMap.put("code",1);
            hashMap.put("msg","");
            e.printStackTrace();
        }

        return hashMap;
    }

    @RequestMapping("findBaoBiao")
    @ResponseBody
    public HashMap<String,Object> findBaoBiao(Integer page,Integer limit,String jyh,String mindate,String maxdate,Integer zt,String userid){
        return service.findBaoBiao((page-1)*limit,limit,jyh,mindate,maxdate,zt,userid);
    }


    @RequestMapping("srZc")
    @ResponseBody
    public Double srZc(String userid){
        return service.srZc(userid);
    }

    @RequestMapping("zc")
    @ResponseBody
    public Double zc(String userid){
        return service.zc(userid);
    }

    @RequestMapping("findtransport")
    @ResponseBody
    public HashMap<String, Object> findtransport(Integer page,Integer limit,
                                                 String ddh,
                                                 Integer sfjs,String mindate,String maxdate,String userid){
        return service.findtransport((page-1)*limit,limit,ddh,sfjs,mindate,maxdate,userid);
    }
    @RequestMapping("getTransportMoney")
    @ResponseBody
    public Double getTransportMoney(String userid){
        return service.getTransportMoney(userid);
    }
    @RequestMapping("findOrderByOrderNum")
    @ResponseBody
    public Order findOrderByOrderNum(String num){
        return service.findOrderByOrderNum(num);
    }
    @RequestMapping("updatetran")
    @ResponseBody
    public HashMap<String,Object> updatetran(String id){
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        try {
            service.updatetran(id);
            hashMap.put("code",0);
            hashMap.put("msg","");
        } catch (Exception e) {
            hashMap.put("code",1);
            hashMap.put("msg","");
            e.printStackTrace();
        }

        return hashMap;
    }
    @RequestMapping("getShouRu")
    @ResponseBody
    public List<Echars> getShouRu(String userid){
        return service.getShouRu(userid);
    }
    @RequestMapping("getZhiChu")
    @ResponseBody
    public List<Echars> getZhiChu(String userid){
        return service.getZhiChu(userid);
    }
}
