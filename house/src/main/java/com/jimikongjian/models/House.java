package com.jimikongjian.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;



@Scope("prototype")
@Repository("house")
public class House {
	private String owner;
	private int id;
	private String city;
	private String district;
	private String bussiness;
	private String community;
	private String buildingNum;
	private String initNum;
	private String houseNum;
	private Double acrea;
	private Double price;
	private String floor;
	private String orient;
	private String decoration;
	private String rentType;
	private String rentTag;
	private int ownerId;
	private int renterId;
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getBussiness() {
		return bussiness;
	}
	public void setBussiness(String bussiness) {
		this.bussiness = bussiness;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getBuildingNum() {
		return buildingNum;
	}
	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}
	public String getInitNum() {
		return initNum;
	}
	public void setInitNum(String initNum) {
		this.initNum = initNum;
	}
	public String getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}
	public Double getAcrea() {
		return acrea;
	}
	public void setAcrea(Double acrea) {
		this.acrea = acrea;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getOrient() {
		return orient;
	}
	public void setOrient(String orient) {
		this.orient = orient;
	}
	public String getDecoration() {
		return decoration;
	}
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}
	public String getRentType() {
		return rentType;
	}
	public void setRentType(String rentType) {
		this.rentType = rentType;
	}
	public String getRentTag() {
		return rentTag;
	}
	public void setRentTag(String rentTag) {
		this.rentTag = rentTag;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getRenterId() {
		return renterId;
	}
	public void setRenterId(int renterId) {
		this.renterId = renterId;
	}
	

	
}
