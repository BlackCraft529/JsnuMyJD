package com.jsnu.jd.jsnujd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsnu.jd.jsnujd.service.GoodsService;
import com.jsnu.jd.jsnujd.vo.VagueGoods;
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

    /**
     * 模糊关键字搜索商品信息
     * @param jsonData json数据
     * @return 商品列表
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/getSomething")
    @ResponseBody
    public String getSomething(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String vagueName = node.get("search_key").toString().replaceAll("\"","");
        VagueGoods vagueGoods = new VagueGoods();
        if(goodsService.selectGoodsByVagueName(vagueName).size()==0){
            vagueGoods.setResult(false);
            return jsonObjectMapper.valueToTree(vagueGoods).toString();
        }
        vagueGoods.setGoodsList(goodsService.selectGoodsByVagueName(vagueName));
        vagueGoods.setResult(true);
        return jsonObjectMapper.valueToTree(vagueGoods).toString();
    }

    /**
     * 获取商品信息
     * @param jsonData json数据
     * @return 商品信息
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/getGoods")
    @ResponseBody
    public String getGoods(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String goodsId = node.get("goods_id").toString().replaceAll("\"","");
        return jsonObjectMapper.valueToTree(goodsService.selectGoodsByGoodsId(goodsId)).toString();
    }
}
