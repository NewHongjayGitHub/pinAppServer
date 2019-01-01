package com.yangkw.pin.domain.order;

import com.yangkw.pin.domain.address.GeoAddress;

import java.time.LocalDateTime;

/**
 * 类Order.java
 *
 * @author kaiwen.ykw 2018-12-26
 */
public class Order {
    /**
     * 是否是队长
     */
    private Boolean leader;
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
    private String publishTime;
    /**
     * 最后更新时间
     */
    private String updateTime;
    /**
     * 目标出行时间
     */
    private String targetTime;

    private LocalDateTime orderItem;
    /**
     * 队伍人数
     */
    private Integer targetNum;
    /**
     * 当前人数
     */
    private Integer currentNum;

    public Boolean getLeader() {
        return leader;
    }

    public Order setLeader(Boolean leader) {
        this.leader = leader;
        return this;
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

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(String targetTime) {
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

    public LocalDateTime getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(LocalDateTime orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public String toString() {
        return "Order{" +
                "leader=" + leader +
                ", id=" + id +
                ", startAddress=" + startAddress +
                ", endAddress=" + endAddress +
                ", publishTime=" + publishTime +
                ", updateTime=" + updateTime +
                ", targetTime=" + targetTime +
                ", targetNum=" + targetNum +
                ", currentNum=" + currentNum +
                '}';
    }
}
