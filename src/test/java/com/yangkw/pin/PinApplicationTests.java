package com.yangkw.pin;

import com.yangkw.pin.domain.address.Dot;
import com.yangkw.pin.domain.address.GeoAddress;
import com.yangkw.pin.domain.request.PublishOrderRequest;
import com.yangkw.pin.infrastructure.cache.AbstractGeoCache;
import com.yangkw.pin.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PinApplicationTests {
    @Autowired
    @Qualifier("start")
    private AbstractGeoCache abstractGeoCache1;
    @Autowired
    private OrderService orderService;
    @Test
    public void contextLoads() {
    }

    @Test
    public void publishOrder() {
        PublishOrderRequest request = new PublishOrderRequest();
        request.setToken("afn0cnjek0qbexjf7dw0t9xqx5f0wxo5");
        request.setNow(false);
        request.setTargetNum(2);
        request.setTargetTime(LocalDateTime.now().plusDays(1));
        GeoAddress start = new GeoAddress();
        start.setAddress("cqyddx");
        start.setName("重庆邮电大学");
        start.setDot(new Dot(30.000000, 130.000000));
        request.setStartAddress(start);
        GeoAddress end = new GeoAddress();
        end.setAddress("alibaba");
        end.setName("阿里巴巴");
        end.setDot(new Dot(30.000010, 130.000000));
        request.setEndAddress(end);
        orderService.publish(request);
    }

}

