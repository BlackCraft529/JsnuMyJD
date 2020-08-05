package com.jsnu.jd.jsnujd.controller;

import com.jsnu.jd.jsnujd.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

/**
 * @author 魏荣轩
 * @date 2020/8/5 14:51
 */
@Controller
public class LoginController {

    @RequestMapping("/test")
    public String getUser(HttpSession session, Model model){
        model.addAttribute("user",new User());
        return "login";
    }
}
