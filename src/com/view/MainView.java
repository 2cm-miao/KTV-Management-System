package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.dao.BoxDao;
import com.inter.IBoxChange;
import com.service.BillService;
import com.service.BoxService;

public class MainView extends JFrame implements ActionListener {
	BoxService bs=null;
	JLabel label=null;
	JButton btn1=null;
	JButton btn2=null;
	JButton btn3=null;
	
	Object [][] dataAll=null;
	Object [] columnNamesAll=null;
	DefaultTableModel model=null;
	JTable table=null;

	public static void main(String[] args) {
		new MainView();
	}
	public MainView(){
		this.setTitle("KTV管理系统");
		this.setLayout(new BorderLayout());
		this.setSize(1100, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);//窗口不可调
		initpanel();//面板布局初始化
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗口关闭
		this.setVisible(true);//窗口可见
	}
	public void initpanel() {
		//上面板
		JPanel paneltop=new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
		//paneltop.setBorder(new LineBorder(Color.BLACK));
		paneltop.setPreferredSize(new Dimension(0,100));
		this.add(paneltop,BorderLayout.NORTH);
		initTop(paneltop);
		//左面板
		JPanel panelleft=new JPanel();
		panelleft.setBorder(new LineBorder(Color.BLACK));
		panelleft.setPreferredSize(new Dimension(200,0));
		this.add(panelleft,BorderLayout.WEST);
		initLeft(panelleft);
		//中面板
		JSplitPane splitpane=new JSplitPane(JSplitPane.VERTICAL_SPLIT);//垂直分割面板
		splitpane.setDividerLocation(360);
		//splitpane.setOneTouchExpandable(true);//UI收放小组件
		this.add(splitpane,BorderLayout.CENTER);
		initTop(splitpane);
		initBottom(splitpane);
	}
	public void initLeft(JPanel panelleft) {
		label=new JLabel();
		panelleft.add(label);
		Timer timer=new Timer(1000,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date=new Date();
				label.setText(format.format(date));
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
	public void initTop(JSplitPane splitpane) {
		JTabbedPane tab=new JTabbedPane();
		JPanel panel1=new JPanel(new FlowLayout(FlowLayout.LEFT,30,30));
		JPanel panel2=new JPanel(new FlowLayout(FlowLayout.LEFT,30,30));
		JPanel panel3=new JPanel(new FlowLayout(FlowLayout.LEFT,30,30));
		panel1.setPreferredSize(new Dimension(750,280));//这里设置是为了在合适的时候显示滚动条
		panel2.setPreferredSize(new Dimension(750,280));//这里设置是为了在合适的时候显示滚动条
		panel3.setPreferredSize(new Dimension(750,280));//这里设置是为了在合适的时候显示滚动条
		JScrollPane jsp1=new JScrollPane(panel1);
		JScrollPane jsp2=new JScrollPane(panel2);
		JScrollPane jsp3=new JScrollPane(panel3);
		tab.addTab("小包",jsp1);
		tab.addTab("中包",jsp2);
		tab.addTab("大包",jsp3);
		splitpane.setTopComponent(tab);
		//添加包厢按钮
		String [] number=new String [30];
		for (int i = 0; i < number.length; i++) {
			btn1=new JButton("1"+String.format("%03d", i+1));
			panel1.add(btn1);
			colorChange(btn1);
			btn1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if(e.getClickCount()==1){
						btn1=(JButton) e.getComponent();
						boxClick1(btn1);
					}
					if(e.getClickCount()==2){
						btn1=(JButton) e.getComponent();
						boxClick2(btn1);
					}
				}
			});
			btn2=new JButton("2"+String.format("%03d", i+1));
			panel2.add(btn2);
			colorChange(btn2);
			btn2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if(e.getClickCount()==1){
						btn2=(JButton) e.getComponent();
						boxClick1(btn2);
					}
					if(e.getClickCount()==2){
						btn2=(JButton) e.getComponent();
						boxClick2(btn2);
					}
				}
			});
			btn3=new JButton("3"+String.format("%03d", i+1));
			panel3.add(btn3);
			colorChange(btn3);
			btn3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if(e.getClickCount()==1){
						btn3=(JButton) e.getComponent();
						boxClick1(btn3);
					}
					if(e.getClickCount()==2){
						btn3=(JButton) e.getComponent();
						boxClick2(btn3);
					}
				}
			});
		}
	}
	public void initBottom(JSplitPane splitpane){
		columnNamesAll=new Object[]{"包厢编号","商品名称","售价","数量","商品类型"};
		model=new DefaultTableModel(dataAll,columnNamesAll);
		table=new JTable(model);
		JScrollPane jsp=new JScrollPane(table);
		splitpane.setBottomComponent(jsp);
		
	}
	public void initTop(JPanel paneltop) {
		String [] name1=new String[]{"预定管理","顾客管理","员工管理","历史账单","退出系统"};
		for (int i = 0; i < name1.length; i++) {
			JButton btn=new JButton(name1[i]);
			btn.setPreferredSize(new Dimension(100,80));
			paneltop.add(btn);
			btn.addActionListener(this);//为按钮添加监听
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "预定管理":
			new ReserveView();
			break;
		case "顾客管理":
			new VipView();
			break;
		case "员工管理":
			new WaitersView();
			break;
		case "历史账单":
			new CheckView();
			break;
		case "退出系统":
			System.exit(0);
			break;
		default:
			break;
		}
	}
	/**
	 * 包厢状态颜色改变
	 */
	public void colorChange(JButton btn){
		bs=new BoxService();
		String state=bs.state(btn.getText());
		if("空闲".equals(state)){
			btn.setBackground(Color.WHITE);
		}else if("占用".equals(state)){
			btn.setBackground(Color.YELLOW);
		}else if("停用".equals(state)){
			btn.setBackground(Color.RED);
		}else if("预定".equals(state)){
			btn.setBackground(Color.BLUE);
		}
		
	}
	/**
	 * 根据包厢状态点击
	 */
	public void boxClick1(JButton btn){
		bs=new BoxService();
		String text=btn.getText();
		Object [] info=bs.info(text);
		String id=info[0]+"";
		String state=bs.state(id);
		if("占用".equals(state)){
			dataAll=new BillService().showMoney(Integer.parseInt(id));
			model.setDataVector(dataAll, columnNamesAll);
		}else{
			model.setDataVector(null, columnNamesAll);
		}
	}
	public void boxClick2(final JButton btn){
		bs=new BoxService();
		String text=btn.getText();
		Object [] info=bs.info(text);
		final String id=info[0]+"";
		String type=info[1]+"";
		String min=info[2]+"";
		String state=bs.state(id);
		if("空闲".equals(state)){
			new OpenView(type,id,min,new IBoxChange() {
				
				@Override
				public void ChangeColor(String s) {
					if("确定".equals(s)){
						new BoxDao().update("占用", id);
						btn.setBackground(Color.YELLOW);
					}else if("停用".equals(s)){
						new BoxDao().update("停用", id);
						btn.setBackground(Color.RED);
					}
				}
			});
		}else if("占用".equals(state)){
			
			new ConsumeView(btn.getText(),new IBoxChange() {
				
				@Override
				public void ChangeColor(String s) {
					if("0".equals(s)){
						btn.setBackground(Color.WHITE);
					}
				}
			});
		}else if("预定".equals(state)){
			JOptionPane.showMessageDialog(null, "包厢正被预定中！");
		}else{
			int count=JOptionPane.showConfirmDialog(null, "包厢已被停用，是否重新开启？", "提示信息", JOptionPane.OK_CANCEL_OPTION);
			if(count==JOptionPane.OK_OPTION){
				new BoxDao().update("空闲", id);
				btn.setBackground(Color.WHITE);
			}
		}
	}
	

}
