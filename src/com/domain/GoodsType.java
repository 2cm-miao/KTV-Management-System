package com.domain;

public class GoodsType {
	private int gtid;
	private String gtname;
	public int getGtid() {
		return gtid;
	}
	public void setGtid(int gtid) {
		this.gtid = gtid;
	}
	public String getGtname() {
		return gtname;
	}
	public void setGtname(String gtname) {
		this.gtname = gtname;
	}
	public GoodsType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GoodsType(int gtid, String gtname) {
		super();
		this.gtid = gtid;
		this.gtname = gtname;
	}
	@Override
	public String toString() {
		return "GoodsType [gtid=" + gtid + ", gtname=" + gtname + "]";
	}
	

}
