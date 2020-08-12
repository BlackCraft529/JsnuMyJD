package com.jsnu.jd.jsnujd.vo;

import com.jsnu.jd.jsnujd.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
}
