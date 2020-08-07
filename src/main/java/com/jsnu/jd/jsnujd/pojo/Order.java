package com.jsnu.jd.jsnujd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * @author 魏荣轩
 * @date 2020/8/5 17:13
 * 注：该订单类仅配合数据库
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private String goodsList;
    private String userId;
    private double payment;
    private double settlement;
    private Date createTime;
    private int status;
}
