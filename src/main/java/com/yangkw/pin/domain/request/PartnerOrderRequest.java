package com.yangkw.pin.domain.request;

import javax.validation.constraints.NotNull;

/**
 * 类JoinOrderRequest.java
 *
 * @author kaiwen.ykw 2019-01-01
 */
public class PartnerOrderRequest extends BaseRequest {
    @NotNull(message = "orderId can't null")
    private Integer orderId;
    @NotNull(message = "formId can't null")
    private String formId;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
