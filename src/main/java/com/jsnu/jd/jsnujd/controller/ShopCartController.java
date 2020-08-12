package com.jsnu.jd.jsnujd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsnu.jd.jsnujd.service.ShopCartService;
import com.jsnu.jd.jsnujd.vo.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 魏荣轩
 * @date 2020/8/12 13:15
 */
@Controller
public class ShopCartController {
    @Autowired
    @Qualifier("ShopCartService")
    private ShopCartService shopCartService;

    /**
     * json工具对象
     */
    private ObjectMapper jsonObjectMapper =  new ObjectMapper();

    /**
     * 获取用户购物车数据
     * @param jsonData json数据
     * @return 购物车json数据
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/getCartList")
    @ResponseBody
    public String loginAction(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String userId=node.get("uuid").toString().replaceAll("\"","");
        ShopCart shopCart=shopCartService.selectShopCartByUserId(userId);
        return jsonObjectMapper.valueToTree(shopCart).toString();
    }
}
