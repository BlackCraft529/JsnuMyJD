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

import java.util.UUID;

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

    /**
     * 发布商品
     * @param jsonData json数据
     * @return 是否添加成功
     * @throws JsonProcessingException 转换错误
     */
    @RequestMapping("/release")
    @ResponseBody
    public String addNewGoods(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String goodsName=node.get("name").toString().replaceAll("\"","");
        double price=Double.parseDouble(node.get("price").toString().replaceAll("\"",""));
        String desc=node.get("desc").toString().replaceAll("\"","");
        int leftAmount=Integer.parseInt(node.get("left_amount").toString().replaceAll("\"",""));
        String cate=node.get("cate").toString().replaceAll("\"","");
        String image=node.get("image").toString().replaceAll("\"","");
        String userId=node.get("uuid").toString().replaceAll("\"","");
        //String name,String desc,double price,double sellPrice,String cate,int leftAmount,String image,String publisher
        return jsonObjectMapper.valueToTree(
                goodsService.addGoods(goodsName, desc, price, price, cate, leftAmount, image, userId) != 0).toString();
    }
}
