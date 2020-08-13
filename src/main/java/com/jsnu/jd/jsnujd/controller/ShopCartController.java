package com.jsnu.jd.jsnujd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsnu.jd.jsnujd.pojo.Goods;
import com.jsnu.jd.jsnujd.pojo.User;
import com.jsnu.jd.jsnujd.service.GoodsService;
import com.jsnu.jd.jsnujd.service.ShopCartService;
import com.jsnu.jd.jsnujd.vo.ShopCart;
import com.jsnu.jd.jsnujd.vo.UserShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author 魏荣轩
 * @date 2020/8/12 13:15
 */
@Controller
public class ShopCartController {
    @Autowired
    @Qualifier("ShopCartService")
    private ShopCartService shopCartService;

    @Autowired
    @Qualifier("GoodsService")
    private GoodsService goodsService;

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
    public String getCartList(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String userId=node.get("uuid").toString().replaceAll("\"","");
        UserShopCart shopCart = shopCartService.selectShopCartByUserId(userId);
        return jsonObjectMapper.valueToTree(shopCart).toString();
    }

    /**
     * 根据用户请求删除用户车内容
     * @param jsonData json数据
     * @return 删除是否成功
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/delSome")
    @ResponseBody
    public String delSome(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        JsonNode goodsArr = node.get("goods_id");
        String userId = node.get("uuid").toString().replaceAll("\"","");
        if(goodsArr.isArray()){
            ShopCart shopCart = shopCartService.selectShopCartByUserId(userId).toShopCartVo();
            Map<Goods,Integer> goodsMap = shopCart.getGoodsList();
            for(JsonNode value : goodsArr){
                Goods goods = goodsService.selectGoodsByGoodsId(value.toString().replaceAll("\"",""));
                goodsMap.remove(goods);
            }
            shopCart.setGoodsList(goodsMap);
            System.out.println(shopCart);
            return jsonObjectMapper.valueToTree(shopCartService.updateShopCartByPojo(shopCart) != 0).toString();
        }
        return jsonObjectMapper.valueToTree(false).toString();
    }

    /**
     * 删除用户购物车信息
     * @param jsonData json数据
     * @return 返回是否删除成功
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/delAll")
    @ResponseBody
    public String delAll(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String userId = node.get("uuid").toString().replaceAll("\"","");
        String cartId = shopCartService.selectShopCartByUserId(userId).getCartId();
        shopCartService.deleteShopCartByCartId(cartId);
        return jsonObjectMapper.valueToTree(shopCartService.selectShopCartByUserId(userId)==null).toString();
    }

    /**
     * 新增用户购物车数量
     * @param jsonData json数据
     * @return 是否新增
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/enrollCart")
    @ResponseBody
    public String enrollCart(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String userId=node.get("uuid").toString().replaceAll("\"","");
        String goodsId=node.get("goods_id").toString().replaceAll("\"","");
        return jsonObjectMapper.valueToTree(shopCartService.addShopCartGoodsByUserId(userId,goodsId,1) != 0).toString();
    }
}
