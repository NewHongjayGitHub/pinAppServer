package com.yangkw.pin.domain.order;

import java.time.LocalDateTime;

/**
 * 类OrderDO.java
 *
 * @author kaiwen.ykw 2018-12-27
 */
public class OrderDO {
    private Integer id;
    private Integer creator;
    private Integer leader;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Integer startAddressId;
    private Integer endAddressId;
    private LocalDateTime targetTime;
    private Integer targetNum;
    private Integer currentNum;
    private Boolean deleted;

    public Integer getLeader() {
        return leader;
    }

    public void setLeader(Integer leader) {
        this.leader = leader;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getStartAddressId() {
        return startAddressId;
    }

    public void setStartAddressId(Integer startAddressId) {
        this.startAddressId = startAddressId;
    }

    public Integer getEndAddressId() {
        return endAddressId;
    }

    public void setEndAddressId(Integer endAddressId) {
        this.endAddressId = endAddressId;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "OrderDO{" +
                "id=" + id +
                ", creator=" + creator +
                ", leader=" + leader +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", startAddressId=" + startAddressId +
                ", endAddressId=" + endAddressId +
                ", targetTime=" + targetTime +
                ", targetNum=" + targetNum +
                ", currentNum=" + currentNum +
                ", deleted=" + deleted +
                '}';
    }
}
