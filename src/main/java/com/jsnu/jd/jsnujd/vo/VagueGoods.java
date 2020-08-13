package com.jsnu.jd.jsnujd.vo;

import com.jsnu.jd.jsnujd.pojo.Goods;
import com.jsnu.jd.jsnujd.utils.StaticServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 魏荣轩
 * @date 2020/8/12 20:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VagueGoods {
    private List<Goods> goodsList;
    private boolean result;

    public void setGoodsList(List<Goods> goodsList){
        this.goodsList=new ArrayList<>();
        String goodsPublisher;
        for(Goods goods:goodsList){
            goodsPublisher = StaticServiceImpl.getUserService()
                    .selectUserByUserId(goods.getPublisher()).getName();
            goods.setPublisher(goodsPublisher);
            this.goodsList.add(goods);
        }
    }
}
