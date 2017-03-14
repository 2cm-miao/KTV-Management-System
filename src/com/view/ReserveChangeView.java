package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

import com.dao.ReserveDao;
import com.domain.Reserve;
import com.service.BoxService;

public class ReserveChangeView extends JDialog implements ActionListener{
	String format="yyyy-MM-dd HH:mm:ss";
	DateFormat timeFormat = new SimpleDateFormat(format);
	String [] bid=null;
	JTextField fieldName=new JTextField();
	JTextField fieldTel=new JTextField();
	JTextArea area=new JTextArea();
	JComboBox<String> cbId=null;
	SpinnerModel model=null;
	JSpinner spinner=null;
	String oldname;
	
	public ReserveChangeView(String name,String tel,String type,String id,String time,String bz){
		this.setTitle("预定管理");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		init(name,tel,type,id,time,bz);
		this.setModal(true);
		this.setVisible(true);
	}
	public void init(String name,String tel,String type,String id,String time,String bz) {
		oldname=name;
		
		String [] str=new String[]{"宾客姓名","联系方式","包厢类型","包厢编号"};
		for (int i = 0; i < str.length; i++) {
			JLabel label=new JLabel(str[i]);
			if(i%2==0){
				label.setBounds(30, 30+30*i, 60, 20);
			}else{
				label.setBounds(200, 30+30*(i-1), 60, 20);
			}
			this.add(label);
		}
		JLabel label2=new JLabel("保留时间");
		label2.setBounds(30, 150, 60, 20);
		this.add(label2);
		JLabel label3=new JLabel("备注");
		label3.setBounds(30, 200, 60, 20);
		this.add(label3);
		
		//姓名编辑框
		fieldName.setBounds(90, 30, 100, 20);
		this.add(fieldName);
		fieldName.setText(name);
		//电话编辑框
		fieldTel.setBounds(260, 30, 100, 20);
		this.add(fieldTel);
		fieldTel.setText(tel);
		//包厢类型下拉列表
		final BoxService bs=new BoxService();
		String [] state=bs.typeAll();
		JComboBox<String> cbType=new JComboBox<String>(state);
		cbType.setBounds(90, 90, 100, 20);
		this.add(cbType);
		cbType.setSelectedItem(type);
		//包厢ID下拉列表
		cbId=new JComboBox<String>(bs.idAll(cbType.getSelectedItem()+""));
		cbId.setBounds(260, 90, 100, 20);
		this.add(cbId);
		cbId.setSelectedItem(id);
		//根据包厢类型选项更新包厢ID选项
		cbType.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				bid=bs.idAll(e.getItem()+"");
				cbId.removeAllItems();
				for (int i = 0; i < bid.length; i++) {
					cbId.addItem(bid[i]);
				}
			}
		});
		//时间微调器
		
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();//当前时间
		cal.add(Calendar.YEAR, -50);
		Date startDate = cal.getTime();//时间最小值
		cal.add(Calendar.YEAR, 100);
		Date endDate = cal.getTime();//时间最大值
		model=new SpinnerDateModel(now, startDate, endDate, Calendar.YEAR);
		spinner=new JSpinner(model);
		spinner.setEditor(new JSpinner.DateEditor(spinner, format));
		spinner.setBounds(90, 150, 150, 20);
		this.add(spinner);
		try {
			if(time!=null){
				spinner.setValue(timeFormat.parse(time));
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		//文本域
		area.setBounds(90, 200, 250, 80);
		this.add(area);
		area.setText(bz);
		//按钮
		JButton btn1=new JButton("确定");
		btn1.setBounds(90, 300, 80, 30);
		this.add(btn1);
		btn1.addActionListener(this);
		JButton btn2=new JButton("取消");
		btn2.setBounds(220, 300, 80, 30);
		this.add(btn2);
		btn2.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if("确定".equals(e.getActionCommand())){
			info();
		}
		if("取消".equals(e.getActionCommand())){
			this.dispose();
		}
		
	}
	public void info(){
		String name=fieldName.getText();
		String tel=fieldTel.getText();
		String bid=cbId.getSelectedItem()+"";
		String time=timeFormat.format((Date)spinner.getValue());
		String bz=area.getText();
		ReserveDao rd=new ReserveDao();
		List<Reserve> list=rd.showAll();
		int flag=0;
		if(oldname==null){//添加新记录
			for (int i = 0; i < list.size(); i++) {
				String btname=list.get(i).getBtname();
				if(btname.equals(name)){
					JOptionPane.showMessageDialog(null, "该姓名已存在，请重新输入！");
					flag=1;
				}
			}
			if(flag==0){
				int count=rd.insert(name, tel, bid, time, bz);
				if(count>0){
					JOptionPane.showMessageDialog(null, "预定成功！");
				}else{
					JOptionPane.showMessageDialog(null, "预定失败！");
				}
			}
		}else{//修改原有的数据
			int count=rd.update(tel, bid, time, bz, oldname);
			if(count>0){
				JOptionPane.showMessageDialog(null, "修改成功！");
			}else{
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		}
		
	}
}
