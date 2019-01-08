package com.yangkw.pin.infrastructure.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 类TemplateCache.java
 *
 * @author kaiwen.ykw 2019-01-02
 */
@Component
@CacheConfig(cacheNames = "templateMap")
public class TemplateCache {
    @CachePut(key = "#p0")
    public String setId(Integer userId, String templateId) {
        return templateId;
    }

    @Cacheable(key = "#p0")
    public String getTemplateId(Integer userId) {
        return null;
    }
}
