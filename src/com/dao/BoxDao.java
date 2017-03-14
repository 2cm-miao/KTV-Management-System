package com.dao;

import java.util.List;

import com.domain.Boxs;
import com.domain.BoxsType;

public class BoxDao {

	/**
	 * ��ѯ���а�����Ϣ
	 * @return
	 */
	public List<Boxs> showAll(){
		CRUDDao<Boxs> crud=new CRUDDao<Boxs>(Boxs.class);
		String sql="select bid,boxs.btid,btname,btmin,bstate,wid from boxs LEFT JOIN boxs_type on boxs.btid=boxs_type.btid;";
		List<Boxs> list=crud.display(sql);
		return list;
	}
	/**
	 * ��ѯ���а���������Ϣ
	 */
	public List<BoxsType> showType(){
		CRUDDao<BoxsType> crud=new CRUDDao<BoxsType>(BoxsType.class);
		String sql="select * from boxs_type";
		List<BoxsType> list=crud.display(sql);
		return list;
	}
	/**
	 * �ı����״̬
	 */
	public int update(String bstate,String bid){
		CRUDDao<Boxs> crud=new CRUDDao<Boxs>(Boxs.class);
		String sql="update boxs set bstate=? where bid=?";
		int count=crud.zsg(sql, bstate,bid);
		
		return count;
	}
	/**
	 * ����ѡ�������
	 */
	public int updateWid(int wid,String bid){
		CRUDDao<Boxs> crud=new CRUDDao<Boxs>(Boxs.class);
		String sql="update boxs set wid=? where bid=?";
		int count=crud.zsg(sql, wid,bid);
		
		return count;
	}

}
