package com.yangkw.pin.domain.order;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 类OrderCache.java
 *
 * @author kaiwen.ykw 2018-12-27
 */
public class OrderCacheDO implements Serializable {
    private static final long serialVersionUID = 6823945518498051272L;
    private Integer orderId;
    private LocalDateTime targetTime;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(LocalDateTime targetTime) {
        this.targetTime = targetTime;
    }

    @Override
    public String toString() {
        return "OrderCacheDO{" +
                "orderId=" + orderId +
                ", targetTime=" + targetTime +
                '}';
    }
}
