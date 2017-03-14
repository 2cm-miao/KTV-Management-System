package com.domain;

import java.util.Date;

public class Vip {
	private int vipid;
	private String vipname;
	private String vipsex;
	private String viptel;
	private int vipjf;
	private Date viptime;
	private String vipbz;
	public int getVipid() {
		return vipid;
	}
	public void setVipid(int vipid) {
		this.vipid = vipid;
	}
	public String getVipname() {
		return vipname;
	}
	public void setVipname(String vipname) {
		this.vipname = vipname;
	}
	public String getVipsex() {
		return vipsex;
	}
	public void setVipsex(String vipsex) {
		this.vipsex = vipsex;
	}
	public String getViptel() {
		return viptel;
	}
	public void setViptel(String viptel) {
		this.viptel = viptel;
	}
	public int getVipjf() {
		return vipjf;
	}
	public void setVipjf(int vipjf) {
		this.vipjf = vipjf;
	}
	public Date getViptime() {
		return viptime;
	}
	public void setViptime(Date viptime) {
		this.viptime = viptime;
	}
	public String getVipbz() {
		return vipbz;
	}
	public void setVipbz(String vipbz) {
		this.vipbz = vipbz;
	}
	public Vip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vip(int vipid, String vipname, String vipsex, String viptel,
			int vipjf, Date viptime, String vipbz) {
		super();
		this.vipid = vipid;
		this.vipname = vipname;
		this.vipsex = vipsex;
		this.viptel = viptel;
		this.vipjf = vipjf;
		this.viptime = viptime;
		this.vipbz = vipbz;
	}
	@Override
	public String toString() {
		return "Vip [vipid=" + vipid + ", vipname=" + vipname + ", vipsex="
				+ vipsex + ", viptel=" + viptel + ", vipjf=" + vipjf
				+ ", viptime=" + viptime + ", vipbz=" + vipbz + "]";
	}
	
	
}
