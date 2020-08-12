package com.jsnu.jd.jsnujd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author 魏荣轩
 * @date 2020/8/5 17:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCart {
    private String id;
    private String ownerId;
    private String goodsList;

    public ShopCart (com.jsnu.jd.jsnujd.vo.ShopCart shopCart){
        String goodsListMap="";
        for(Map.Entry<Goods, Integer> entry : shopCart.getGoodsList().entrySet()){
            goodsListMap+=entry.getKey().getId()+"#"+entry.getValue()+",";
        }
        this.goodsList=goodsListMap;
        this.ownerId=shopCart.getOwner().getUuid();
        this.id=shopCart.getId();
    }
}
