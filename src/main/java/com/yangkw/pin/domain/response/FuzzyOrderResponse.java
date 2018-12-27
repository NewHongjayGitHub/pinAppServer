package com.yangkw.pin.domain.response;

import com.yangkw.pin.domain.order.Order;

import java.util.List;

/**
 * 类FuzzyOrderResponse.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-26
 */
public class FuzzyOrderResponse extends BaseResponse {
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
