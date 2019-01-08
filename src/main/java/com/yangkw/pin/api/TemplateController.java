package com.yangkw.pin.api;

import com.yangkw.pin.domain.request.SaveTemplateIdRequest;
import com.yangkw.pin.infrastructure.cache.TemplateCache;
import com.yangkw.pin.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类TemplateController.java
 *
 * @author kaiwen.ykw 2019-01-02
 */
@RestController
@RequestMapping("template")
public class TemplateController {
    @Autowired
    private CacheService cacheService;
    @Autowired
    private TemplateCache templateCache;

    @PostMapping("save")
    public void save(@RequestBody SaveTemplateIdRequest request) {
        Integer userId = cacheService.getUserId(request.getToken());
        templateCache.setId(userId, request.getTemplateId());
    }
}
