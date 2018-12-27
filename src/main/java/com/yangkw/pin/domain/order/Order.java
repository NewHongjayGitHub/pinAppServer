package com.yangkw.pin.domain.order;

import com.yangkw.pin.domain.address.GeoAddress;

import java.time.LocalDateTime;

/**
 * 类Order.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-26
 */
public class Order {
    /**
     * 发起人的userId
     */
    private Integer initiator;
    /**
     * 出行单id
     */
    private Integer id;
    /**
     * 起点
     */
    private GeoAddress startAddress;
    /**
     * 终点
     */
    private GeoAddress endAddress;
    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 目标出行时间
     */
    private LocalDateTime targetTime;
    /**
     * 队伍人数
     */
    private Integer targetNum;
    /**
     * 当前人数
     */
    private Integer currentNum;
    /**
     * 立即出发
     */
    private Boolean now;

    public Integer getInitiator() {
        return initiator;
    }

    public void setInitiator(Integer initiator) {
        this.initiator = initiator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
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

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    public Boolean getNow() {
        return now;
    }

    public void setNow(Boolean now) {
        this.now = now;
    }
}
