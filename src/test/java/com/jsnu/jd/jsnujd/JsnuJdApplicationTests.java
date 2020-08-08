package com.jsnu.jd.jsnujd;

import com.jsnu.jd.jsnujd.pojo.Goods;
import com.jsnu.jd.jsnujd.service.GoodsService;
import com.jsnu.jd.jsnujd.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsnuJdApplicationTests {
    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Test
    void contextLoads() {
    }

    @Test
    void testPasswordIsMatch(){
        System.out.println("密码匹配测试："+userService.userPasswordIsMatch("UUID-123456","1515206"));
    }

    @Test
    void findAllGoods(){
        for (Goods goods:goodsService.selectAllGoods()){
            System.out.println(goods);
        }
    }

    @Test
    void findAllUser(){
        System.out.println(userService.selectUserByUserId("UUID-123456"));
    }
}
