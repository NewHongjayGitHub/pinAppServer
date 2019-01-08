package com.yangkw.pin.domain.relation;

import java.time.LocalDateTime;

/**
 * 类UserOrderRelDO.java
 *
 * @author kaiwen.ykw 2018-12-27
 */
public class UserOrderRelDO {
    private Integer id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Integer orderId;
    private Integer userId;
    private Boolean leader;
    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getLeader() {
        return leader;
    }

    public void setLeader(Boolean leader) {
        this.leader = leader;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "UserOrderRelDO{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", leader=" + leader +
                ", deleted=" + deleted +
                '}';
    }
}
