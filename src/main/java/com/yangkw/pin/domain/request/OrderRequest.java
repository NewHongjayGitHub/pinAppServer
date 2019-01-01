package com.yangkw.pin.domain.request;

import javax.validation.constraints.NotNull;

/**
 * 类OrderRequest.java
 *
 * @author kaiwen.ykw 2018-12-30
 */
public class OrderRequest extends BaseRequest {
    @NotNull(message = "orderId is null")
    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
