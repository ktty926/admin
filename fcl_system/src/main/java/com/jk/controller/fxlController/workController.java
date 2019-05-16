package com.jk.controller.fxlController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("demo")
public class workController {
    @RequestMapping("test")
    public String test(){
        return "123";
    }

}
