package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dao.VipDao;
import com.service.VipService;

public class VipView extends JDialog implements ActionListener{
	
	final JFrame fra=new JFrame();
	final JLabel Jid=new JLabel();
	JTextField Jtext=null;
	DefaultTableModel model=null;
	Object [] columnNames=null;
	JTable table=null;
	Object [][] data=null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new VipView();
	}
	
	public VipView()
	{
		fra.setTitle("顾客管理");
		fra.setBounds(400, 150, 600, 450);
		this.setModal(true);
		fra.setLayout(null);
		
		chu();
		
		fra.setVisible(true);
	}
	
	public void chu()
	{
		Jid.setText("顾客姓名 / 编号");
		Jid.setBounds(40, 30, 100, 25);
		fra.add(Jid);
		
		Jtext=new JTextField();
		Jtext.setBounds(130, 30, 100, 25);
		fra.add(Jtext);
		
		String [] str=new String[]{"查询","刷新","添加","删除"};
		for (int i = 0; i < str.length; i++) {
			JButton btn=new JButton(str[i]);
			btn.setBounds(270+70*i, 30, 60, 25);
			fra.add(btn);
			btn.addActionListener(this);
		}
		
		//表格
		data=new VipService().showAll();
		columnNames=new Object[]{"编号","姓名","联系方式","积分","注册日期","备注"};
		model=new DefaultTableModel(data,columnNames);
		table=new JTable(model){

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;//表格不可编辑
			}
			
		};
		
		JScrollPane jsp=new JScrollPane(table);
		jsp.setBounds(40, 70, 500, 300);
		fra.add(jsp);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		case "查询":
			Object [][] find=new VipService().find(Jtext.getText(),data);
			model.setDataVector(find, columnNames);
			break;
		case "刷新":
			Jtext.setText("");
			data=new VipService().showAll();
			model.setDataVector(data, columnNames);
			break;
		case "添加":
			new VIPCangeView(null,null,null,null,null, null, null);
			break;
		case "删除":
			int selected=table.getSelectedRow();
			if(selected!=-1){
				int count=JOptionPane.showConfirmDialog(null, "确认删除编号"+data[selected][0]+"的信息吗？", "删除", JOptionPane.OK_OPTION);
				if(count==JOptionPane.YES_OPTION){
					new VipDao().delete(Integer.parseInt(data[selected][0].toString()));
					model.removeRow(selected);
					JOptionPane.showMessageDialog(null, "删除成功！");
				}else{
					return;
				}
			}
			break;
		}
	}

}
