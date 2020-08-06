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

    /**
     * 查询当前用户是否为零售商
     * @param userId 用户ID
     * @return 是否为零售商
     */
    boolean userIsRetailer(String userId);

    /**
     * 新增一个用户信息
     * @param password 明文密码
     * @param name 姓名
     * @param phone 电话
     * @param email 邮箱
     * @param avatar 地址
     * @param isRetailer 是否为商家
     * @return 添加条数
     */
    int addUser(String password,String name,String phone,String email,String avatar,boolean isRetailer);

    /**
     * 更新一个用户的用户名
     * @param userId 用户ID
     * @param name 用户名
     * @return 修改条数
     */
    int updateUserName(String userId,String name);

    /**
     * 更新一个用户的邮箱
     * @param userId 用户ID
     * @param email 邮箱
     * @return 修改条数
     */
    int updateUserEmail(String userId,String email);

    /**
     * 更新用户的最后登录时间
     * @param userId 用户ID
     * @return 修改条数
     */
    int updateUserLastLoginTime(String userId);

    /**
     * 更新用户的手机号
     * @param userId 用户ID
     * @param phone 手机号
     * @return 更新条数
     */
    int updateUserPhone(String userId,String phone);

    /**
     * 更新用户是否为商家
     * @param userId 用户ID
     * @param isRetailer 是否为商家
     * @return 修改条数
     */
    int updateUserRetailer(String userId,Boolean isRetailer);

    /**
     * 更新用户密码
     * @param userId 用户ID
     * @param password 明文密码
     * @return 更新条数
     */
    int updateUserPassword(String userId,String password);

    /**
     * 删除用户数据
     * @param userId 用户ID
     * @return 删除条数
     */
    int deleteUserByUuid(String userId);
}
