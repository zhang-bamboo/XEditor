package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddr {
	public static String getLocalIp(){	
			try {
				return InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			return null;
	}
	public static String getHostName(){
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

}
