package com.jk.controller.homepage;

import com.jk.model.area.AreaBean;
import com.jk.model.sea.SeaBean;
import com.jk.service.homepage.HomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomepageController {
@Autowired
    HomepageService homepageService;
@RequestMapping("piao")
@ResponseBody
public  String   piao(){

    return "带你去嫖";
}
    @RequestMapping("findSea")
    @ResponseBody
    public List<SeaBean>   findSea(SeaBean seaBean){

        return homepageService.findSea(seaBean);
    }

    //地区表
    @RequestMapping("findArea")
    @ResponseBody
    public List<AreaBean> findArea(Integer pid){

        return  homepageService.findArea(pid);
    }

}
