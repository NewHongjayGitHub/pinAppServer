package com.yangkw.pin.domain.response;

/**
 * 类LoginResponse.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-22
 */
public class LoginResponse extends BaseResponse {
    private String token;
    private String expireDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
