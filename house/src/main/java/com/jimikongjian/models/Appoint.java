package com.jimikongjian.models;

import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;



@Scope("prototype")
@Repository("appoint")
public class Appoint {
	private int id;
	private int houseId;
	private int ownerId;
	private String renterName;
	private String renterPhone;
	private String renterSex;
	private Timestamp date;
	private String time;
	private String bussiness;
	private String community;
	private String ownerTel;
	private String owner;
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwnerTel() {
		return ownerTel;
	}
	public void setOwnerTel(String ownerTel) {
		this.ownerTel = ownerTel;
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
	public String getRenterPhone() {
		return renterPhone;
	}
	public void setRenterPhone(String renterPhone) {
		this.renterPhone = renterPhone;
	}
	public String getRenterSex() {
		return renterSex;
	}
	public void setRenterSex(String renterSex) {
		this.renterSex = renterSex;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
