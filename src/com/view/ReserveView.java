package com.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dao.BoxDao;
import com.dao.ReserveDao;
import com.dao.VipDao;
import com.inter.IBoxChange;
import com.service.BoxService;
import com.service.ReserveService;

public class ReserveView extends JDialog implements ActionListener {
	JTextField field=null;
	Object [][] data=null;
	Object [] columnNames=null;
	DefaultTableModel model=null;
	JTable table=null;
	IBoxChange ic;
	public static void main(String[] args) {
		new ReserveView();
	}
	public ReserveView(){
		this.setTitle("Ԥ������");
		this.setSize(750, 450);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		init();
		this.setModal(true);
		this.setVisible(true);
	}

	public void init(){
		JLabel label=new JLabel("�����/��������");
		label.setBounds(60, 30, 100, 25);
		this.add(label);
		
		field=new JTextField();
		field.setBounds(160, 30, 120, 25);
		this.add(field);
		
		String [] str=new String[]{"��ѯ","ˢ��","���","ɾ��"};
		for (int i = 0; i < str.length; i++) {
			JButton btn=new JButton(str[i]);
			btn.setBounds(290+100*i, 30, 80, 25);
			this.add(btn);
			btn.addActionListener(this);
		}	
		//���
		data=new ReserveService().showAll();
		columnNames=new Object[]{"��������","��ϵ�绰","��������","������","Ԥ��״̬","��ֹʱ��","Ԥ��ʱ��","��ע"};
		model=new DefaultTableModel(data,columnNames);
		table=new JTable(model){

			@Override
			public boolean isCellEditable(int row, int column) {
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
		jsp.setBounds(40, 70, 660, 300);
		this.add(jsp);
		
		JButton open=new JButton("����");
		open.setBounds(350, 380, 80, 30);
		this.add(open);
		open.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "��ѯ":
			Object [][] find=new ReserveService().find(field.getText(), data);
			model.setDataVector(find, columnNames);
			break;
		case "ˢ��":
			field.setText("");
			data=new ReserveService().showAll();
			model.setDataVector(data, columnNames);
			break;
		case "���":
			new ReserveChangeView(null,null,null,null,null,null);
			
			break;
		case "ɾ��":
			int selected=table.getSelectedRow();
			if(selected!=-1){
				int count=JOptionPane.showConfirmDialog(null, "ȷ��ɾ�����"+data[selected][0]+"����Ϣ��", "ɾ��", JOptionPane.OK_OPTION);
				if(count==JOptionPane.YES_OPTION){
					new ReserveDao().delete(data[selected][0].toString());
					model.removeRow(selected);
					JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
				}else{
					return;
				}
			}
			break;
		case "����":
			int row=table.getSelectedRow();
			final String text=model.getValueAt(row, 3)+"";
			BoxService bs=new BoxService();
			Object [] info=bs.info(text);
			String type=info[1]+"";
			String min=info[2]+"";
			new OpenView(type,text,min,ic);
			break;
		default:
			break;
		}
		
	}
	public void update(MouseEvent e){
		table=(JTable) e.getComponent();
		int row=table.getSelectedRow();
		int column=table.getSelectedColumn();
		model=(DefaultTableModel) table.getModel();
		String name=model.getValueAt(row, 0)+"";
		String tel=model.getValueAt(row, 1)+"";
		String type=model.getValueAt(row, 2)+"";
		String id=model.getValueAt(row, 3)+"";
		String time=model.getValueAt(row, 5)+"";
		String bz=model.getValueAt(row, 7)+"";
		new ReserveChangeView(name,tel,type,id,time,bz);
	}
}
