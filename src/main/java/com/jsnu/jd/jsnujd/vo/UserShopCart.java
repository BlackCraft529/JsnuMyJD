package com.jsnu.jd.jsnujd.vo;

import com.jsnu.jd.jsnujd.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 魏荣轩
 * @date 2020/8/13 17:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserShopCart {
    private String owner;
    private String cartId;
    private List<ShopCartGoods> shopCartGoods;

    public UserShopCart(ShopCart shopCart){
        this.shopCartGoods=new ArrayList<>();
        for(Map.Entry<Goods,Integer> value : shopCart.getGoodsList().entrySet()){
            this.shopCartGoods.add(new ShopCartGoods(value.getKey(),value.getValue()));
        }
    }
}
