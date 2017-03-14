package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.BoxDao;
import com.domain.Boxs;
import com.domain.BoxsType;

public class BoxService {
	
	/**
	 * ��ȡ����ţ��������ͣ��������
	 * @param text
	 * @return
	 */
	public Object [] info(String text){
		List<Boxs> list=new BoxDao().showAll();
		Object [] info=new Object[3];
		for (int i = 0; i < list.size(); i++) {
			String bid=list.get(i).getBid()+"";
			if(text.equals(bid)){
				info[0]=bid;
				info[1]=list.get(i).getBtname();
				info[2]=list.get(i).getBtmin();
			}
		}
		return info;
	}
	/**
	 * ��ȡ����״̬
	 */
	public String state(String text){
		List<Boxs> list=new BoxDao().showAll();
		String state=null;
		for (int i = 0; i < list.size(); i++) {
			String bid=list.get(i).getBid()+"";
			if(text.equals(bid)){
				state=list.get(i).getBstate();
			}
		}
		return state;
	}
	/**
	 * ��ȡ���а�������
	 */
	public String [] typeAll(){
		List<BoxsType> list=new BoxDao().showType();
		String [] type=new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			String btname=list.get(i).getBtname();
			type[i]=btname;
		}
		return type;
	}
	/**
	 * ���ݰ������ͻ�ȡ������
	 */
	public String [] idAll(String type){
		List<Boxs> list=new BoxDao().showAll();
		List<String> l=new ArrayList<>();
		for (int i = 0; i <list.size(); i++) {
			String btname=list.get(i).getBtname();
			if(type.equals(btname)){
				l.add(list.get(i).getBid()+"");
			}
		}
		String [] idAll=new String[l.size()];
		for (int i = 0; i < idAll.length; i++) {
			idAll[i]=l.get(i);
		}
		return idAll;
	}
	/**
	 * ���ݰ���Ż�ȡ������ID
	 */
	public int findID(int bid){
		int wid=0;
		List<Boxs> list=new BoxDao().showAll();
		for (int i = 0; i < list.size(); i++) {
			if(bid==list.get(i).getBid()){
				wid=list.get(i).getWid();
			}
		}
		return wid;
	}
}
