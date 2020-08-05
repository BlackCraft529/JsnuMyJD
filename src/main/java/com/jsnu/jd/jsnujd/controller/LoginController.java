package com.jsnu.jd.jsnujd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsnu.jd.jsnujd.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 魏荣轩
 * @date 2020/8/5 14:51
 */
@Controller
public class LoginController {
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 测试方法
     *
     * @return json数据
     */
    @RequestMapping("/test")
    @ResponseBody
    public String getUser(){
        return objectMapper.valueToTree(new User()).toString();
    }
}
