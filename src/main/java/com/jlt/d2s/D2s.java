package com.jlt.d2s;
import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class D2s/* extends TimerTask*/{

	/*@Override
	public void run() {
		D2SMethod();
	}*/

	static final String LOGON_SITE = "10.1.110.41";

	static final int LOGON_PORT = 7001;

	public static void D2SMethod() throws Exception{

		HttpClient client = new HttpClient();

		client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT,"http");

		// 模拟登录页面login.jsp->main.jsp

		PostMethod post = new PostMethod("/console/j_security_check");

		NameValuePair name = new NameValuePair("j_username", "weblogic");

		NameValuePair pass = new NameValuePair("j_password", "weblogic");
		
		 post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		
		//System.out.println("set username and password");

		post.setRequestBody(new NameValuePair[] { name, pass });

		int status = -1;
		try {
			 status = client.executeMethod(post);
		} catch (HttpException e) {
			// 发生http异常
			e.printStackTrace();
		} catch (IOException e) {
			// 发生IO异常
			e.printStackTrace();
		}
		
		if (status == HttpStatus.SC_OK) {
            System.out.println("=====================================post提交成功!==============================");
        }

		System.out.println(post.getResponseBodyAsString());
		
		post.releaseConnection();
		
	      System.out.println("========================================post结束!================================");
		
		CookieSpec cookiespec = CookiePolicy.getDefaultSpec(); 
        Cookie[] cookies = cookiespec.match(LOGON_SITE, LOGON_PORT, "/console", false, client.getState().getCookies());  
       if (cookies.length == 0) {  
           System.out.println("None");     
       } else {  
           for (int i = 0; i < cookies.length; i++) {  
               System.out.println(cookies[i].toString());     
              client.getState().addCookie(cookies[i]);
           }  
        }  		 
       
      
       
		GetMethod get = new GetMethod("/console/console.portal");  
		   
		int getStatus=-1;
		         try {
		        	 getStatus=client.executeMethod(get);
					// 发生http异常
		         } catch (HttpException e) {
		 			// 发生http异常
		 			e.printStackTrace();
		 		} catch (IOException e) {
		 			// 发生IO异常
		 			e.printStackTrace();
		 		}
		 		
		 		if(getStatus==HttpStatus.SC_OK){
		            System.out.println("==========================================get提交成功===================================!");
		 		}
		   
		        System.out.println(get.getResponseBodyAsString());  
		  
		         get.releaseConnection();
		         
		         System.out.println("==============================get结束!==========================");

	}
	
	public static void main(String[] args) {
		//Timer timer=new Timer();
		//timer.schedule(new D2s(), 1*60*1000);
		try {
			D2SMethod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
