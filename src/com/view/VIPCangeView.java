package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dao.VipDao;
import com.domain.Vip;
import com.service.VipService;

public class VIPCangeView extends JDialog implements ActionListener{
	JTextField fieldId=new JTextField();   //编号
	JTextField fieldName=new JTextField();  //姓名
	ButtonGroup bg=new ButtonGroup();
	JRadioButton rb1=new JRadioButton("男",true);
	JRadioButton rb2=new JRadioButton("女");
	JTextField fieldTel=new JTextField();
	JTextField fieldJF=new JTextField();
	JTextField fieldDate=new JTextField();
	JTextArea ta=new JTextArea();
	
	String oldid=null;

/*	public static void main(String[] args) {
		
		new VIPCangeView(null, null, null, null, null, null, null);
 
	}*/
	public VIPCangeView(String id,String name,String sex,String tel,String jf,Date time,String bz){
		oldid=id;
		this.setTitle("会员信息");
		this.setLayout(null);
		this.setSize(450, 450);
		this.setLocationRelativeTo(null);
		init();
		this.setModal(true);
		this.setVisible(true);
	}
	public void init() {
		String [] str=new String[]{"会员编号：","会员姓名：","会员性别：","会员电话：","当前积分：","登记时间：","备注："};
		for (int i = 0; i < str.length; i++) {
			JLabel label=new JLabel(str[i]);
			if(i%2==0){
				label.setBounds(30, 50+30*i, 70, 30);
				
			}else{
				label.setBounds(220, 50+30*(i-1), 70, 30);
				
			}
			this.add(label);
		}
		
		fieldId.setBounds(100, 50, 100, 30);   
		this.add(fieldId);
		
		fieldName.setBounds(290, 50, 100, 30);
		this.add(fieldName);
		
		//性别选项
		rb1.setBounds(100, 110, 50, 30);
		this.add(rb1);
		rb2.setBounds(160, 110, 50, 30);
		this.add(rb2);
		bg.add(rb1);
		bg.add(rb2);
		
		//电话
		fieldTel.setBounds(290, 110, 100, 30);
		this.add(fieldTel);
		
		//当前积分
		fieldJF.setBounds(100, 170, 100, 30);
		this.add(fieldJF);
		
		//登记时间
		fieldDate.setBounds(290, 170, 100, 30);
		this.add(fieldDate);
		
		//备注
		ta.setBounds(100, 230, 280, 120);
		this.add(ta);
		
		JButton btn1=new JButton("保存");
		btn1.setBounds(80, 360, 80, 30);
		this.add(btn1);
		btn1.addActionListener(this);
		JButton btn2=new JButton("取消");
		btn2.setBounds(280, 360, 80, 30);
		this.add(btn2);
		btn2.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if("保存".equals(e.getActionCommand())){
			save();
		}
		if("取消".equals(e.getActionCommand())){
			this.dispose();
		}
	}
	
	public void save()
	{
		String name=fieldName.getText();
		int id=Integer.parseInt(fieldId.getText());
		String tel=fieldTel.getText();
		Date da=java.sql.Date.valueOf(fieldDate.getText());
		int jf=Integer.parseInt(fieldJF.getText());
		String bz=ta.getText();
		String sex;
		if(rb1.isSelected())  sex="男";
		else  sex="女";
		
		VipDao vi=new VipDao();
		List<Vip> list=vi.ShowAll();
		int flag=0;
		if(oldid==null)
		{
			for(int i=0;i<list.size();i++)
			{
				String str=list.get(i).getVipid()+"";
				if(str.equals(id))
				{
					JOptionPane.showMessageDialog(null, "编号已存在，请重新输入！");
					flag=1;
					break;
				}
			}
			
			if(flag==0)
			{
				int count=vi.add(id, name, sex, tel, jf, da, bz);
				if(count==0)  JOptionPane.showMessageDialog(null,"录入失败！");
				else  JOptionPane.showMessageDialog(null,"录入成功");
			}
		}
		else
		{
			if(!oldid.equals(id))
			{
				JOptionPane.showMessageDialog(null,"编号不能修改！");
				fieldId.setText(oldid);
			}
			else
			{
				int count=vi.update(id, name, sex, tel, jf, da, bz);
				if(count==0)  JOptionPane.showMessageDialog(null,"修改失败！");
				else  JOptionPane.showMessageDialog(null,"修改成功！");
			}
		}
	}
}
