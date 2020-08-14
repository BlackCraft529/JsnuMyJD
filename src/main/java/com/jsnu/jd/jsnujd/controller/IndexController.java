package com.jsnu.jd.jsnujd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 魏荣轩
 * @date 2020/8/5 14:40
 */
@Controller
public class IndexController {

    @RequestMapping("/jd1")
    public String toOldJd(){
        return "oldjd";
    }

    @RequestMapping("/cart1")
    public String toCart1(){return "cart1";}
}
