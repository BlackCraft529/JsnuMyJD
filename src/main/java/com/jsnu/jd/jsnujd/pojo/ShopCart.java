package com.jsnu.jd.jsnujd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 魏荣轩
 * @date 2020/8/5 17:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCart {
    private String id;
    private String ownerId;
    private String goodsList;
}
