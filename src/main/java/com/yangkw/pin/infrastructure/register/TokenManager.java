package com.yangkw.pin.infrastructure.register;

import com.yangkw.pin.domain.user.UserToken;
import com.yangkw.pin.service.util.RandomUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类UserTokenManager.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-22
 */
@Component
@CacheConfig(cacheNames = "token")
public class TokenManager {
    private static Map<String, UserToken> tokenMap = new ConcurrentHashMap<>();
    private static Map<Integer, UserToken> idMap = new ConcurrentHashMap<>();

    public Integer getUserId(String token) {
        UserToken userToken = tokenMap.get(token);
        if (userToken == null) {
            return null;
        }
        if (userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            tokenMap.remove(token);
            idMap.remove(userToken.getUserId());
            return null;
        }
        return userToken.getUserId();
    }

    @CachePut(key = "#p0")
    public UserToken generateToken(Integer id, String sessionKey, String openid) {
        String token;
        do {
            token = RandomUtil.getRandomString(32);
        }
        while (tokenMap.containsKey(token));

        LocalDateTime update = LocalDateTime.now();
        LocalDateTime expire = update.plusWeeks(1);

        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken.setUpdateTime(update);
        userToken.setExpireTime(expire);
        userToken.setSessionKey(sessionKey);
        userToken.setOpenid(openid);
        userToken.setUserId(id);
        tokenMap.put(token, userToken);
        idMap.put(id, userToken);

        return userToken;
    }

    public String getSessionKey(Integer userId) {
        UserToken userToken = idMap.get(userId);
        if (userToken == null) {
            return null;
        }

        if (userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            tokenMap.remove(userToken.getToken());
            idMap.remove(userId);
            return null;
        }

        return userToken.getSessionKey();
    }

    public void removeToken(Integer userId) {
        UserToken userToken = idMap.get(userId);
        String token = userToken.getToken();
        idMap.remove(userId);
        tokenMap.remove(token);
    }
}

