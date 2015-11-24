package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private String ip;
	private int port;
	private Socket socket;
	private BufferedReader reader;
	//private BufferedWriter writer;
	private PrintWriter writer;
    public Client(String ip,int port){
    	try {
    		this.ip=ip;
    		this.port=port;
			socket=new Socket(ip,port);
			//reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer=new PrintWriter(socket.getOutputStream(),true);
			//writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public BufferedReader getReader(){
    	return reader;
    }
    public PrintWriter getWriter(){
    	return writer;
    }
}
