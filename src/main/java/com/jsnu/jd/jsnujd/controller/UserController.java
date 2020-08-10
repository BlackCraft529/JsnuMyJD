package com.jsnu.jd.jsnujd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsnu.jd.jsnujd.pojo.User;
import com.jsnu.jd.jsnujd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 魏荣轩
 * @date 2020/8/5 14:51
 */
@Controller
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    /**
     * json工具对象
     */
    private ObjectMapper jsonObjectMapper =  new ObjectMapper();

    /**
     * 登录请求
     * @param jsonData json字符串
     * @return 登录对象
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/loginAction")
    @ResponseBody
    public String loginAction(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String account=node.get("account").toString();
        String password=node.get("password").toString();
        User user=userService.matchUserPasswordByVagueKey(account,password);
        return jsonObjectMapper.valueToTree(user).toString();
    }

    /**
     * 用户注册
     * @param jsonData json数据
     * @return 用户
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/registerUser")
    @ResponseBody
    public String registerNewUser(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String name = node.get("name").toString();
        String password = node.get("password").toString();
        String phone = node.get("phone").toString();
        String email = node.get("mail").toString();
        User user = new User();
        if(userService.addUser(password,name,phone,email,"未设置",false)>0){
            user=userService.selectUserByPhone(phone);
        }
        return jsonObjectMapper.valueToTree(user).toString();
    }

    /**
     * 检查用户是否存在
     * @param jsonData json数据
     * @return 是否存在
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/getName")
    @ResponseBody
    public String getName(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String name = node.get("name").toString();
        if(userService.selectUserByName(name)!=null){
            return jsonObjectMapper.valueToTree(true).toString();
        }
        return jsonObjectMapper.valueToTree(false).toString();
    }

    /**
     * 检查用户手机号是否存在
     * @param jsonData json数据
     * @return boolean json数据
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/getPhone")
    @ResponseBody
    public String getPhone(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String phone = node.get("phone").toString();
        if(userService.selectUserByPhone(phone)!=null){
            return jsonObjectMapper.valueToTree(true).toString();
        }else {
            return jsonObjectMapper.valueToTree(false).toString();
        }
    }

    /**
     * 检查用户邮箱是否存在
     * @param jsonData json数据
     * @return boolean json数据
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/getMail")
    @ResponseBody
    public String getEmail(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String email = node.get("email").toString();
        if(userService.selectUserByEmail(email)!=null){
            return jsonObjectMapper.valueToTree(true).toString();
        }else {
            return jsonObjectMapper.valueToTree(false).toString();
        }
    }
}
