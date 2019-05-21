package com.jk.controller.homepage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jk.model.area.AreaBean;
import com.jk.model.company.CompanyBean;
import com.jk.model.invoice.InvoicBeane;
import com.jk.model.line.LineBean;
import com.jk.model.sea.SeaBean;
import com.jk.model.tejia.TejiaBean;
import com.jk.service.homepage.HomepageService;
import com.jk.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class HomepageController {
@Autowired
    HomepageService homepageService;
@RequestMapping("piao")
@ResponseBody
public  String   piao(){

    return "带你去";
}
//海运
    @RequestMapping("findSea")
    @ResponseBody
    public List<SeaBean>   findSea(SeaBean seaBean){

        return homepageService.findSea(seaBean);
    }

    //地区表
    @RequestMapping("findArea")
    @ResponseBody
    public List<AreaBean> findArea(Integer pid){
        List<AreaBean> area = homepageService.findArea(pid);
        return  area;
    }
    //新闻
    @RequestMapping("journalism")
    @ResponseBody
    public JSONObject journalism() {
        HashMap<String, Object> params = new HashMap<>();
        String returnStr = HttpClientUtil.get("https://www.apiopen.top/journalismApi", params);
        JSONObject parseObject = JSON.parseObject(returnStr);
        int code = parseObject.getIntValue("code");
        //请求成功或者失败
        if(code!=200){
            System.out.println("请求失败");
        }else{

            return parseObject;
        }
        return null;

    }

    //地图经纬度  与题意不符
    @RequestMapping("map")
    @ResponseBody
    public JSONObject map(String key,String lat,String lng,int type) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", key);//申请的KEY
        params.put("lat", lat);//维度
        params.put("lng", lng);//经度
        params.put("type",type);//转换类型，1：GPS->百度 ，3:GPS->谷歌，  5:百度->谷歌 ，6:谷歌->百度
        String returnStr = HttpClientUtil.get("http://v.juhe.cn/offset/index", params);
        JSONObject parseObject = JSON.parseObject(returnStr);
        int resultcode = parseObject.getIntValue("resultcode");
        //请求成功或者失败
        if(resultcode!=200){
            System.out.println("请求失败");
        }else{

            return parseObject;
        }
        return null;

    }

    //物流
    @RequestMapping("findLogistics")
    @ResponseBody
    public  HashMap<String,Object> findLogistics(Integer page,Integer limit){

       return  homepageService.findLogistics(page,limit);
    }

    //首页
    @RequestMapping("findShouYe")
    @ResponseBody
    public  List<CompanyBean> findShouYe(CompanyBean companyBean){
        List<CompanyBean> shouYe = homepageService.findShouYe(companyBean);
        for (int i=0;i<shouYe.size();i++){
            String endHour = shouYe.get(i).getEndHour();
            int endHour1 = Integer.parseInt(endHour);
            shouYe.get(i).setJiaHao(endHour1/24);
        }
        return shouYe;
    }
    //零担物流专线
    @RequestMapping("findTransportation")
    @ResponseBody
    public  List<CompanyBean> findTransportation(){

        return  homepageService.findTransportation();
    }
    //区域查市
    @RequestMapping("findRegionId")
    @ResponseBody
    public  List<AreaBean> findRegionId(Integer regionId){

        return  homepageService.findRegionId(regionId);
    }

    //专线
    @RequestMapping("findLine")
    @ResponseBody
    public  List<LineBean> findLine(){

        return  homepageService.findLine();
    }

    //发货单
    @RequestMapping("findInvoice")
    @ResponseBody
    public  List<InvoicBeane> findInvoice(){

        return  homepageService.findInvoice();
    }
    //特价
    @RequestMapping("findTejia")
    @ResponseBody
    public  List<TejiaBean> findTejia(TejiaBean tejiaBean){
        List<TejiaBean> tejia = homepageService.findTejia(tejiaBean);
        for (int i=0;i<tejia.size();i++){
            Double priceMax = tejia.get(i).getPriceMax();
            Double priceMin = tejia.get(i).getPriceMin();
            Double v = priceMin / priceMax * 10;
            String result = String.format("%.1f",v);
            tejia.get(i).setZheQ(result);
        }
        return  tejia;
    }


}
