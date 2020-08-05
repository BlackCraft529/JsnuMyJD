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
    String selectUserPassword(String userId);
}
