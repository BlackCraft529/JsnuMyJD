package com.jsnu.jd.jsnujd.vo;

import com.jsnu.jd.jsnujd.pojo.Goods;
import com.jsnu.jd.jsnujd.pojo.User;
import com.jsnu.jd.jsnujd.utils.StaticServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 魏荣轩
 * @date 2020/8/7 22:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCart {
    private String id;
    private User owner;
    private Map<Goods, Integer> goodsList;

    public ShopCart(com.jsnu.jd.jsnujd.pojo.ShopCart shopCart){
        goodsList=new HashMap<>();
        if(shopCart!=null){
            if(!Objects.equals(shopCart.getGoodsList(), "")) {
                for (String value : shopCart.getGoodsList().split(",")) {
                    String goodsId = value.split("#")[0];
                    int amount = Integer.parseInt(value.split("#")[1]);
                    if (StaticServiceImpl.getGoodsService().selectGoodsByGoodsId(goodsId) == null) {
                        continue;
                    }
                    goodsList.put(StaticServiceImpl.getGoodsService().selectGoodsByGoodsId(goodsId), amount);
                }
            }
            this.id=shopCart.getId();
            this.owner=StaticServiceImpl.getUserService().selectUserByUserId(shopCart.getOwnerId());
        }
    }
}
