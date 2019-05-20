package com.jk.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class PageController {
    //跳转首页
    @RequestMapping("toHomePage")
    public String toHomePage(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "index";
    }
    //跳转物流专线
    @RequestMapping("toLogisticsLine")
    public String toLogisticsLine(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "logisticsLine";
    }
    //跳转在线发货
    @RequestMapping("toOnlineDelivery")
    public String toOnlineDelivery(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "onlineDelivery";
    }
    //跳转国际海运
    @RequestMapping("toInternationalTransport")
    public String toInternationalTransport(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "internationalTransport";
    }
    @RequestMapping("toLogisticsCompany")
    public String toLogisticsCompany(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "logisticsCompany";
    }
    //跳转新闻
    @RequestMapping("toJournalism")
    public String toJournalism(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "journalism";
    }
    //跳转物流招标
    @RequestMapping("toCallForBids")
    public String toCallForBids(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "callForBids";
    }
    //跳转在线购买
    @RequestMapping("toPurchase")
    public String toPurchase(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "purchase";
    }
    //跳转个人中心
    @RequestMapping("toPersonalCenter")
    public String toPersonalCenter(){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "personalCenter";
    }

}
