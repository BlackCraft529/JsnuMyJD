package com.jsnu.jd.jsnujd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 魏荣轩
 * @date 2020/8/5 16:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private String id;
    private String name;
    private String description;
    private double price,sellPrice;
    private String cate;
    private int leftAmount;
    private String image;
    private int sellAmount;
    private Date createTime;
}
