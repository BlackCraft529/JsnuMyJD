package com.jsnu.jd.jsnujd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 魏荣轩
 * @date 2020/8/5 14:40
 */
@Controller
public class IndexController {

    /**
     * 登录界面跳转
     *
     * @return 登录界面
     */
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    /**
     * 注册界面跳转
     *
     * @return 注册界面
     */
    @RequestMapping("/register")
    public String toRegister(){
        return "register";
    }
}
