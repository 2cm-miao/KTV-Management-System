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
		this.setTitle("KTV����ϵͳ");
		this.setLayout(new BorderLayout());
		this.setSize(1100, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);//���ڲ��ɵ�
		initpanel();//��岼�ֳ�ʼ��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ô��ڹر�
		this.setVisible(true);//���ڿɼ�
	}
	public void initpanel() {
		//�����
		JPanel paneltop=new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
		//paneltop.setBorder(new LineBorder(Color.BLACK));
		paneltop.setPreferredSize(new Dimension(0,100));
		this.add(paneltop,BorderLayout.NORTH);
		initTop(paneltop);
		//�����
		JPanel panelleft=new JPanel();
		panelleft.setBorder(new LineBorder(Color.BLACK));
		panelleft.setPreferredSize(new Dimension(200,0));
		this.add(panelleft,BorderLayout.WEST);
		initLeft(panelleft);
		//�����
		JSplitPane splitpane=new JSplitPane(JSplitPane.VERTICAL_SPLIT);//��ֱ�ָ����
		splitpane.setDividerLocation(360);
		//splitpane.setOneTouchExpandable(true);//UI�շ�С���
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
		panel1.setPreferredSize(new Dimension(750,280));//����������Ϊ���ں��ʵ�ʱ����ʾ������
		panel2.setPreferredSize(new Dimension(750,280));//����������Ϊ���ں��ʵ�ʱ����ʾ������
		panel3.setPreferredSize(new Dimension(750,280));//����������Ϊ���ں��ʵ�ʱ����ʾ������
		JScrollPane jsp1=new JScrollPane(panel1);
		JScrollPane jsp2=new JScrollPane(panel2);
		JScrollPane jsp3=new JScrollPane(panel3);
		tab.addTab("С��",jsp1);
		tab.addTab("�а�",jsp2);
		tab.addTab("���",jsp3);
		splitpane.setTopComponent(tab);
		//��Ӱ��ᰴť
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
		columnNamesAll=new Object[]{"������","��Ʒ����","�ۼ�","����","��Ʒ����"};
		model=new DefaultTableModel(dataAll,columnNamesAll);
		table=new JTable(model);
		JScrollPane jsp=new JScrollPane(table);
		splitpane.setBottomComponent(jsp);
		
	}
	public void initTop(JPanel paneltop) {
		String [] name1=new String[]{"Ԥ������","�˿͹���","Ա������","��ʷ�˵�","�˳�ϵͳ"};
		for (int i = 0; i < name1.length; i++) {
			JButton btn=new JButton(name1[i]);
			btn.setPreferredSize(new Dimension(100,80));
			paneltop.add(btn);
			btn.addActionListener(this);//Ϊ��ť��Ӽ���
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Ԥ������":
			new ReserveView();
			break;
		case "�˿͹���":
			new VipView();
			break;
		case "Ա������":
			new WaitersView();
			break;
		case "��ʷ�˵�":
			new CheckView();
			break;
		case "�˳�ϵͳ":
			System.exit(0);
			break;
		default:
			break;
		}
	}
	/**
	 * ����״̬��ɫ�ı�
	 */
	public void colorChange(JButton btn){
		bs=new BoxService();
		String state=bs.state(btn.getText());
		if("����".equals(state)){
			btn.setBackground(Color.WHITE);
		}else if("ռ��".equals(state)){
			btn.setBackground(Color.YELLOW);
		}else if("ͣ��".equals(state)){
			btn.setBackground(Color.RED);
		}else if("Ԥ��".equals(state)){
			btn.setBackground(Color.BLUE);
		}
		
	}
	/**
	 * ���ݰ���״̬���
	 */
	public void boxClick1(JButton btn){
		bs=new BoxService();
		String text=btn.getText();
		Object [] info=bs.info(text);
		String id=info[0]+"";
		String state=bs.state(id);
		if("ռ��".equals(state)){
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
		if("����".equals(state)){
			new OpenView(type,id,min,new IBoxChange() {
				
				@Override
				public void ChangeColor(String s) {
					if("ȷ��".equals(s)){
						new BoxDao().update("ռ��", id);
						btn.setBackground(Color.YELLOW);
					}else if("ͣ��".equals(s)){
						new BoxDao().update("ͣ��", id);
						btn.setBackground(Color.RED);
					}
				}
			});
		}else if("ռ��".equals(state)){
			
			new ConsumeView(btn.getText(),new IBoxChange() {
				
				@Override
				public void ChangeColor(String s) {
					if("0".equals(s)){
						btn.setBackground(Color.WHITE);
					}
				}
			});
		}else if("Ԥ��".equals(state)){
			JOptionPane.showMessageDialog(null, "��������Ԥ���У�");
		}else{
			int count=JOptionPane.showConfirmDialog(null, "�����ѱ�ͣ�ã��Ƿ����¿�����", "��ʾ��Ϣ", JOptionPane.OK_CANCEL_OPTION);
			if(count==JOptionPane.OK_OPTION){
				new BoxDao().update("����", id);
				btn.setBackground(Color.WHITE);
			}
		}
	}
	

}
