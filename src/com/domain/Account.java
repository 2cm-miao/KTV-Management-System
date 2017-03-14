package com.domain;

import java.util.Date;

public class Account {
	private int aid;
	private int abid;
	private double amoney;
	private Date atime;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getAbid() {
		return abid;
	}
	public void setAbid(int abid) {
		this.abid = abid;
	}
	public double getAmoney() {
		return amoney;
	}
	public void setAmoney(double amoney) {
		this.amoney = amoney;
	}
	public Date getAtime() {
		return atime;
	}
	public void setAtime(Date atime) {
		this.atime = atime;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int aid, int abid, double amoney, Date atime) {
		super();
		this.aid = aid;
		this.abid = abid;
		this.amoney = amoney;
		this.atime = atime;
	}
	@Override
	public String toString() {
		return "Account [aid=" + aid + ", abid=" + abid + ", amoney=" + amoney
				+ ", atime=" + atime + "]";
	}

}
