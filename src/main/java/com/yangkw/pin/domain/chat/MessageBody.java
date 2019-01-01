package com.yangkw.pin.domain.chat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类MessageBody.java
 *
 * @author kaiwen.ykw 2018-12-29
 */
public class MessageBody {
    private UserInfoForChat info;
    private String time;
    private String message;
    private Boolean ownMsg;

    public MessageBody(UserInfoForChat info, String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.time = sdf.format(new Date());
        this.info = info;
        this.message = message;
    }

    public MessageBody(String time, UserInfoForChat info, String message) {
        this.time = time;
        this.info = info;
        this.message = message;
    }

    public UserInfoForChat getInfo() {
        return info;
    }

    public void setInfo(UserInfoForChat info) {
        this.info = info;
    }

    public Boolean getOwnMsg() {
        return ownMsg;
    }

    public void setOwnMsg(Boolean ownMsg) {
        this.ownMsg = ownMsg;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
