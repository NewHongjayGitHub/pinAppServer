package com.yangkw.pin.infrastructure.cache;

import com.alibaba.fastjson.JSON;
import com.yangkw.pin.domain.address.Dot;
import com.yangkw.pin.domain.order.OrderCacheDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 类GeoCache.java
 *
 * @author kaiwen.ykw 2018-12-27
 */
@Component
public abstract class AbstractGeoCache {
    @Autowired
    protected StringRedisTemplate redisTemplate;

    /**
     * 增加缓存
     *
     * @param dot        坐标
     * @param orderId    订单号
     * @param targetTime 目标时间
     */
    public abstract void add(Dot dot, Integer orderId, LocalDateTime targetTime);

    /**
     * 添加缓存
     *
     * @param key        key
     * @param dot        坐标
     * @param orderId    订单号
     * @param targetTime 目标时间
     */
    protected void add(String key, Dot dot, Integer orderId, LocalDateTime targetTime) {
        GeoOperations<String, String> operations = redisTemplate.opsForGeo();
        Point point = new Point(dot.getLongitude(), dot.getLatitude());
        OrderCacheDO orderCacheDO = new OrderCacheDO();
        orderCacheDO.setOrderId(orderId);
        orderCacheDO.setTargetTime(targetTime);
        operations.add(key, point, JSON.toJSONString(orderCacheDO));
    }

    /**
     * 查询相近orderId
     *
     * @param dot 坐标
     * @return
     */
    public abstract List<Integer> findOrderId(Dot dot);

    protected List<Integer> findOrderId(Dot dot, String key) {
        List<Integer> orderIdList = new LinkedList<>();
        GeoOperations<String, String> operations = redisTemplate.opsForGeo();
        Point point = new Point(dot.getLongitude(), dot.getLatitude());
        Distance distance = new Distance(3, RedisGeoCommands.DistanceUnit.KILOMETERS);
        Circle circle = new Circle(point, distance);
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = operations.radius(key, circle);
        if (geoResults == null) {
            return Collections.emptyList();
        }
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> x : geoResults.getContent()) {
            OrderCacheDO cache = JSON.parseObject(x.getContent().getName(), OrderCacheDO.class);
            if (cache.getTargetTime() != null && cache.getTargetTime().isBefore(LocalDateTime.now())) {
                operations.remove(key, x.getContent().getName());
            } else {
                orderIdList.add(cache.getOrderId());
            }
        }
        return orderIdList;
    }

}
