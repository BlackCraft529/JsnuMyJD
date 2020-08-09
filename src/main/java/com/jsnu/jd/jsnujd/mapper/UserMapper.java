package com.jsnu.jd.jsnujd.mapper;

import com.jsnu.jd.jsnujd.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 魏荣轩
 * @date 2020/8/5 19:56
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 新增一个用户
     * @param user 用户
     * @return 条数
     */
    int addUser(User user);

    /**
     * 查询用户密码
     * @param userId 用户ID
     * @return 用户数据库加密密码串
     */
    String selectUserPasswordByUserId(String userId);

    /**
     * 根据用户邮箱查询用户信息
     * @param email 邮箱
     * @return 用户信息
     */
    User selectUserByEmail(String email);

    /**
     * 根据用户手机号查询密码
     * @param phone 手机号
     * @return 用户数据库加密密码串
     */
    User selectUserByPhone(String phone);

    /**
     * 根据用户ID查询用户信息
     * @param userId 用户ID
     * @return 用户实体类
     */
    User selectUserByUuid(String userId);

    /**
     * 根据用户名查找用户数据
     * @param name 用户名
     * @return 用户
     */
    User selectUserByName(String name);

    /**
     * 删除用户数据
     * @param userId 用户ID
     * @return 成功条数
     */
    int deleteUserByUuid(String userId);

    /**
     * 更新用户数据
     * @param user 用户
     * @return 成功条数
     */
    int editUserInfoByPojo(User user);

}
