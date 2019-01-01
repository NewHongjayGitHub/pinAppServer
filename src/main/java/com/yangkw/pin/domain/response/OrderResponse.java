package com.yangkw.pin.domain.response;

import com.yangkw.pin.domain.order.Order;

/**
 * 类OrderResponse.java
 *
 * @author kaiwen.ykw 2018-12-30
 */
public class OrderResponse extends BaseResponse {
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
