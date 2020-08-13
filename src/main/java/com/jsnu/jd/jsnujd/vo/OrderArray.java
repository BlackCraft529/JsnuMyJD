package com.jsnu.jd.jsnujd.vo;

import com.jsnu.jd.jsnujd.pojo.Goods;
import com.jsnu.jd.jsnujd.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @author 魏荣轩
 * @date 2020/8/13 22:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderArray {
    private String id;
    private List<ShopCartGoods> goodsList;
    private User user;
    private double payment;
    private double settlement;
    private Date createTime;
    private String address;
    private int status;

    public OrderArray(Order order){
        goodsList=new ArrayList<>();
        for(Map.Entry<Goods,Integer> value : order.getGoodsList().entrySet()){
            goodsList.add(new ShopCartGoods(value.getKey(),value.getValue()));
        }
        this.id=order.getId();
        this.user=order.getUser();
        this.payment=order.getPayment();
        this.settlement=order.getSettlement();
        this.createTime=order.getCreateTime();
        this.address=order.getAddress();
        this.status=order.getStatus();
    }
}
