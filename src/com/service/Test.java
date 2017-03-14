package com.service;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Test extends JFrame implements ActionListener{
	JLabel label=new JLabel();
	JButton btn=new JButton("图标");
	Timer timer;

	public static void main(String[] args) {
		new Test();
	}
	public Test(){
		this.setTitle("计时器Timer测试");
		this.setSize(380, 380);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		//设置定时器
		timer=new Timer(1000,this);
		timer.setInitialDelay(0);
		timer.start();
		
		//this.add(label);
		btn.setBounds(10, 10, 300, 300);
		btn.setIcon(new ImageIcon("src/images/151103.jpg"));
		btn.setVerticalTextPosition(JButton.BOTTOM);
		btn.setHorizontalTextPosition(JButton.CENTER);
		btn.setContentAreaFilled(false);
		this.add(btn);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		label.setText(format.format(date));
	}
	/**
	 * 从二维数组中读取满足条件的多条记录
	 */
	private static void fun1() {
		int temp=4;
		int [][] a=new int[][]{{1,2,3},{4,5,6},{4,8,9},{10,11,12}};
		int [][] b=new int[a.length][a[0].length];
		//int [] ob=new int[a.length];
		int flag=0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if(a[i][j]==temp){
					b[flag]=a[i];
					flag++;
				}
			}
		}
		/*int flag=0,count=0;
		int [][] b=null;
		b=new int[count][a.length];*/
		
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				System.out.print(b[i][j]);
			}
			System.out.println();
		}
	}

}
