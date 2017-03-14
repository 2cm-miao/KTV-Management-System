package com.service;

import java.lang.reflect.Field;
import java.util.List;

import com.dao.AccountDao;
import com.domain.Account;

public class AccountService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Object [][] showAll(){
		List<Account> list=new AccountDao().showAll();
		Account account=new Account();
		Field [] fields=account.getClass().getDeclaredFields();
		Object [][] data=new Object[list.size()][fields.length];
		for (int i = 0; i < data.length; i++) {
			account=list.get(i);
			Object [] ob=new Object[fields.length];
			for (int j = 0; j < data[i].length; j++) {
				fields[j].setAccessible(true);
				try {
					ob[j]=fields[j].get(account);
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
