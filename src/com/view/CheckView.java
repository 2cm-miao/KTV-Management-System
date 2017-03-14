package com.view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.service.AccountService;

public class CheckView extends JDialog{
	
	final JFrame fra=new JFrame();
	Object [] columnNames=null;
	Object [][] data=null;
	DefaultTableModel model=null;
	JTable table=null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CheckView();
	}
	
	public CheckView()
	{
		fra.setTitle("历史账单");
		fra.setBounds(400, 150, 600, 450);
		this.setModal(true);
		fra.setLayout(null);
		
		chu();
		
		fra.setVisible(true);
	}
	
	public void chu()
	{
		data=new AccountService().showAll();
		columnNames=new Object[]{"编号","包厢编号","消费价格","结账时间"};
		model=new DefaultTableModel(data,columnNames);
		table=new JTable(model){

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;//表格不可编辑
			}
			
		};
		
		JScrollPane jsp=new JScrollPane(table);
		jsp.setBounds(40, 30, 500, 350);
		fra.add(jsp);
	}

}
