package com.jsnu.jd.jsnujd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsnu.jd.jsnujd.pojo.Goods;
import com.jsnu.jd.jsnujd.pojo.User;
import com.jsnu.jd.jsnujd.service.GoodsService;
import com.jsnu.jd.jsnujd.service.OrderService;
import com.jsnu.jd.jsnujd.service.ShopCartService;
import com.jsnu.jd.jsnujd.service.UserService;
import com.jsnu.jd.jsnujd.vo.Order;
import com.jsnu.jd.jsnujd.vo.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author 魏荣轩
 * @date 2020/8/12 20:05
 */
@Controller
public class OrderController {
    @Autowired
    @Qualifier("OrderService")
    private OrderService orderService;

    @Autowired
    @Qualifier("ShopCartService")
    private ShopCartService shopCartService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("GoodsService")
    private GoodsService goodsService;

    /**
     * json工具对象
     */
    private ObjectMapper jsonObjectMapper =  new ObjectMapper();

    /**
     * 获取用户order数据
     * @param jsonData json数据
     * @return order数据表
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/getOrder")
    @ResponseBody
    public String getOrder(@RequestBody String jsonData) throws JsonProcessingException {
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        String userId =  node.get("uuid").toString().replaceAll("\"","");
        return jsonObjectMapper.valueToTree(orderService.getNewestOrderListByUserId(userId)).toString();
    }

    /**
     * 根据购物车删除的数据新建订单信息
     * @param jsonData json数据
     * @return 新建条数
     * @throws JsonProcessingException json转换错误
     */
    @RequestMapping("/pay")
    @ResponseBody
    public String pay(@RequestBody String jsonData) throws JsonProcessingException {
        boolean isDel=false;
        JsonNode node = jsonObjectMapper.readTree(jsonData);
        JsonNode goodsArr = node.get("goods_id");
        String userId = node.get("uuid").toString().replaceAll("\"","");
        HashMap<Goods,Integer> goodsMapOrder = new HashMap<>();
        double settlement = 0D;
        if(goodsArr.isArray()){
            ShopCart shopCart = shopCartService.selectShopCartByUserId(userId).toShopCartVo();
            Map<Goods,Integer> goodsMap = shopCart.getGoodsList();
            for(JsonNode value : goodsArr){
                Goods goods = goodsService.selectGoodsByGoodsId(value.toString().replaceAll("\"",""));
                if(goodsMap.get(goods)!=null) {
                    goodsMapOrder.put(goods,goodsMap.get(goods));
                    settlement+=goods.getSellPrice();
                    goodsMap.remove(goods);
                }
            }
            shopCart.setGoodsList(goodsMap);
            isDel = shopCartService.updateShopCartByPojo(shopCart) != 0;
        }
        User user = userService.selectUserByUserId(userId);
        if(isDel){
            Order order = new Order();
            String orderId = UUID.randomUUID().toString();
            while (orderService.selectOrderByOrderId(orderId)!=null){
                orderId = UUID.randomUUID().toString();
            }
            order.setUser(user);
            order.setAddress(user.getAddress());
            order.setCreateTime(new Date());
            order.setGoodsList(goodsMapOrder);
            order.setPayment(settlement);
            order.setSettlement(settlement);
            order.setStatus(5);
            order.setId(orderId);
            return jsonObjectMapper.valueToTree(orderService.createNewOrder(order)!=0).toString();
        }
        return jsonObjectMapper.valueToTree(false).toString();
    }

}
