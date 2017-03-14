package com.domain;

public class Goods {
	private int gid;
	private String gname;
	private double gsell;
	private double gcost;
	private int gcount;
	private String gtname;
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public double getGsell() {
		return gsell;
	}
	public void setGsell(double gsell) {
		this.gsell = gsell;
	}
	public double getGcost() {
		return gcost;
	}
	public void setGcost(double gcost) {
		this.gcost = gcost;
	}
	public int getGcount() {
		return gcount;
	}
	public void setGcount(int gcount) {
		this.gcount = gcount;
	}
	public String getGtname() {
		return gtname;
	}
	public void setGtname(String gtname) {
		this.gtname = gtname;
	}
	public Goods(int gid, String gname, double gsell, double gcost, int gcount,
			String gtname) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.gsell = gsell;
		this.gcost = gcost;
		this.gcount = gcount;
		this.gtname = gtname;
	}
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", gname=" + gname + ", gsell=" + gsell
				+ ", gcost=" + gcost + ", gcount=" + gcount + ", gtname="
				+ gtname + "]";
	}
	

}
