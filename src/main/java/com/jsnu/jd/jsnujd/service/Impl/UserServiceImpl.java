package com.jsnu.jd.jsnujd.service.Impl;

import com.jsnu.jd.jsnujd.mapper.UserMapper;
import com.jsnu.jd.jsnujd.pojo.User;
import com.jsnu.jd.jsnujd.service.UserService;
import com.jsnu.jd.jsnujd.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.UUID;

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

    /**
     * 查询当前用户是否为零售商
     *
     * @param userId 用户ID
     * @return 是否为零售商
     */
    @Override
    public boolean userIsRetailer(String userId) {
        if(userMapper.selectUserByUuid(userId)!=null) {
            return userMapper.selectUserByUuid(userId).isRetailer();
        }
        return false;
    }

    /**
     * 新增一个用户信息
     *
     * @param password   明文密码
     * @param name       姓名
     * @param phone      电话
     * @param email      邮箱
     * @param avatar     地址
     * @param isRetailer 是否为商家
     * @return 添加条数
     */
    @Override
    public int addUser(String password, String name, String phone, String email, String avatar, boolean isRetailer) {
        User user = new User();
        String mD5Password = MD5Util.getSaltMD5(password);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        while(userMapper.selectUserByUuid(uuid)!=null){
            uuid = UUID.randomUUID().toString().replaceAll("-","");
        }
        user.setUuid(uuid);
        user.setPassword(mD5Password);
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setAvatar(avatar);
        user.setRegisterTime(new Date());
        user.setLastLoginTime(new Date());
        user.setRetailer(isRetailer);
        return userMapper.addUser(user);
    }

    /**
     * 更新一个用户的用户名
     *
     * @param userId 用户ID
     * @param name   用户名
     * @return 修改条数
     */
    @Override
    public int updateUserName(String userId, String name) {
        if(userMapper.selectUserByUuid(userId)==null){
            return 0;
        }
        User user = userMapper.selectUserByUuid(userId);
        user.setName(name);
        return userMapper.editUserInfoByPojo(user);
    }

    /**
     * 更新一个用户的邮箱
     *
     * @param userId 用户ID
     * @param email  邮箱
     * @return 修改条数
     */
    @Override
    public int updateUserEmail(String userId, String email) {
        if(userMapper.selectUserByUuid(userId)==null){
            return 0;
        }
        User user = userMapper.selectUserByUuid(userId);
        user.setEmail(email);
        return userMapper.editUserInfoByPojo(user);
    }

    /**
     * 更新用户的最后登录时间
     *
     * @param userId 用户ID
     * @return 修改条数
     */
    @Override
    public int updateUserLastLoginTime(String userId) {
        if(userMapper.selectUserByUuid(userId)==null){
            return 0;
        }
        User user = userMapper.selectUserByUuid(userId);
        user.setLastLoginTime(new Date());
        return userMapper.editUserInfoByPojo(user);
    }

    /**
     * 更新用户的手机号
     *
     * @param userId 用户ID
     * @param phone  手机号
     * @return 更新条数
     */
    @Override
    public int updateUserPhone(String userId, String phone) {
        if(userMapper.selectUserByUuid(userId)==null){
            return 0;
        }
        User user = userMapper.selectUserByUuid(userId);
        user.setPhone(phone);
        return userMapper.editUserInfoByPojo(user);
    }

    /**
     * 更新用户是否为商家
     *
     * @param userId     用户ID
     * @param isRetailer 是否为商家
     * @return 修改条数
     */
    @Override
    public int updateUserRetailer(String userId, Boolean isRetailer) {
        if(userMapper.selectUserByUuid(userId)==null){
            return 0;
        }
        User user = userMapper.selectUserByUuid(userId);
        user.setRetailer(isRetailer);
        return userMapper.editUserInfoByPojo(user);
    }

    /**
     * 更新用户密码
     *
     * @param userId   用户ID
     * @param password 明文密码
     * @return 更新条数
     */
    @Override
    public int updateUserPassword(String userId, String password) {
        if(userMapper.selectUserByUuid(userId)==null){
            return 0;
        }
        User user = userMapper.selectUserByUuid(userId);
        user.setPassword(MD5Util.getSaltMD5(password));
        return userMapper.editUserInfoByPojo(user);
    }

    /**
     * 删除用户数据
     *
     * @param userId 用户ID
     * @return 删除条数
     */
    @Override
    public int deleteUserByUuid(String userId) {
        if(userMapper.selectUserByUuid(userId)==null){
            return 0;
        }
        return userMapper.deleteUserByUuid(userId);
    }

    /**
     * 根据用户ID查找用户信息
     *
     * @param userId 用户ID
     * @return 用户
     */
    @Override
    public User selectUserByUserId(String userId) {
        return userMapper.selectUserByUuid(userId);
    }


}
