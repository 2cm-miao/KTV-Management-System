package com.domain;

public class Boxs {
	private int bid;
	private int btid;
	private String btname;
	private double btmin;
	private String bstate;
	private Integer wid;
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
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
	public String getBstate() {
		return bstate;
	}
	public void setBstate(String bstate) {
		this.bstate = bstate;
	}
	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}
	public Boxs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Boxs(int bid, int btid, String btname, double btmin, String bstate, Integer wid) {
		super();
		this.bid = bid;
		this.btid = btid;
		this.btname = btname;
		this.btmin = btmin;
		this.bstate = bstate;
		this.wid = wid;
	}
	@Override
	public String toString() {
		return "Boxs [bid=" + bid + ", btid=" + btid + ", btname=" + btname
				+ ", btmin=" + btmin + ", bstate=" + bstate + ", wid=" + wid
				+ "]";
	}
	

}
