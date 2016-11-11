package com.github.jorgefilho.camel.router.bean;

public class MonitorBean {
	
	 public void logMsgHeader(String header) {
         System.out.println("Received: " + header);
     }
}
