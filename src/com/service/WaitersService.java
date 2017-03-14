package com.service;

import java.lang.reflect.Field;
import java.util.List;

import com.dao.WaiterDao;
import com.domain.Waiters;

public class WaitersService{
	/**
	 * 遍历的全部信息装入表格作为数据
	 */
	public Object [][] showAll(){
		List<Waiters> list=new WaiterDao().showAll();
		Waiters waiter=new Waiters();
		Field [] fields=waiter.getClass().getDeclaredFields();
		
		Object [][] data=new Object[list.size()][fields.length];
		for (int i = 0; i < data.length; i++) {
			waiter=list.get(i);
			Object [] ob=new Object[fields.length];
			for (int j = 0; j < data[i].length; j++) {
				fields[j].setAccessible(true);
				try {
					ob[j]=fields[j].get(waiter);
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
	 * 查询指定编号或姓名
	 * @param text
	 */
	public Object [][] find(String text,Object [][] data){
		Object [][] find=new Object[data.length][data[0].length];
		int flag=0;
		if(text!=""){
			for (int i = 0; i < data.length; i++) {
				if(text.equals(data[i][0].toString())||text.equals(data[i][1].toString())){
					find[flag]=data[i];
					flag++;
				}
			}
		}
		return find;
		
	}
	/**
	 * 显示所有空闲服务生
	 */
	public Object [][] free(Object [][] data){
		Object [][] free=new Object[data.length][data[0].length];
		int flag=0;
		for (int i = 0; i < free.length; i++) {
			if("空闲".equals(data[i][5])){
				free[flag]=data[i];
				flag++;
			}
		}
		return free;
	}

}
