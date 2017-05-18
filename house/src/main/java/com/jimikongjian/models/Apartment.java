package com.jimikongjian.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;



@Scope("prototype")
@Repository("apartment")
public class Apartment {
	private int id;
	private String apartmentName;
	private String companyName;
	private String allNum;
	private String rentNum;
	private String appointNum;
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAllNum() {
		return allNum;
	}
	public void setAllNum(String allNum) {
		this.allNum = allNum;
	}
	public String getRentNum() {
		return rentNum;
	}
	public void setRentNum(String rentNum) {
		this.rentNum = rentNum;
	}
	public String getAppointNum() {
		return appointNum;
	}
	public void setAppointNum(String appointNum) {
		this.appointNum = appointNum;
	}
}
