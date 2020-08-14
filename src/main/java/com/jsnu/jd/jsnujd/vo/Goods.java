package com.jsnu.jd.jsnujd.vo;

import com.jsnu.jd.jsnujd.pojo.User;
import com.jsnu.jd.jsnujd.utils.StaticServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * @author 魏荣轩
 * @date 2020/8/14 19:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private String id;
    private String name;
    private String publisher;
    private String description;
    private double price,sellPrice;
    private String cate;
    private int leftAmount;
    private String image;
    private int sellAmount;
    private Date createTime;

    public Goods(com.jsnu.jd.jsnujd.pojo.Goods goods){
        this.id = goods.getId();
        this.name = goods.getName();
        this.publisher = StaticServiceImpl.getUserService().selectUserByUserId(goods.getPublisher()).getName();
        this.description = goods.getDescription();
        this.price = goods.getPrice();
        this.sellPrice = goods.getSellPrice();
        this.cate = goods.getCate();
        this.leftAmount = goods.getLeftAmount();
        this.image = goods.getImage();
        this.sellAmount = goods.getSellAmount();
        this.createTime = goods.getCreateTime();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof com.jsnu.jd.jsnujd.pojo.Goods)){
            return false;
        }
        com.jsnu.jd.jsnujd.pojo.Goods goods = (com.jsnu.jd.jsnujd.pojo.Goods) o;
        return getId().equals(goods.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
