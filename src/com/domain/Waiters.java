package com.domain;

public class Waiters {
	private int wid;//服务生编号
	private String wname;//服务生姓名
	private String wsex;//服务生性别
	private String widnum;//服务生身份证号
	private String wtel;//服务生联系方式
	private String wstate;//服务生状态
	
	public String getWstate() {
		return wstate;
	}
	public void setWstate(String wstate) {
		this.wstate = wstate;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getWsex() {
		return wsex;
	}
	public void setWsex(String wsex) {
		this.wsex = wsex;
	}
	public String getWidnum() {
		return widnum;
	}
	public void setWidnum(String widnum) {
		this.widnum = widnum;
	}
	public String getWtel() {
		return wtel;
	}
	public void setWtel(String wtel) {
		this.wtel = wtel;
	}
	public Waiters() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Waiters(int wid, String wname, String wsex, String widnum,
			String wtel, String wstate) {
		super();
		this.wid = wid;
		this.wname = wname;
		this.wsex = wsex;
		this.widnum = widnum;
		this.wtel = wtel;
		this.wstate = wstate;
	}
	@Override
	public String toString() {
		return "Waiters [wid=" + wid + ", wname=" + wname + ", wsex=" + wsex
				+ ", widnum=" + widnum + ", wtel=" + wtel + ", wstate="
				+ wstate + "]";
	}
	

}
