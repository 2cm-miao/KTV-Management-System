package com.dao;

import java.util.Date;
import java.util.List;

import com.domain.Vip;
import com.domain.Waiters;

public class VipDao {
	CRUDDao<Vip> crud=null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}
	
	public List<Vip> ShowAll()
	{
		crud=new CRUDDao<Vip>(Vip.class);
		String sql="select * from vip";
		List<Vip> list=crud.display(sql);
		return list;
	}
	
	//É¾³ý
	public int delete(int id){
		crud=new CRUDDao<Vip>(Vip.class);
		String sql="delete from vip where vipid=?";
		int count=crud.zsg(sql, id);
		return count;
	}
	
	//Ìí¼Ó
	public int add(int id,String name,String sex,String tel,int jf,Date time,String bz){
		crud=new CRUDDao<Vip>(Vip.class);
		String sql="insert into vip(vipid,vipname,vipsex,viptel,vipjf,viptime,vipbz) values(?,?,?,?,?,?,?)";
		int count=crud.zsg(sql, id,name,sex,tel,jf,time,bz);
		return count;
	}
	
	//ÐÞ¸Ä
	public int update(int id,String name,String sex,String tel,int jf,Date time,String bz)
	{
		crud=new CRUDDao<Vip>(Vip.class);
		String sql="UPDATE vip set vipid=?,vipname=?,vipsex=?,viptel=?,vipjf=?,viptime=?,vipbz=?";
		int count=crud.zsg(sql, id,name,sex,tel,jf,time,bz);
		return count;
	}

}
