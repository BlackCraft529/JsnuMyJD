package com.jsnu.jd.jsnujd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author 魏荣轩
 * @date 2020/8/7 22:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCart {
    private String id;
    private String ownerId;
    private Map<String , Integer> goodsList;
}
