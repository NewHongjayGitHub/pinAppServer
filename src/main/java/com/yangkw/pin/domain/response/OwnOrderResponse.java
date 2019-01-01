package com.yangkw.pin.domain.response;

import com.yangkw.pin.domain.order.Order;

import java.util.List;

/**
 * 类OwnOrderResponse.java
 *
 * @author kaiwen.ykw 2018-12-31
 */
public class OwnOrderResponse extends BaseResponse {
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
