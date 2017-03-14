package com.dao;

import java.util.List;

import com.domain.Bill;

public class BillDao {
	CRUDDao<Bill> crud=null;
	public static void main(String[] args) {
		//new BillDao().showAll();
	}
	public List<Bill> showAll(){
		crud=new CRUDDao<Bill>(Bill.class);
		String sql="select * from qingdan";
		List<Bill> list=crud.display(sql);
		return list;
		
	}
	public int insert(int qbid,String qgname,double qgsell,int qcount){
		crud=new CRUDDao<Bill>(Bill.class);
		String sql="insert into qingdan values(?,?,?,?)";
		int count=crud.zsg(sql,qbid, qgname,qgsell,qcount);
		return count;
	}
	/**
	 * 删除结账包厢清单
	 * @param qbid
	 * @return
	 */
	public int delete(int qbid){
		crud=new CRUDDao<Bill>(Bill.class);
		String sql="delete from qingdan where qbid=?";
		int count=crud.zsg(sql, qbid);
		return count;
	}
	/**
	 * 删除某一商品
	 */
	public int deleteGood(String name){
		crud=new CRUDDao<Bill>(Bill.class);
		String sql="delete from qingdan where qgname=?";
		int count=crud.zsg(sql, name);
		return count;
	}
	

}
