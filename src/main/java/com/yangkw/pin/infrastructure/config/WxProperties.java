package com.yangkw.pin.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 类WxProperties.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-26
 */
@Configuration
@ConfigurationProperties(prefix = "wx")
public class WxProperties {
    private String appId;
    private String secret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}