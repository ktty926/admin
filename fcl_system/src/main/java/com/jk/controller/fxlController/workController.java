package com.jk.controller.fxlController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("demo")
public class workController {
    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return "123";
    }

}
