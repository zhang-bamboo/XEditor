package xEditorUI;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
import example.MyListModel;
import file.MyComboBoxModel;

public class SouthPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final JButton but1;
	final JButton but2;
	final JButton but3;
	final JButton but4;
	final JButton but5;
	private JButton but6;
	private JButton but7;
	private JLabel jlabSnow;
	public SouthPanel(Frame f){
		setLayout(new FlowLayout());
		but1=new JButton("JTextField");
		but1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JDialog jd1=new JDialog(f);
				Container ct1=jd1.getContentPane();
				JLabel jl1=new JLabel("Account Id:");
				ct1.add(jl1);
			    ct1.add(new JTextField(15));
			    jd1.setLayout(new FlowLayout());
				jd1.setBounds(120,120,200,100);
				jd1.setVisible(true);
			}
		});
		
		but2=new JButton("RadioButton");
		but2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JDialog jd1=new JDialog(f);
				Container ct1=jd1.getContentPane();
				JRadioButton jrb1=new JRadioButton("ok");//创建单选按钮,Radio Button
				JRadioButton jrb2=new JRadioButton("yes");
				ct1.add(jrb1);
				ct1.add(jrb2);
				ButtonGroup btGroup=new ButtonGroup();//单选按钮组,Radio Button Broup
				btGroup.add(jrb1);
				btGroup.add(jrb2);
			    jd1.setLayout(new FlowLayout());
				jd1.setBounds(120,120,200,100);
				jd1.setVisible(true);
			}
		});
		
		but3=new JButton("JCheckBox");
		but3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JDialog jd1=new JDialog(f);
				Container ct1=jd1.getContentPane();
				JCheckBox jcb1=new JCheckBox("1");//创建复选框
				JCheckBox jcb2=new JCheckBox("2");
				ct1.add(jcb1);
				ct1.add(jcb2);
			    jd1.setLayout(new FlowLayout());
				jd1.setBounds(120,120,200,100);
				jd1.setVisible(true);
			}
		});
		
		but4=new JButton("JComboBox");
		but4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JDialog jd1=new JDialog(f);
				Container ct1=jd1.getContentPane();
				JComboBox<String> jcmb=new JComboBox<>(new MyComboBoxModel());
				ct1.add(jcmb);//添加下拉列表
			    jd1.setLayout(new FlowLayout());
				jd1.setBounds(120,120,200,100);
				jd1.setVisible(true);
			}
		});
		
		but5=new JButton("JList");
		but5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JDialog jd1=new JDialog(f);
				Container ct1=jd1.getContentPane();
				JList<String> jls1=new JList<>(new MyListModel());
				ct1.add(jls1);//添加列表
			    jd1.setLayout(new FlowLayout());
				jd1.setBounds(120,120,200,100);
				jd1.setVisible(true);
			}
		});
		but6=new JButton("Thread");
		but6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JDialog jd1=new JDialog(f);
				Container ct1=jd1.getContentPane();
				URL url=XEditorFrame.class.getResource("1.gif");
				jlabSnow=new JLabel(new ImageIcon(url));
				jlabSnow.setHorizontalAlignment(SwingConstants.LEFT);
				jlabSnow.setBounds(10, 10, 200, 50);
				ct1.add(jlabSnow);//添加标签
				Thread thread=new Thread(new Runnable(){
					public void run(){
						int count=10;
						while(true){
							jlabSnow.setBounds(count, 10, 200, 50);
						    count+=5;
						    if(count>=200)
						    	count=10;
						    try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				});
				thread.start();
			    jd1.setLayout(null);
				jd1.setBounds(300,200,250,100);
				jd1.setVisible(true);
			}
		});
		but7=new JButton("JProgressBar");
		but7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JDialog jd1=new JDialog(f);
				Container ct1=jd1.getContentPane();
				JProgressBar progressBar1=new JProgressBar();
				progressBar1.setStringPainted(true);
				ct1.add(progressBar1,BorderLayout.NORTH);//添加标签
				JProgressBar progressBar2=new JProgressBar();
				progressBar2.setStringPainted(true);
				ct1.add(progressBar2,BorderLayout.SOUTH);//添加标签
				Thread thread1=new Thread(new Runnable(){
					private synchronized void doIt(){//同步方法
						int count1=0;
						int count2=0;
						while(true){
							progressBar2.setValue(count2++);
							if(count2>100){
								count2=0;
								progressBar1.setValue(++count1);
								if(count1>=100)
									break;
							}
						    try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}					
					}
					public void run(){
						doIt();
					}	
				});
				thread1.start();	
				jd1.setBounds(300,200,250,100);
				jd1.setVisible(true);
			}
		});
		add(but1);
		add(but2);
		add(but3);
		add(but4);
		add(but5);
		add(but6);
		add(but7);
	}
}
