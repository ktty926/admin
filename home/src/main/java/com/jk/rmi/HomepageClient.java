package com.jk.rmi;

import com.alibaba.fastjson.JSONObject;
import com.jk.model.area.AreaBean;
import com.jk.model.company.CompanyBean;
import com.jk.model.sea.SeaBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@FeignClient("LHP-HOMEPAGE")
public interface HomepageClient {
    @RequestMapping("findSea")
    public List<SeaBean> findSea(@RequestBody SeaBean seaBean);
    @RequestMapping("findArea")
    public HashMap<String, Object> findArea();
    @RequestMapping("journalism")
    public JSONObject journalism();
    @RequestMapping("findLogistics")
    public HashMap<String,Object> findLogistics(@RequestParam("page")Integer page, @RequestParam("limit")Integer limit);
    @RequestMapping("findShouYe")
    public  List<CompanyBean> findShouYe(@RequestBody CompanyBean companyBean);
    @RequestMapping("findTransportation")
    public  List<CompanyBean> findTransportation();
    @RequestMapping("findRegionId")
    public  HashMap<String ,Object> findRegionId(@RequestParam("regionId") Integer regionId);
}
