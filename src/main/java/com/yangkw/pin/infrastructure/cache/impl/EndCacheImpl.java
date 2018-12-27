package com.yangkw.pin.infrastructure.cache.impl;

import com.yangkw.pin.domain.address.Dot;
import com.yangkw.pin.infrastructure.cache.AbstractGeoCache;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 类EndCacheImpl.java
 *
 * @author kaiwen.ykw 2018-12-27
 */
@Component(value = "end")
public class EndCacheImpl extends AbstractGeoCache {
    @Override
    public void add(Dot dot, Integer orderId, LocalDateTime targetTime) {
        super.add("end", dot, orderId, targetTime);
    }

    @Override
    public List<Integer> findOrderId(Dot dot) {
        return super.findOrderId(dot, "end");
    }
}
