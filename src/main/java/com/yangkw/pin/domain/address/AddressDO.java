package com.yangkw.pin.domain.address;

import java.time.LocalDateTime;

/**
 * 类AddressDO.java
 *
 * @author kaiwen.ykw 2018-12-27
 */
public class AddressDO {
    private Integer id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private String name;
    private String address;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 0 false: 起点
     * 1 true：终点
     */
    private Boolean type;
    private Integer hotPoint;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Integer getHotPoint() {
        return hotPoint;
    }

    public void setHotPoint(Integer hotPoint) {
        this.hotPoint = hotPoint;
    }

    @Override
    public String toString() {
        return "AddressDO{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", type=" + type +
                ", hotPoint=" + hotPoint +
                '}';
    }
}
