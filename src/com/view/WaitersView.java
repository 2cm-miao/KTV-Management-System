package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dao.WaiterDao;
import com.service.WaitersService;

public class WaitersView extends JDialog implements ActionListener {
	JTextField field=null;
	Object [][] data=null;
	Object [] columnNames=null;
	DefaultTableModel model=null;
	JTable table=null;
	
	public static void main(String[] args) {
		new WaitersView();
	}
	public WaitersView(){
		this.setTitle("����������");
		this.setLayout(null);
		this.setSize(600, 450);
		this.setLocationRelativeTo(null);
		init();
		this.setModal(true);
		this.setVisible(true);
	}
	public void init() {
		JLabel label=new JLabel("��������� / ����");
		label.setBounds(40, 30, 100, 25);
		this.add(label);
		
		field=new JTextField();
		field.setBounds(140, 30, 120, 25);
		this.add(field);
		
		String [] str=new String[]{"��ѯ","ˢ��","���","ɾ��"};
		for (int i = 0; i < str.length; i++) {
			JButton btn=new JButton(str[i]);
			btn.setBounds(270+70*i, 30, 60, 25);
			this.add(btn);
			btn.addActionListener(this);
		}	
		//���
		data=new WaitersService().showAll();
		columnNames=new Object[]{"���","����","�Ա�","���֤����","�绰����","����״̬"};
		model=new DefaultTableModel(data,columnNames);
		table=new JTable(model){

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;//��񲻿ɱ༭
			}
			
		};
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getClickCount()==2){
					update(e);
				}
			}
			
		});
		JScrollPane jsp=new JScrollPane(table);
		jsp.setBounds(40, 70, 500, 300);
		//jsp.setViewportView(table);
		this.add(jsp);
		
		JButton free=new JButton("��ʾ���з���Ա");
		free.setBounds(230, 375, 130, 30);
		this.add(free);
		free.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "��ѯ":
			Object [][] find=new WaitersService().find(field.getText(),data);
			model.setDataVector(find, columnNames);
			break;
		case "ˢ��":
			field.setText("");
			data=new WaitersService().showAll();
			model.setDataVector(data, columnNames);
			break;
		case "���":
			new WaitersChangeView(null,null,null,null,null);
			break;
		case "ɾ��":
			int selected=table.getSelectedRow();
			if(selected!=-1){
				int count=JOptionPane.showConfirmDialog(null, "ȷ��ɾ�����"+data[selected][0]+"����Ϣ��", "ɾ��", JOptionPane.OK_OPTION);
				if(count==JOptionPane.YES_OPTION){
					new WaiterDao().delete(Integer.parseInt(data[selected][0].toString()));
					model.removeRow(selected);
					JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
				}else{
					return;
				}
			}
			break;
		case "��ʾ���з���Ա":
			Object [][] free=new WaitersService().free(data);
			model.setDataVector(free, columnNames);
			break;

		default:
			break;
		}
	}
	
	/**
	 * ˫������޸���Ϣ
	 */
	public void update(MouseEvent e){
		table=(JTable) e.getComponent();
		int row=table.getSelectedRow();
		//int column=table.getSelectedColumn();
		model=(DefaultTableModel) table.getModel();
		String id=model.getValueAt(row, 0)+"";
		String name=model.getValueAt(row, 1)+"";
		String sex=model.getValueAt(row, 2)+"";
		String idnum=model.getValueAt(row, 3)+"";
		String tel=model.getValueAt(row, 4)+"";
		new WaitersChangeView(id,name,sex,idnum,tel);
	}

}
