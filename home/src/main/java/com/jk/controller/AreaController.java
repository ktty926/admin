package com.jk.controller;

import com.jk.model.Cities;
import com.jk.model.Districts;
import com.jk.model.Provinces;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RestController
public class AreaController {
    @RequestMapping("findArea")
    public HashMap<String,Object> findArea(){
        HashMap<String, Object> hashMap = new HashMap<>();
        ArrayList<Cities> citiesList = new ArrayList<>(Arrays.asList(
                    new Cities("2","北京市",true,"1",""),
                new Cities("657","石家庄市",false,"656",""),
                new Cities("958","唐山市",false,"656",""),
                new Cities("1214","秦皇岛市",false,"656",""),
                new Cities("1321","邯郸市",false,"656",""),
                new Cities("1582","邢台市",false,"656",""),
                new Cities("1800","保定市",false,"656","")));
        ArrayList<Districts> districtsList = new ArrayList<>(Arrays.asList(
                new Districts("670","桥东区","657"),
                new Districts("694","新华区","657"),
                new Districts("710","井陉矿区","657"),
                new Districts("974","路北区","958"),
                new Districts("944","鹿泉市","958"),
                new Districts("1236","山海关区","1214"),
                new Districts("1294","抚宁县","1214")
        ));
        ArrayList<Provinces> provincesList = new ArrayList<>(Arrays.asList(
                new Provinces("656","河北",false,"0"),
                new Provinces("1","北京",true,"0"),
                new Provinces("4641","山西",false,"0")
        ));
        hashMap.put("cities",citiesList);
        hashMap.put("districts",districtsList);
        hashMap.put("provinces",provincesList);
        return hashMap;
    }
}
