package com.yangkw.pin.infrastructure.cache;

import com.yangkw.pin.domain.user.UserToken;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 类UserTokenManager.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-22
 */
@Component
@CacheConfig(cacheNames = "tokenMap")
public class TokenCache {
    @CachePut(key = "#p0")
    public UserToken generateToken(String token, Integer id, String sessionKey, String openid) {
        UserToken userToken = new UserToken();
        userToken.setSessionKey(sessionKey);
        userToken.setOpenid(openid);
        userToken.setUserId(id);
        userToken.setToken(token);
        return userToken;
    }

    @Cacheable(key = "#p0")
    public UserToken getInfo(String token) {
        return null;
    }

}
