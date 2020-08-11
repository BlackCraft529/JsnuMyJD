package com.jsnu.jd.jsnujd.vo;

import com.jsnu.jd.jsnujd.pojo.Goods;
import com.jsnu.jd.jsnujd.pojo.User;
import com.jsnu.jd.jsnujd.utils.StaticServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.HashMap;

/**
 * @author 魏荣轩
 * @date 2020/8/7 21:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private HashMap<Goods,Integer> goodsList;
    private User user;
    private double payment;
    private double settlement;
    private Date createTime;
    private String address;
    private int status;
    public Order(com.jsnu.jd.jsnujd.pojo.Order order){
        if(order!=null) {
            for (String value : order.getGoodsList().split(",")) {
                String goodsId = value.split("#")[0];
                int amount = Integer.parseInt(value.split("#")[1]);
                if (StaticServiceImpl.getGoodsService().selectGoodsByGoodsId(goodsId) == null) {
                    continue;
                }
                goodsList.put(StaticServiceImpl.getGoodsService().selectGoodsByGoodsId(goodsId), amount);
            }
            this.id = order.getId();
            this.user = StaticServiceImpl.getUserService().selectUserByUserId(order.getUserId());
            this.payment = order.getPayment();
            this.settlement = order.getSettlement();
            this.createTime = order.getCreateTime();
            this.address=order.getAddress();
            this.status = order.getStatus();
        }
    }
}