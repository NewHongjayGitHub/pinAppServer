package com.yangkw.pin.domain.request;

import com.yangkw.pin.domain.address.GeoAddress;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 类PublishOrderRequest.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-26
 */
public class PublishOrderRequest extends BaseRequest {
    /**
     * 起点
     */
    @NotNull
    private GeoAddress startAddress;
    /**
     * 终点
     */
    @NotNull
    private GeoAddress endAddress;
    /**
     * 目标出行时间
     */
    private LocalDateTime targetTime;
    /**
     * 队伍人数
     */
    private Integer targetNum;

    private Boolean now;

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

    public LocalDateTime getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(LocalDateTime targetTime) {
        this.targetTime = targetTime;
    }

    public Integer getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(Integer targetNum) {
        this.targetNum = targetNum;
    }

    public Boolean getNow() {
        return now;
    }

    public void setNow(Boolean now) {
        this.now = now;
    }
}
