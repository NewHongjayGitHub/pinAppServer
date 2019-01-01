package com.yangkw.pin;

import com.yangkw.pin.domain.address.Dot;
import com.yangkw.pin.domain.address.GeoAddress;
import com.yangkw.pin.domain.order.Order;
import com.yangkw.pin.domain.order.TimeDTO;
import com.yangkw.pin.domain.request.FuzzyOrderRequest;
import com.yangkw.pin.domain.request.PublishOrderRequest;
import com.yangkw.pin.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PinApplicationTests {
    @Autowired
    private OrderService orderService;
    @Test
    public void contextLoads() {
    }

    @Test
    public void publishFutureOrder() throws InterruptedException {
        PublishOrderRequest request = new PublishOrderRequest();
        request.setToken("afn0cnjek0qbexjf7dw0t9xqx5f0wxo5");
        request.setTargetNum(3);
        request.setTimeDTO(new TimeDTO(2019, 1, 12, 14, 30));
        GeoAddress start = new GeoAddress();
        start.setAddress("重庆市南岸区重庆邮电大学");
        start.setName("重庆邮电大学");
        start.setDot(new Dot(29.532496, 106.607440));
        request.setStartAddress(start);
        GeoAddress end = new GeoAddress();
        end.setAddress("重庆市南岸区第二师范学院");
        end.setName("第二师范学院");
        end.setDot(new Dot(29.497082, 106.596812));
        request.setEndAddress(end);
        orderService.publish(request);
    }

    @Test
    public void publishNowOrder() throws InterruptedException {
        PublishOrderRequest request = new PublishOrderRequest();
        request.setToken("afn0cnjek0qbexjf7dw0t9xqx5f0wxo5");
        request.setTargetNum(3);
        GeoAddress start = new GeoAddress();
        start.setAddress("重庆市南岸区重庆邮电大学now");
        start.setName("重庆邮电大学now");
        start.setDot(new Dot(29.532495, 106.607440));
        request.setStartAddress(start);
        GeoAddress end = new GeoAddress();
        end.setAddress("重庆市南岸区第二师范学院now");
        end.setName("第二师范学院now");
        end.setDot(new Dot(29.497081, 106.596812));
        request.setEndAddress(end);
        orderService.publish(request);
    }

    @Test
    public void fuzzy() {
        FuzzyOrderRequest request = new FuzzyOrderRequest();
        Dot s = new Dot(29.532495, 106.607440);
        Dot e = new Dot(29.497081, 106.596812);
        request.setStartDot(s);
        request.setEndDot(e);
        List<Order> orderList = orderService.findOrderList(request);
        orderList.forEach(x -> {
            System.out.println(x.toString());
        });
    }

}

