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
	JTextField fieldId=new JTextField();   //���
	JTextField fieldName=new JTextField();  //����
	ButtonGroup bg=new ButtonGroup();
	JRadioButton rb1=new JRadioButton("��",true);
	JRadioButton rb2=new JRadioButton("Ů");
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
		this.setTitle("��Ա��Ϣ");
		this.setLayout(null);
		this.setSize(450, 450);
		this.setLocationRelativeTo(null);
		init();
		this.setModal(true);
		this.setVisible(true);
	}
	public void init() {
		String [] str=new String[]{"��Ա��ţ�","��Ա������","��Ա�Ա�","��Ա�绰��","��ǰ���֣�","�Ǽ�ʱ�䣺","��ע��"};
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
		
		//�Ա�ѡ��
		rb1.setBounds(100, 110, 50, 30);
		this.add(rb1);
		rb2.setBounds(160, 110, 50, 30);
		this.add(rb2);
		bg.add(rb1);
		bg.add(rb2);
		
		//�绰
		fieldTel.setBounds(290, 110, 100, 30);
		this.add(fieldTel);
		
		//��ǰ����
		fieldJF.setBounds(100, 170, 100, 30);
		this.add(fieldJF);
		
		//�Ǽ�ʱ��
		fieldDate.setBounds(290, 170, 100, 30);
		this.add(fieldDate);
		
		//��ע
		ta.setBounds(100, 230, 280, 120);
		this.add(ta);
		
		JButton btn1=new JButton("����");
		btn1.setBounds(80, 360, 80, 30);
		this.add(btn1);
		btn1.addActionListener(this);
		JButton btn2=new JButton("ȡ��");
		btn2.setBounds(280, 360, 80, 30);
		this.add(btn2);
		btn2.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if("����".equals(e.getActionCommand())){
			save();
		}
		if("ȡ��".equals(e.getActionCommand())){
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
		if(rb1.isSelected())  sex="��";
		else  sex="Ů";
		
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
					JOptionPane.showMessageDialog(null, "����Ѵ��ڣ����������룡");
					flag=1;
					break;
				}
			}
			
			if(flag==0)
			{
				int count=vi.add(id, name, sex, tel, jf, da, bz);
				if(count==0)  JOptionPane.showMessageDialog(null,"¼��ʧ�ܣ�");
				else  JOptionPane.showMessageDialog(null,"¼��ɹ�");
			}
		}
		else
		{
			if(!oldid.equals(id))
			{
				JOptionPane.showMessageDialog(null,"��Ų����޸ģ�");
				fieldId.setText(oldid);
			}
			else
			{
				int count=vi.update(id, name, sex, tel, jf, da, bz);
				if(count==0)  JOptionPane.showMessageDialog(null,"�޸�ʧ�ܣ�");
				else  JOptionPane.showMessageDialog(null,"�޸ĳɹ���");
			}
		}
	}
}
