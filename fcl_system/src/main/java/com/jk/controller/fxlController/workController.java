package com.jk.controller.fxlController;

import com.jk.model.fxlModel.Company;
import com.jk.service.fxlService.workService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("demo")
public class workController {
    @Autowired
    private workService workService;

    //公司管理
    @RequestMapping("findCompany")
    @ResponseBody
    public HashMap<String,Object> findCompany(Company company,Integer page, Integer limit){
        return workService.findCompany(company,page,limit);
    }

    //取消合作
    @RequestMapping("cancelCooperation")
    @ResponseBody
    public String cancelCooperation(Integer cooperation){
        workService.cancelCooperation(cooperation);
        return null;
    }

}
