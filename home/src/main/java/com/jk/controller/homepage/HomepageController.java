package com.jk.controller.homepage;

import com.alibaba.fastjson.JSONObject;
import com.jk.model.Cities;
import com.jk.model.Districts;
import com.jk.model.Provinces;
import com.jk.model.area.AreaBean;
import com.jk.model.company.CompanyBean;
import com.jk.model.sea.SeaBean;
import com.jk.rmi.HomepageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class HomepageController {
    @Autowired
    private HomepageClient homepageClient;
    @RequestMapping("findSea")
    public List<SeaBean> findSea(SeaBean seaBean){
        List<SeaBean> list = homepageClient.findSea(seaBean);
        return list;
    }
    @RequestMapping("findArea")
    public HashMap<String, Object> findArea(){
        return homepageClient.findArea();
    }
    @RequestMapping("journalism")
    public JSONObject journalism(){

        return homepageClient.journalism();
    }
    @RequestMapping("findLogistics")
    public HashMap<String,Object> findLogistics(Integer page,Integer limit){
        HashMap<String,Object> hashMap = homepageClient.findLogistics(page,limit);
        return hashMap;
    }
    @RequestMapping("findShouYe")
    public  List<CompanyBean> findShouYe(CompanyBean companyBean){
        List<CompanyBean> list = homepageClient.findShouYe(companyBean);
        return list;
    }
    @RequestMapping("findTransportation")
    public  List<CompanyBean> findTransportation(){
        List<CompanyBean> list = homepageClient.findTransportation();
        return list;
    }
    @RequestMapping("findRegionId")
    public  HashMap<String ,Object> findRegionId(Integer regionId){
        HashMap<String ,Object> hashMap = homepageClient.findRegionId(regionId);
        return hashMap;
    }
}
