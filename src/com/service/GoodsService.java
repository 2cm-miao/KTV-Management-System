package com.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.dao.GoodsDao;
import com.domain.Goods;
import com.domain.GoodsType;
import com.domain.Waiters;

public class GoodsService {

	public static void main(String[] args) {
		new GoodsService().showGoods("商品类");
	}
	/**
	 * 获取所有商品类型
	 * @return
	 */
	public String [] showType(){
		List<GoodsType> list=new GoodsDao().showType();
		String [] type=new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			type[i]=list.get(i).getGtname();
		}
		return type;
	}
	/**
	 * 获取某商品类型的全部商品
	 */
	public Object [][] showGoods(String typeName){
		List<Goods> list=new GoodsDao().showAll();
		List<Goods> good=new ArrayList<>();
		String type;
		for (int i = 0; i < list.size(); i++) {
			type=list.get(i).getGtname();
			if(type.equals(typeName)){
				good.add(list.get(i));
			}
		}
		Goods goods=new Goods();
		Field [] fields=goods.getClass().getDeclaredFields();
		Object [][] data=new Object[good.size()][fields.length-1];
		int flag=0;
		for (int i = 0; i < data.length; i++) {
			goods=good.get(i);
			Object [] ob=new Object[fields.length-1];
			for (int j = 0; j < fields.length; j++) {
				fields[j].setAccessible(true);
				if(!"gcost".equals(fields[j].getName())){
					try {
						ob[flag]=fields[j].get(goods);
						flag++;
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} 
				}
			}
			flag=0;
			data[i]=ob;
		}
		return data;
	}
	/**
	 * 根据商品编号查找商品
	 */

}
