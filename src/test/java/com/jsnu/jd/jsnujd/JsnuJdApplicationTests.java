package com.jsnu.jd.jsnujd;

import com.jsnu.jd.jsnujd.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsnuJdApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
    }

    @Test
    void testPasswordIsMatch(){
        System.out.println("密码匹配测试："+userService.userPasswordIsMatch("UUID-123456","1515206"));
    }

}
