package net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

public class Server {
	private static final int maxServNum=10;
	private int serverPort;
	private ServerSocket severSocket;
	private Socket[] socket=new Socket[maxServNum];
	private int servingNum=0;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private Thread serverThread=new Thread(new Runnable(){
		private synchronized boolean waiting(){
			if(servingNum<maxServNum){
    			try {
					socket[servingNum]=severSocket.accept();
				} catch (IOException e) {
					e.printStackTrace();
				}
    			servingNum++;
    			return true;
    		}
			else
				return false;
		}
    	public void run(){
    		System.out.println("run server");
    		while(true){
    			if(!waiting())
    				break;
    		}
    		System.out.println("end server");
    	}
    });
    public void startServer(int port){
    	try {
    		serverPort=port;
    		severSocket=new ServerSocket(serverPort);
    		serverThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void stopServer(){
    	serverThread.interrupt();
    }
    public boolean isServing(){
    	if(servingNum>0)
    		return true;
    	else
    	    return false;
    }
    public BufferedReader getReader(int serveId){
    	if(serveId<0||serveId>=servingNum)
    		return null;
		try {
			reader=new BufferedReader(new InputStreamReader(socket[serveId].getInputStream()));
			return reader;
		} catch (IOException e) {
			e.printStackTrace();
		}  	
		return null;
    }
    public BufferedWriter getWriter(int serveId){
    	if(serveId<0||serveId>=servingNum)
    		return null;
		try {
			writer=new BufferedWriter(new OutputStreamWriter(socket[serveId].getOutputStream()));
			return writer;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
}
