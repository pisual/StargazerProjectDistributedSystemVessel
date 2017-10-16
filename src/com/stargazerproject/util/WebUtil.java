package com.stargazerproject.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class WebUtil {
	
	/**获取本机IP
	 * 如果无法获取，将返回"127.0.0.1"作为默认IP
	 * @exception UnknownHostException
	 * **/
	public static String getServerIP(){
		String ip = "127.0.0.1";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}
	
}
