package com.yangkw.pin.domain.request;

import com.yangkw.pin.domain.address.Dot;

import javax.validation.constraints.NotNull;

/**
 * 类FuzzyOrderRequest.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-26
 */
public class FuzzyOrderRequest extends BaseRequest {
    @NotNull(message = "startAddress can't null")
    private Dot startDot;
    @NotNull(message = "endAddress can't null")
    private Dot endDot;

    public Dot getStartDot() {
        return startDot;
    }

    public void setStartDot(Dot startDot) {
        this.startDot = startDot;
    }

    public Dot getEndDot() {
        return endDot;
    }

    public void setEndDot(Dot endDot) {
        this.endDot = endDot;
    }
}
