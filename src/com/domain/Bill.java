package com.domain;

public class Bill {
	private int qbid;
	private String qgname;
	private double qgsell;
	private int qcount;
	public int getQbid() {
		return qbid;
	}
	public void setQbid(int qbid) {
		this.qbid = qbid;
	}
	public String getQgname() {
		return qgname;
	}
	public void setQgname(String qgname) {
		this.qgname = qgname;
	}
	public double getQgsell() {
		return qgsell;
	}
	public void setQgsell(double qgsell) {
		this.qgsell = qgsell;
	}
	public int getQcount() {
		return qcount;
	}
	public void setQcount(int qcount) {
		this.qcount = qcount;
	}
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bill(int qbid, String qgname, double qgsell, int qcount) {
		super();
		this.qbid = qbid;
		this.qgname = qgname;
		this.qgsell = qgsell;
		this.qcount = qcount;
	}
	@Override
	public String toString() {
		return "Bill [qbid=" + qbid + ", qgname=" + qgname + ", qgsell="
				+ qgsell + ", qcount=" + qcount + "]";
	}
	


}
