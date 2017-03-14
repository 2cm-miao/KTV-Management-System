package com.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CRUDDao<T> extends BaseJDBC {
	private static Connection con = null;
	private static PreparedStatement ps=null;
	private static ResultSet rs = null;
	private Class<T> c;
	
	public CRUDDao(){
		
	}
	public CRUDDao(Class<T> c){
		this.c=c;
	}
	
	public int zsg(String sql,Object...obj){
		con=super.create();
		int count=0;
		try {
			ps=con.prepareStatement(sql);
			bind(obj);
			count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.close(con, ps, null);
		}
		return count;
	}

	/**
	 * 查询所有用户
	 */
	public List<T> display(String sql) {
		con = super.create();
		List<T> list = new ArrayList<T>();
		if (con != null) {
			try {
				ps=con.prepareStatement(sql);
				rs = ps.executeQuery();
				// 查询所有用户并保存到集合中
				while (rs.next()) {
					list.add(toBean());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				super.close(con, ps, rs);
			}
		}
		return list;
	}
	/**
	 * 绑定参数
	 */
	private void bind(Object[] obj){
			try {
				for (int i = 0; i < obj.length && obj!=null; i++) {
					ps.setObject(i+1, obj[i]);//问号个数从1开始
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * 创建一个泛型类
	 */
	private T toBean(){
		T t=null;
		try {
			t=c.newInstance();//通过反射得到这个泛型类对应的具体类
			Field [] field=c.getDeclaredFields();//通过反射得到此类所有字段
			for (Field f : field) {
				Object value=rs.getObject(f.getName());//根据字段名获取数据库的结果集中的数据
				f.setAccessible(true);//访问私有字段需要权限
				f.set(t, value);//将值装到字段里
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
}
