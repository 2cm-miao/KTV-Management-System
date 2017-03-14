package com.dao;

import java.util.List;

import com.domain.Reserve;

public class ReserveDao {
	/**
	 * ����������Ϣ
	 * @return
	 */
	public List<Reserve> showAll(){
		CRUDDao<Reserve> crud=new CRUDDao<Reserve>(Reserve.class);
		String sql="SELECT rname,rtel,btname,reserve.rbid,rstate,rtime,rdate,rbz FROM `reserve` LEFT JOIN boxs ON reserve.rbid=boxs.bid LEFT JOIN boxs_type ON boxs.btid=boxs_type.btid";
		List<Reserve> list=crud.display(sql);
		return list;
	}
	/**
	 * �����µ�Ԥ����Ϣ
	 */
	public int insert(String name,String tel,String bid,String time,String bz){
		CRUDDao<Reserve> crud=new CRUDDao<Reserve>(Reserve.class);
		String sql="insert into reserve(rname,rtel,rbid,rtime,rbz) values(?,?,?,?,?)";
		int count=crud.zsg(sql, name,tel,bid,time,bz);
		return count;
	}
	/**
	 * �޸���Ϣ
	 */
	public int update(String tel,String bid,String time,String bz,String name){
		CRUDDao<Reserve> crud=new CRUDDao<Reserve>(Reserve.class);
		String sql="update reserve set rtel=?,rbid=?,rtime=?,rbz=? where rname=?";
		int count=crud.zsg(sql, tel,bid,time,bz,name);
		return count;
	}
	//ɾ��
		public int delete(String name)
		{
			CRUDDao<Reserve> crud=new CRUDDao<Reserve>(Reserve.class);
			String sql="delete from reserve where rname=?";
			int count=crud.zsg(sql, name);
			return count;
		}
	

}
