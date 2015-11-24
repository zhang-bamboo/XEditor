package net;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClientDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton butn1;
	private JButton butn2;
	private JTextArea historyText;
	private JTextArea sendText;
	Client myClient=null;
    PrintWriter writer;
	public ClientDialog(Frame f){
		super(f,"client");
		Container ct=getContentPane();
		historyText=new JTextArea();
		ct.add(new JScrollPane(historyText),BorderLayout.CENTER);
		JPanel panel=new JPanel();
		panel.setLayout(new FlowLayout());
		sendText=new JTextArea(3,18);
		panel.add(new JScrollPane(sendText));
	    butn1=new JButton("send");
	    butn1.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		  if(myClient==null){
	    			  myClient=new Client(InetAddr.getLocalIp(),8998);
	  	    		  writer=myClient.getWriter();
	    		  }  
	    		  writer.println(sendText.getText());
	    		  historyText.append(sendText.getText()+"\n");
	    		  sendText.setText("");
	    	}
	    });
		panel.add(butn1);
		butn2=new JButton("connect");
	    butn2.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		myClient=new Client(InetAddr.getLocalIp(),8998);
	    		writer=myClient.getWriter();
	    	}
	    });
		panel.add(butn2);
		ct.add(panel,BorderLayout.SOUTH);
		setBounds(120,120,400,200);
	}


}
