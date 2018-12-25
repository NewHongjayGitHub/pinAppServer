package com.yangkw.pin.domain.login;

import com.yangkw.pin.domain.user.UserInfo;

import javax.validation.constraints.NotNull;

/**
 * 类LoginInfo.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-21
 */
public class LoginInfoDTO {
    @NotNull
    private String code;
    @NotNull
    private UserInfo userInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "LoginInfoDTO{" +
                "code='" + code + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
