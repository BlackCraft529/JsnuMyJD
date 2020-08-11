package com.jsnu.jd.jsnujd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsnu.jd.jsnujd.pojo.Goods;
import com.jsnu.jd.jsnujd.service.GoodsService;
import com.jsnu.jd.jsnujd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 魏荣轩
 * @date 2020/8/11 20:16
 */
@Controller
public class GoodsController {
    @Autowired
    @Qualifier("GoodsService")
    private GoodsService goodsService;

    /**
     * json工具对象
     */
    private ObjectMapper jsonObjectMapper =  new ObjectMapper();

    @RequestMapping("/addGoods")
    @ResponseBody
    public boolean addNewGoods(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);

        return false;
    }
}
