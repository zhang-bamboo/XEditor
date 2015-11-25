package main;


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import graphic.DrawingPane;
import graphic.PaintMethod;
import myDate.MyCalendar;
import xEditorUI.XEditorFrame;
/**
 * MainClass has main method
 * @author zhang
 *
 */
public class MainClass {

	public static void main(String args[]) {
	    
	    
	    
		XEditorFrame editorFrame;
		try {
		    editorFrame = new XEditorFrame("Hello");
		    editorFrame.setVisible(true);
		} catch (MalformedURLException e) {
		    // TODO 自动生成的 catch 块
		    e.printStackTrace();
		}
	    
	    
		//new PasswordDialog(editorFrame,0).setVisible(true);		
		//new MyTreeFrame().setVisible(true);
//		Database database = new Database();
//		database.getConnection();
//		database.printAllFrom("customers");
		//String i=(String) database.maxValue("customers", "cust_name");
//		String[] insertStr={"zhang bin", "4545 53rd Street", "Chicago", "IL", "54545", "USA", "Kim Howard",null};
//		database.insertRow("customers", "cust_id", insertStr);
//		database.printAllFrom("customers");
		// StringBuilder[] stringBuilders = new StringBuilder[5];
		// StringBuilder stringBuilder = new StringBuilder("a");
		// for (int i = 0; i < 5; i++) {
		// stringBuilder.append(" ");
		// stringBuilders[i] = new StringBuilder(stringBuilder);
		// }
		// stringBuilders[4]=new StringBuilder("aaaaa");
		// //StringFormat.stringTrim(stringBuilders);
		// for (int i = 0; i < stringBuilders.length; i++)
		// System.out.println(stringBuilders[i]);
		// new Test();
		// new LoginFrame();
 		 
		// Server myServer=new Server();
		// myServer.startServer(8988);
		// Client myClient=new Client(InetAddr.getLocalIp(),8988);
		// myClient.getWriter().println("hello World");
		// Thread thread1=new Thread(new Runnable(){
		// public void run(){
		// try {
		// Server myServer=new Server();
		// myServer.getServer(8988);
		// System.out.println(myServer.getReader().readLine());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// });
		// thread1.start();
		// Client myClient=new Client(InetAddr.getLocalIp(),8988);
		// myClient.getWriter().println("hello World");

	}
}
