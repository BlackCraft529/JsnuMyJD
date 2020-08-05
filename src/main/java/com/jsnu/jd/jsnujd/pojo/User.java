package com.jsnu.jd.jsnujd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 魏荣轩
 * @date 2020/8/5 14:49
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String uuid;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String avatar;
    private Date lastLoginTime,registerTime;
}
