package com.jsnu.jd.jsnujd.service;

/**
 * @author 魏荣轩
 * @date 2020/8/5 20:28
 */
public interface UserService {

    /**
     * 玩家密码是否匹配数据库加密数据
     *
     * @param userId 用户ID
     * @param password 明文密码
     * @return 是否匹配
     */
    boolean userPasswordIsMatch(String userId,String password);
}
