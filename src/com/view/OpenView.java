package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.dao.BoxDao;
import com.dao.WaiterDao;
import com.inter.IBoxChange;
import com.service.BillService;
import com.service.WaitersService;

public class OpenView extends JDialog{
	JButton btn1=new JButton("ȷ��");
	JButton btn2=new JButton("ȡ��");
	JButton btn3=new JButton("ͣ��");
	JComboBox<String> cb=null;
	String id;
	String wname;
	IBoxChange bc;

	public OpenView(String type,String id,String min,IBoxChange bc){
		this.id=id;
		this.bc=bc;
		this.setTitle("��������");
		this.setLayout(null);
		this.setSize(280, 350);
		this.setLocationRelativeTo(null);
		init(type,min);
		this.setModal(true);
		this.setVisible(true);
	}
	public void init(String type,String min) {
		String [] str=new String []{"�������ͣ�","�����ţ�","������ѣ�","��������"};
		for (int i = 0; i < str.length; i++) {
			JLabel label=new JLabel(str[i]);
			label.setBounds(50, 20+50*i, 70, 30);
			this.add(label);
		}
		JLabel ltype=new JLabel(type);
		ltype.setBounds(120, 20, 60, 30);
		this.add(ltype);
		JLabel lbid=new JLabel(id);
		lbid.setBounds(120, 70, 60, 30);
		this.add(lbid);
		JLabel lmin=new JLabel(min);
		lmin.setBounds(120, 120, 60, 30);
		this.add(lmin);
		
		WaitersService ws=new WaitersService();
		Object [][] data=ws.showAll();
		final Object [][] free=ws.free(data);
		String [] fname=new String[free.length];
		for (int i = 0; i < free.length; i++) {
			fname[i]=free[i][1]+"";
		}
		cb=new JComboBox<String>(fname);
		cb.setBounds(120, 170, 100, 30);
		this.add(cb);
		
		
		btn1.setBounds(40, 250, 60, 30);
		this.add(btn1);
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bc.ChangeColor(btn1.getText());
				wname=cb.getSelectedItem()+"";
				int wid=0;
				for (int i = 0; i < free.length; i++) {
					if(wname.equals(free[i][1])){
						wid=(int) free[i][0];
					}
				}
				new BoxDao().updateWid(wid, id);//���İ��������
				new WaiterDao().updateState("æµ", wid);
				new BillService().moneyInit(Integer.parseInt(id));//����ʱ�Զ����������õ������嵥
				OpenView.this.dispose();
			}
		});
		
		btn2.setBounds(160, 250, 60, 30);
		this.add(btn2);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bc.ChangeColor(btn2.getText());
				OpenView.this.dispose();
			}
		});
		btn3.setBounds(100, 210, 60, 30);
		this.add(btn3);
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int count=JOptionPane.showConfirmDialog(null, "ȷ��ͣ�øð��᣿", "ͣ��ȷ��", JOptionPane.OK_CANCEL_OPTION);
				if(count==JOptionPane.OK_OPTION){
					bc.ChangeColor(btn3.getText());
					OpenView.this.dispose();
				}
			}
		});
	}
}
