package com.jsnu.jd.jsnujd.utils;

import com.jsnu.jd.jsnujd.service.GoodsService;
import com.jsnu.jd.jsnujd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author 魏荣轩
 * @date 2020/8/7 21:56
 */

public class StaticServiceImpl {
    @Autowired
    @Qualifier("GoodsServiceImpl")
    private static GoodsService goodsService;
    public static GoodsService getGoodsService(){return goodsService;}

    @Autowired
    @Qualifier("UserServiceImpl")
    private static UserService userService;
    public static UserService getUserService(){return userService;}
}
