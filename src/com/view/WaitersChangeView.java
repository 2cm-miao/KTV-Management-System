package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.dao.WaiterDao;
import com.domain.Waiters;

public class WaitersChangeView extends JDialog implements ActionListener {
	String oldid=null;
	JTextField fieldId=new JTextField();
	JTextField fieldName=new JTextField();
	JRadioButton rb1=new JRadioButton("男");
	JRadioButton rb2=new JRadioButton("女");
	JTextField fieldNum=new JTextField();
	JTextField fieldTel=new JTextField();
	

	public WaitersChangeView(String id,String name,String sex,String idnum,String tel){
		this.setTitle("会员信息");
		this.setLayout(null);
		this.setSize(430, 450);
		this.setLocationRelativeTo(null);
		init(id,name,sex,idnum,tel);
		this.setModal(true);
		this.setVisible(true);
	}
	public void init(String id,String name,String sex,String idnum,String tel) {
		oldid=id;
		String [] str=new String[]{"编        号：","姓        名：","性         别：","身份证号：","联系电话："};
		for (int i = 0; i < str.length; i++) {
			JLabel label=new JLabel(str[i]);
			label.setBounds(80, 30+60*i, 70, 30);
			this.add(label);
		}
		
		
		fieldId.setBounds(150, 30, 150, 30);
		this.add(fieldId);
		fieldId.setText(id);
		
		fieldName.setBounds(150, 90, 150, 30);
		this.add(fieldName);
		fieldName.setText(name);
		
		ButtonGroup bg=new ButtonGroup();

		rb1.setBounds(150, 150, 50, 30);
		this.add(rb1);
		
		rb2.setBounds(260, 150, 50, 30);
		this.add(rb2);
		bg.add(rb1);
		bg.add(rb2);
		if("女".equals(sex)){
			rb2.setSelected(true);
		}else{
			rb1.setSelected(true);
		}
		
		
		fieldNum.setBounds(150, 210, 150, 30);
		this.add(fieldNum);
		fieldNum.setText(idnum);
		fieldTel.setBounds(150, 270, 150, 30);
		this.add(fieldTel);
		fieldTel.setText(tel);
		
		JButton btn1=new JButton("保存");
		btn1.setBounds(80, 360, 80, 30);
		this.add(btn1);
		btn1.addActionListener(this);
		JButton btn2=new JButton("取消");
		btn2.setBounds(250, 360, 80, 30);
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
	/**
	 * 保存新录入的信息
	 */
	public void save(){
		String id=fieldId.getText();
		String name=fieldName.getText();
		String sex;
		if(rb1.isSelected()){
			sex="男";
		}else{
			sex="女";
		}
		String idnum=fieldNum.getText();
		String tel=fieldTel.getText();
		WaiterDao wd=new WaiterDao();
		List<Waiters> list=wd.showAll();
		int flag=0;
		if(oldid==null){//新录入数据
			for (int i = 0; i < list.size(); i++) {//遍历数据库的数
				String wid=list.get(i).getWid()+"";
				if(wid.equals(id)){
					JOptionPane.showMessageDialog(null, "编号已存在，请重新输入！");
					flag=1;
				}
			}
			if(flag==0){
				int count=wd.add(id, name, sex, idnum, tel);
				if(count>0){
					JOptionPane.showMessageDialog(null, "录入成功！");
				}else{
					JOptionPane.showMessageDialog(null, "录入失败！");
				}
			}
		}else{//修改原有的数据
			if(!oldid.equals(id)){
				JOptionPane.showMessageDialog(null, "编号不能修改！");
				fieldId.setText(oldid);
			}else{
				int count=wd.update(oldid, name, sex, idnum, tel);
				if(count>0){
					JOptionPane.showMessageDialog(null, "修改成功！");
				}else{
					JOptionPane.showMessageDialog(null, "修改失败！");
				}
			}
		}
	}
}
