package com.yangkw.pin.domain.request;

import com.yangkw.pin.domain.address.GeoAddress;

import javax.validation.constraints.NotNull;

/**
 * 类FuzzyOrderRequest.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-26
 */
public class FuzzyOrderRequest extends BaseRequest {
    @NotNull(message = "startAddress can't null")
    private GeoAddress startAddress;
    @NotNull(message = "endAddress can't null")
    private GeoAddress endAddress;

    public GeoAddress getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(GeoAddress startAddress) {
        this.startAddress = startAddress;
    }

    public GeoAddress getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(GeoAddress endAddress) {
        this.endAddress = endAddress;
    }
}
