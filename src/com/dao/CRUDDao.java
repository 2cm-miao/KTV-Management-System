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
	 * ��ѯ�����û�
	 */
	public List<T> display(String sql) {
		con = super.create();
		List<T> list = new ArrayList<T>();
		if (con != null) {
			try {
				ps=con.prepareStatement(sql);
				rs = ps.executeQuery();
				// ��ѯ�����û������浽������
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
	 * �󶨲���
	 */
	private void bind(Object[] obj){
			try {
				for (int i = 0; i < obj.length && obj!=null; i++) {
					ps.setObject(i+1, obj[i]);//�ʺŸ�����1��ʼ
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * ����һ��������
	 */
	private T toBean(){
		T t=null;
		try {
			t=c.newInstance();//ͨ������õ�����������Ӧ�ľ�����
			Field [] field=c.getDeclaredFields();//ͨ������õ����������ֶ�
			for (Field f : field) {
				Object value=rs.getObject(f.getName());//�����ֶ�����ȡ���ݿ�Ľ�����е�����
				f.setAccessible(true);//����˽���ֶ���ҪȨ��
				f.set(t, value);//��ֵװ���ֶ���
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
