package com.domain;

import java.util.Date;

public class Reserve {
	private String rname;
	private String rtel;
	private String btname;
	private int rbid;
	private String rstate;
	private Date rtime;
	private Date rdate;
	private String rbz;
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRtel() {
		return rtel;
	}
	public void setRtel(String rtel) {
		this.rtel = rtel;
	}
	public String getBtname() {
		return btname;
	}
	public void setBtname(String btname) {
		this.btname = btname;
	}
	public int getRbid() {
		return rbid;
	}
	public void setRbid(int rbid) {
		this.rbid = rbid;
	}
	public String getRstate() {
		return rstate;
	}
	public void setRstate(String rstate) {
		this.rstate = rstate;
	}
	public Date getRtime() {
		return rtime;
	}
	public void setRtime(Date rtime) {
		this.rtime = rtime;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getRbz() {
		return rbz;
	}
	public void setRbz(String rbz) {
		this.rbz = rbz;
	}
	public Reserve() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reserve(String rname, String rtel, String btname, int rbid,
			String rstate, Date rtime, Date rdate, String rbz) {
		super();
		this.rname = rname;
		this.rtel = rtel;
		this.btname = btname;
		this.rbid = rbid;
		this.rstate = rstate;
		this.rtime = rtime;
		this.rdate = rdate;
		this.rbz = rbz;
	}
	@Override
	public String toString() {
		return "Reserve [rname=" + rname + ", rtel=" + rtel + ", btname="
				+ btname + ", rbid=" + rbid + ", rstate=" + rstate + ", rtime="
				+ rtime + ", rdate=" + rdate + ", rbz=" + rbz + "]";
	}
	
}
