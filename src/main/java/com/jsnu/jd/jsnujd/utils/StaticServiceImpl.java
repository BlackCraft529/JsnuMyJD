package com.jsnu.jd.jsnujd.utils;

import com.jsnu.jd.jsnujd.service.GoodsService;
import com.jsnu.jd.jsnujd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * @author 魏荣轩
 * @date 2020/8/7 21:56
 */
@Component
public class StaticServiceImpl {

    @Autowired
    private GoodsService goodsService;
    public static GoodsService getGoodsService(){return goodsServiceImpl;}

    @Autowired
    private UserService userService;
    public static UserService getUserService(){return userServiceImpl;}

    private static GoodsService goodsServiceImpl;
    private static UserService userServiceImpl;


    @PostConstruct
    public void init() {
        goodsServiceImpl = goodsService;
        userServiceImpl = userService;
    }
}
