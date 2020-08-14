package com.jsnu.jd.jsnujd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;

/**
 * @author 魏荣轩
 * @date 2020/8/14 18:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleOrderArray {
    private String orderName;
    private String orderId;
    private String image;
    private int status;

    public SimpleOrderArray(OrderArray orderArray){
        this.orderName = orderArray.getOrderName();
        this.orderId = orderArray.getId();
        this.image = orderArray.getImage();
        this.status = orderArray.getStatus();
    }
}
