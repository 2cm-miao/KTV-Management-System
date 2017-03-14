package com.dao;

import java.util.List;

import com.domain.Account;

public class AccountDao {

	public static void main(String[] args) {

	}
	public int insert(int abid,double amoney){
		CRUDDao<Account> crud=new CRUDDao<Account>(Account.class);
		String sql="insert into account(abid,amoney) values(?,?)";
		int count=crud.zsg(sql,abid,amoney);
		return count;
	}
	public List<Account> showAll(){
		CRUDDao<Account> crud=new CRUDDao<Account>(Account.class);
		String sql="select *from account";
		List<Account> list=crud.display(sql);
		return list;
	}

}
