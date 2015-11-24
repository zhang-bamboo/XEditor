package xEditorUI;

import javax.swing.*;
import example.MyDialog;
import icon.DotIcon;
import net.ClientDialog;
import net.ServerDialog;
import java.awt.*;
import java.awt.event.*;

public class NorthPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private XEditorFrame faFrame;
	private JButton butn1;
	private JButton butn2;
	private JButton butn6;
	private JButton butn7;
	private void setButn1(){
		butn1=new JButton("Yes",new DotIcon(15,15));
		butn1.addActionListener(new ActionListener(){
			public void  actionPerformed(ActionEvent e){
				new MyDialog(faFrame,"dialog").setVisible(true);
			}
		});
	}
	private void setButn2(){
		butn2=new JButton("hello");
		butn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			}
		});
	}

	private void setButn6(){
		butn6=new JButton("server");
		butn6.addActionListener(new ActionListener(){
			public void  actionPerformed(ActionEvent e){
				new ServerDialog(faFrame).setVisible(true);
			}
		});
	}
	private void setButn7(){
		butn7=new JButton("client");
		butn7.addActionListener(new ActionListener(){
			public void  actionPerformed(ActionEvent e){
				new ClientDialog(faFrame).setVisible(true);
			}
		});
	}
	public NorthPanel(Frame fa){
	        faFrame=(XEditorFrame)fa;
		setLayout(new FlowLayout(0,10,10));
		setButn1();
		add(butn1);//添加提交按钮:dialog
		setButn2();
		add(butn2);//添加提交按钮:password dialog
		setButn6();
		add(butn6);//添加按钮:server
		setButn7();
		add(butn7);//添加按钮:client
	}

}