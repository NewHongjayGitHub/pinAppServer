package com.yangkw.pin.domain.user;

/**
 * 类UserToken.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-22
 */
public class UserToken {
    private Integer userId;
    private String token;
    private String sessionKey;
    private String openid;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}

