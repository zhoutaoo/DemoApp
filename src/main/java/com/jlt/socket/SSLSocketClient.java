package com.jlt.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

public class SSLSocketClient {
	static int port = 16666;

	public static void startSSLClient() throws IOException {
		int port = 16666;// 要连接的服务器端口
		String serverAdd = "localhost";// 要连接的服务器地址192.168.1.39
		try {
			System.setProperty("javax.net.ssl.trustStore", "D:/Work/Workspaces/Eclipse/DemoApp/target/classes/SSLClentSocket.jks");// 设置可信任的密钥仓库
			System.setProperty("javax.net.ssl.trustStorePassword", "123456"); // 设置可信任的密钥仓库的密码
			SSLSocketFactory sslsf = (SSLSocketFactory) SSLSocketFactory.getDefault();// 利用工厂来创建SSLSocket安全套接字
			Socket csocket = sslsf.createSocket(serverAdd, port);// 创建并连接服务器
			System.out.println("Client OK~");
			System.out.println("===============");
			System.out.println("");
			// 以下代码同socket通讯实例中的代码
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(csocket.getInputStream()));// 接受到的信息
			PrintStream socketOut = new PrintStream(csocket.getOutputStream());// 要发送的信息
			BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));// 用户输入信息
			String s;
			while (true) {
				System.out.print("Client Message: ");
				s = userIn.readLine();
				socketOut.println(s);
				if (s.trim().equals("BYE"))
					break;
				else {
					System.out.println("Please wait Server Message..");
					System.out.println("");
				}
				s = socketIn.readLine();
				System.out.println("Server Message: " + s);
				if (s.trim().equals("BYE"))
					break;
			}
			socketIn.close();
			socketOut.close();
			userIn.close();
			csocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			startSSLClient();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}