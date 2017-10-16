package com.stargazerproject.util;

import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetUtil {

	public static void localNetworkAddress(){
		try {  
            Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();  
            StringBuilder builder = new StringBuilder();  
            while (el.hasMoreElements()) {  
                byte[] mac = el.nextElement().getHardwareAddress();  
                if (mac == null){  
                   continue;  
                }  
                if(builder.length() > 0){  
                    builder.append(",");  
                }  
                for (byte b : mac) {  
                      
                   //convert to hex string.  
                   String hex = Integer.toHexString(0xff & b).toUpperCase();  
                   if(hex.length() == 1){  
                       hex  = "0" + hex;  
                   }  
                   builder.append(hex);  
                   builder.append("-");  
                }  
                builder.deleteCharAt(builder.length() - 1);  
           }  
             
           if(builder.length() == 0){  
               System.out.println("Sorry, can't find your MAC Address.");  
           }else{  
               System.out.println("Your MAC Address is " + builder.toString());  
           }  
       }catch (Exception exception) {  
           exception.printStackTrace();  
       }  
	}
	
	public static void main(String[] args) {
		NetUtil.localNetworkAddress();
	}
}
