package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.dao.AccountDao;
import com.dao.BillDao;
import com.dao.BoxDao;
import com.dao.WaiterDao;
import com.inter.IBoxChange;
import com.service.BillService;
import com.service.BoxService;
import com.service.GoodsService;
import com.service.WaitersService;

public class ConsumeView extends JDialog {
	Object [][] data1=null;
	Object [][] data2=null;
	Object [] columnNames1=null;
	Object [] columnNames2=null;
	JComboBox<String> cb=null;
	DefaultTableModel model1=null;
	JTable table1=null;
	DefaultTableModel model2=null;
	JTable table2=null;
	String bid;
	int wid;
	IBoxChange ic;
	

	public ConsumeView(String bid,IBoxChange ic){
		this.ic=ic;
		this.setTitle("消费详情");
		this.setSize(800, 600);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.bid=bid;
		init();
		this.setModal(true);
		this.setVisible(true);
	}
	/**
	 * 面板初始化
	 */
	private void init() {
		JPanel topPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.setBorder(new LineBorder(Color.RED));
		topPanel.setPreferredSize(new Dimension(0,70));
		this.add(topPanel,BorderLayout.NORTH);
		topInit(topPanel);
		
		JPanel leftPanel=new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setBorder(new LineBorder(Color.RED));
		leftPanel.setPreferredSize(new Dimension(390,0));
		this.add(leftPanel,BorderLayout.WEST);
		leftInit(leftPanel);
		
		JPanel rightPanel=new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setBorder(new LineBorder(Color.RED));
		rightPanel.setPreferredSize(new Dimension(390,0));
		this.add(rightPanel,BorderLayout.EAST);
		rightInit(rightPanel);
	}
	/**
	 * 上面板初始化
	 * @param topPanel
	 */
	private void topInit(JPanel topPanel) {
		JLabel label=new JLabel("包间编号：");
		topPanel.add(label);
		JLabel idLabel=new JLabel(bid+"");
		topPanel.add(idLabel);
		JLabel label2=new JLabel("服务生：");
		topPanel.add(label2);
		String wname=findWname();
		JLabel nameLabel=new JLabel(wname);
		topPanel.add(nameLabel);
		
		/*JButton btn1=new JButton("退单");
		topPanel.add(btn1);
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected=table2.getSelectedRow();
				System.out.println(selected);
				String name=model2.getValueAt(selected, 1)+"";
				model2.removeRow(selected);
				new BillDao().deleteGood(name);
			}
		});*/
		JButton btn2=new JButton("结账");
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double sum=cal();
				int count=JOptionPane.showConfirmDialog(null, "本次消费总额为"+sum, "结账", JOptionPane.OK_CANCEL_OPTION);
				if(count==JOptionPane.OK_OPTION){
					new BoxDao().update("空闲", bid);
					new BillDao().delete(Integer.parseInt(bid));
					new AccountDao().insert(Integer.parseInt(bid), sum);
					new BoxDao().updateWid(wid, bid);//更改包间服务生
					new WaiterDao().updateState("空闲", wid);
					ic.ChangeColor(count+"");
					ConsumeView.this.dispose();
				}
				
			}
		});
		topPanel.add(btn2);
	}
	/**
	 * 左面板初始化
	 * @param leftPanel
	 */
	private void leftInit(JPanel leftPanel) {
		JTabbedPane tab=new JTabbedPane();
		
		JLabel label=new JLabel("商品类别");
		label.setBounds(30, 10, 80, 30);
		leftPanel.add(label);
		
		String [] type=new GoodsService().showType();
		cb=new JComboBox<String>(type);
		cb.setBounds(110, 10, 120, 30);
		leftPanel.add(cb);
		
		cb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				data1=new GoodsService().showGoods(e.getItem()+"");
				model1.setDataVector(data1, columnNames1);
			}
		});
		data1=new GoodsService().showGoods(cb.getSelectedItem()+"");
		columnNames1=new Object[]{"商品编号","商品名称","售价","数量","商品类型"};
		model1=new DefaultTableModel(data1,columnNames1);
		table1=new JTable(model1){

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		table1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					Object [] info=new Object[4];
					int row=table1.getSelectedRow();
					info[0]=bid;
					info[1]=model1.getValueAt(row, 1);
					info[2]=model1.getValueAt(row, 2);
					info[3]=1;
					new BillDao().insert(Integer.parseInt(bid), info[1]+"",Double.parseDouble(info[2].toString()), Integer.parseInt(info[3]+""));
					model2.addRow(info);
				}
			}
			
		});
		JScrollPane jsp1=new JScrollPane(table1);
		jsp1.setBounds(10, 70, 370, 400);
		leftPanel.add(jsp1);
		
		
	}
	/**
	 * 下面板初始化
	 * @param rightPanel
	 */
	private void rightInit(JPanel rightPanel) {
		data2=new BillService().showMoney(Integer.parseInt(bid));
		columnNames2=new Object[]{"商品名称","售价","数量","商品类型"};
		model2=new DefaultTableModel(data2,columnNames2);
		JTable table2=new JTable(model2);
		JScrollPane jsp2=new JScrollPane(table2);
		jsp2.setBounds(10, 20, 370, 450);
		rightPanel.add(jsp2);
	}
	/**
	 * 账单结算
	 */
	public double cal(){
		int row=model2.getRowCount();
		double sum=0;
		for (int i = 0; i < row; i++) {
			double money=(double) model2.getValueAt(i, 2);
			int count=(int) model2.getValueAt(i, 3);
			sum=money*count+sum;
		}
		return sum;
	}
	public String findWname(){
		String name=null;
		wid=new BoxService().findID(Integer.parseInt(bid));
		Object [][] data=new WaitersService().showAll();
		for (int i = 0; i < data.length; i++) {
			if(wid==Integer.parseInt(data[i][0].toString())){
				name=data[i][1]+"";
			}
		}
		return name;
	}

}
