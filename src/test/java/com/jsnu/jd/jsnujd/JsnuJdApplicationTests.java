package com.jsnu.jd.jsnujd;

import com.jsnu.jd.jsnujd.pojo.Goods;
import com.jsnu.jd.jsnujd.service.GoodsService;
import com.jsnu.jd.jsnujd.service.UserService;
import com.jsnu.jd.jsnujd.utils.AddressUtil;
import com.jsnu.jd.jsnujd.utils.StaticServiceImpl;
import com.jsnu.jd.jsnujd.vo.VagueGoods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

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
    void testAddressGetter() throws IOException {
        System.out.println(AddressUtil.getLocationByIp("223.66.155.79"));
    }

    @Test
    void findAllUser(){
        System.out.println(userService.selectUserByUserId("UUID-123456"));
    }

    @Test
    void testFindGoods(){
        String vagueName="手表";
        VagueGoods vagueGoods = new VagueGoods();
        if(goodsService.selectGoodsByVagueName(vagueName).size()==0){
            vagueGoods.setResult(false);
            System.out.println(vagueGoods.toString());
            return;
        }
        vagueGoods.setGoodsList(goodsService.selectGoodsByVagueName(vagueName));
        vagueGoods.setResult(true);
        System.out.println(vagueGoods.toString());
    }

    @Test
    void TestVagueGoodsSet(){
        System.out.println(StaticServiceImpl.getUserService()
                .selectUserByUserId("5f09034154114554832f1f7fd351122e").getName());
    }
}
