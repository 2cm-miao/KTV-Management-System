package com.service;

import java.lang.reflect.Field;
import java.util.List;

import com.dao.ReserveDao;
import com.domain.Reserve;

public class ReserveService {
	/**
	 * 遍历的全部信息装入表格作为数据
	 */
	public Object [][] showAll(){
		List<Reserve> list=new ReserveDao().showAll();
		Reserve reserve=new Reserve();
		Field [] fields=reserve.getClass().getDeclaredFields();
		
		Object [][] data=new Object[list.size()][fields.length];
		for (int i = 0; i < data.length; i++) {
			reserve=list.get(i);
			Object [] ob=new Object[fields.length];
			for (int j = 0; j < data[i].length; j++) {
				fields[j].setAccessible(true);
				try {
					ob[j]=fields[j].get(reserve);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			data[i]=ob;
		}
		return data;
	}
	/**
	 * 查询指定姓名或包厢
	 * @param text
	 */
	public Object [][] find(String text,Object [][] data){
		Object [][] find=new Object[data.length][data[0].length];
		int flag=0;
		if(text!=""){
			for (int i = 0; i < data.length; i++) {
				if(text.equals(data[i][0].toString())||text.equals(data[i][3].toString())){
					find[flag]=data[i];
					flag++;
				}
			}
		}
		return find;
		
	}
}
