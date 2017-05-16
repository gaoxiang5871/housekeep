package com.jimikongjian.models;

import java.sql.Timestamp;

/**
 * Created by fuxiuyang on 17-5-16.
 */
public class Order {

    private int id;

    private int houseId;

    private int ownerId;

    private String renterName;

    private String orderType;

    private Timestamp orderDate;

    private Timestamp startDate;

    private Timestamp endDate;

    private String renterTel;

    private String orderPrice;

    private String ownerTel;
    
	private String orderTag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getRenterTel() {
        return renterTel;
    }

    public void setRenterTel(String renterTel) {
        this.renterTel = renterTel;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOwnerTel() {
        return ownerTel;
    }

    public void setOwnerTel(String ownerTel) {
        this.ownerTel = ownerTel;
    }
    public String getOrderTag() {
		return orderTag;
	}
	public void setOrderTag(String orderTag) {
		this.orderTag = orderTag;
	}
}
