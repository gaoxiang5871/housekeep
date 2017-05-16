package com.jimikongjian.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;



@Scope("prototype")
@Repository("servant")
public class Servant {
	private int id;
	private String owner;
	private String ownerTel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
}
