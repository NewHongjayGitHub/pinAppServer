package com.yangkw.pin.service;

import com.yangkw.pin.domain.address.PublishResult;
import com.yangkw.pin.domain.order.Order;
import com.yangkw.pin.domain.order.OrderDO;
import com.yangkw.pin.domain.relation.UserOrderRelDO;
import com.yangkw.pin.domain.request.FuzzyOrderRequest;
import com.yangkw.pin.domain.request.PublishOrderRequest;
import com.yangkw.pin.infrastructure.repository.OrderRepository;
import com.yangkw.pin.infrastructure.repository.UserOrderRelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类OrderService.java
 *
 * @author kaiwen.ykw 2018-12-27
 */
@Service
public class OrderService {
    private static Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private CacheService cacheService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserOrderRelRepository userOrderRelRepository;
    @Autowired
    private TransactionTemplate transactionTemplate;

    public List<Order> findOrderList(FuzzyOrderRequest request) {
        List<Integer> orderids = cacheService.findNearOrderId(request);
        if (orderids.isEmpty()) {
            return Collections.emptyList();
        }
        List<OrderDO> orderDOList = orderRepository.findAll(orderids);
        if (orderids.isEmpty()) {
            LOG.error("orderDOList is null,orderIds :{}", orderids);
            return Collections.emptyList();
        }
        return orderDOList.stream().map(this::assemble).collect(Collectors.toList());
    }

    public void publish(PublishOrderRequest request) {
        Integer userId = cacheService.getUserId(request.getToken());
//        transactionTemplate.execute(status -> {
        PublishResult publishResult = addressService.publish(request.getStartAddress(), request.getEndAddress());
        OrderDO orderDO = assemble(request, publishResult, userId);
        int row = orderRepository.insert(orderDO);
        userOrderRelRepository.insert(construct(orderDO.getId(), userId));
        cacheService.publishCache(request, orderDO.getId());
//            return true;
//        });
    }

    private UserOrderRelDO construct(Integer orderId, Integer userId) {
        UserOrderRelDO userOrderRelDO = new UserOrderRelDO();
        userOrderRelDO.setOrderId(orderId);
        userOrderRelDO.setUserId(userId);
        userOrderRelDO.setLeader(true);
        userOrderRelDO.setDeleted(false);
        return userOrderRelDO;
    }

    private OrderDO assemble(PublishOrderRequest request, PublishResult result, Integer userId) {
        OrderDO orderDO = new OrderDO();
        orderDO.setStartAddressId(result.getStartId());
        orderDO.setEndAddressId(result.getEndId());
        orderDO.setCreator(userId);
        if (!request.getNow()) {
            orderDO.setTargetTime(request.getTargetTime());
        }
        orderDO.setTargetNum(request.getTargetNum());
        orderDO.setCurrentNum(1);
        orderDO.setNow(request.getNow());
        orderDO.setDeleted(false);
        return orderDO;
    }

    private Order assemble(OrderDO orderDO) {
        Order order = new Order();
        order.setInitiator(orderDO.getCreator());
        order.setId(orderDO.getId());
        order.setStartAddress(addressService.queryGeoAddress(orderDO.getStartAddressId()));
        order.setEndAddress(addressService.queryGeoAddress(orderDO.getEndAddressId()));
        order.setPublishTime(orderDO.getGmtCreate());
        order.setUpdateTime(orderDO.getGmtModified());
        order.setTargetTime(orderDO.getTargetTime());
        order.setTargetNum(orderDO.getTargetNum());
        order.setCurrentNum(orderDO.getCurrentNum());
        order.setNow(orderDO.getNow());
        return order;
    }

}
