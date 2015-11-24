package net;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class ServerDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton butn1;
	private JButton butn2;
	private JTextArea historyText;
	private JTextArea recevingText;
	private Server myServer;
	Thread servingThread=new Thread(new Runnable(){
		private synchronized void doIt(){
			try {
				if(myServer.isServing()){
					String recvStr=myServer.getReader(0).readLine();
					recevingText.setText(recvStr);
					historyText.append(recvStr+"\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	public void run(){
    		System.out.println("run serving");
    		while(true){
    			doIt();		
    		}
    	}
    });
	public ServerDialog(Frame f){
		super(f,"server");
		Container ct=getContentPane();
		historyText=new JTextArea();
		ct.add(new JScrollPane(historyText),BorderLayout.CENTER);
		JPanel panel=new JPanel();
		panel.setLayout(new FlowLayout());
		recevingText=new JTextArea(3,18);
		panel.add(new JScrollPane(recevingText));
	    butn1=new JButton("receive");
	    butn1.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		myServer=new Server();
	    		myServer.startServer(8998); 
	    		servingThread.start();
	    	}
	    });
		panel.add(butn1);
		butn2=new JButton("stop");
	    butn2.addActionListener(new ActionListener(){
	    	@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
	    		myServer.stopServer();
	    		servingThread.stop();
	    	}
	    });
		panel.add(butn2);
		ct.add(panel,BorderLayout.SOUTH);
		setBounds(120,120,400,200);
	}

}
