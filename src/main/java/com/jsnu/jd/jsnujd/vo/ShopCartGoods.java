package com.jsnu.jd.jsnujd.vo;

import com.jsnu.jd.jsnujd.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 魏荣轩
 * @date 2020/8/13 17:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCartGoods {
    private Goods goods;
    private int shopCartAmount;
}
