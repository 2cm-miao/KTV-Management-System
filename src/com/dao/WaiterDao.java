package com.dao;

import java.util.List;

import com.domain.Waiters;

public class WaiterDao {

	CRUDDao<Waiters> crud=null;
	public static void main(String[] args) {
		new WaiterDao().showAll();

	}
	/**
	 * �������з�������Ϣ
	 * @return
	 */
	public List<Waiters> showAll(){
		crud=new CRUDDao<Waiters>(Waiters.class);
		String sql="select * from waiters";
		List<Waiters> list=crud.display(sql);
		return list;
	}
	
	/**
	 * ɾ����������Ϣ
	 */
	public int delete(int id){
		crud=new CRUDDao<Waiters>(Waiters.class);
		String sql="delete from waiters where wid=?";
		int count=crud.zsg(sql, id);
		return count;
	}
	/**
	 * ¼���������Ϣ
	 */
	public int add(String id,String name,String sex,String idnum,String tel){
		crud=new CRUDDao<Waiters>(Waiters.class);
		String sql="insert into waiters(wid,wname,wsex,widnum,wtel) values(?,?,?,?,?)";
		int count=crud.zsg(sql, id,name,sex,idnum,tel);
		return count;
	}
	/**
	 * �޸ķ�������Ϣ
	 */
	public int update(String id,String name,String sex,String idnum,String tel){
		crud=new CRUDDao<Waiters>(Waiters.class);
		String sql="UPDATE waiters set wname=?,wsex=?,widnum=?,wtel=? WHERE wid=?";
		int count=crud.zsg(sql, name,sex,idnum,tel,id);
		return count;
	}
	/**
	 * ���ķ���״̬
	 */
	public int updateState(String wstate,int wid){
		crud=new CRUDDao<Waiters>(Waiters.class);
		String sql="UPDATE waiters set wstate=? WHERE wid=?";
		int count=crud.zsg(sql, wstate,wid);
		return count;
	}

}
