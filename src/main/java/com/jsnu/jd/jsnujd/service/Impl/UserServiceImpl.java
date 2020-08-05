package com.jsnu.jd.jsnujd.service.Impl;

import com.jsnu.jd.jsnujd.mapper.UserMapper;
import com.jsnu.jd.jsnujd.service.UserService;
import com.jsnu.jd.jsnujd.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 魏荣轩
 * @date 2020/8/5 20:27
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 玩家密码是否匹配数据库加密数据
     *
     * @param userId   用户ID
     * @param password 明文密码
     * @return 是否匹配
     */
    @Override
    public boolean userPasswordIsMatch(String userId, String password) {
        return MD5Util.getSaltverifyMD5(password,userMapper.selectUserPassword(userId));
    }
}
