package com.domain;

public class BoxsType {
	private int btid;
	private String btname;
	private double btmin;
	private double btmoney;
	
	public double getBtmoney() {
		return btmoney;
	}
	public void setBtmoney(double btmoney) {
		this.btmoney = btmoney;
	}
	public int getBtid() {
		return btid;
	}
	public void setBtid(int btid) {
		this.btid = btid;
	}
	public String getBtname() {
		return btname;
	}
	public void setBtname(String btname) {
		this.btname = btname;
	}
	public double getBtmin() {
		return btmin;
	}
	public void setBtmin(double btmin) {
		this.btmin = btmin;
	}
	public BoxsType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoxsType(int btid, String btname, double btmin, double btmoney) {
		super();
		this.btid = btid;
		this.btname = btname;
		this.btmin = btmin;
		this.btmoney = btmoney;
	}
	@Override
	public String toString() {
		return "BoxsType [btid=" + btid + ", btname=" + btname + ", btmin="
				+ btmin + ", btmoney=" + btmoney + "]";
	}
	

}
