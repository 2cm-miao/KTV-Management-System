package com.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dao.BillDao;
import com.dao.BoxDao;
import com.domain.Bill;
import com.domain.Boxs;
import com.domain.BoxsType;

public class BillService {

	public static void main(String[] args) {
		//new BillService().moneyInit(2006);
	}
	/**
	 * 获取包间名称和包间费用
	 * @param bid
	 * @return
	 */
	public Map<String,Double> insertInit(int bid){
		String btname=null;
		double btmoney=0;
		Map<String,Double> map=new LinkedHashMap<>();
		BoxDao bd=new BoxDao();
		List<Boxs> boxList=bd.showAll();
		List<BoxsType> typeList=bd.showType();
		for (int i = 0; i <boxList.size(); i++) {
			if(bid==boxList.get(i).getBid()){
				int btid=boxList.get(i).getBtid();
				for (int j = 0; j < typeList.size(); j++) {
					if(btid==typeList.get(j).getBtid()){
						btname=typeList.get(j).getBtname();
						btmoney=typeList.get(j).getBtmoney();
					}
				}
				
			}
		}
		map.put(btname, btmoney);
		return map;
	}
	/**
	 * 初始化消费清单
	 * @param bid
	 * @return
	 */
	public Object [] moneyInit(int bid){
		Map<String, Double> map=insertInit(bid);
		Object [] box=new Object[4];
		for (String key : map.keySet()) {
			//System.out.println(key+"\t"+map.get(key));
			box[0]=key;
			box[1]=map.get(key);
		}
		box[2]=1;
		box[3]="基本费用";
		new BillDao().insert(bid, box[0].toString(), Double.parseDouble(box[1].toString()), Integer.parseInt(box[2].toString()));
		return box;
	}
	/**
	 * 遍历指定包间清单
	 */
	public Object [][] showMoney(int bid){
		List<Bill> list=new BillDao().showAll();
		List<Bill> listData=new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if(bid==list.get(i).getQbid()){
				listData.add(list.get(i));
			}
		}
		Bill bill=new Bill();
		Field [] fields=bill.getClass().getDeclaredFields();
		Object [][] data=new Object[listData.size()][fields.length];
		for (int i = 0; i < data.length; i++) {
			bill=listData.get(i);
			Object [] ob=new Object[fields.length];
			for (int j = 0; j < data[i].length; j++) {
				fields[j].setAccessible(true);
				try {
					ob[j]=fields[j].get(bill);
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

}
