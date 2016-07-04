package com.jlt.socket;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class SSLSocketServer {
	public static void startSSLServer() throws IOException {
		int port = 16666;// 监听端口
		String keyFile = "D:/Work/Workspaces/Eclipse/DemoApp/target/classes/SSLServerSocket.jks";// 密钥库文件
		String keyFilePass = "123456";// 密钥库的密码
		String keyPass = "123456";// 密钥别名的密码
		SSLServerSocket sslsocket = null;// 安全连接套接字
		KeyStore ks;// 密钥库
		KeyManagerFactory kmf;// 密钥管理工厂
		SSLContext sslc = null;// 安全连接方式
		// 初始化安全连接的密钥
		try {
			ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream(keyFile), keyFilePass.toCharArray());
			// 创建管理JKS密钥库的X.509密钥管理器
			kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ks, keyPass.toCharArray());
			// 构造SSL环境，指定SSL版本为3.0，也可以使用TLSv1，但是SSLv3更加常用
			sslc = SSLContext.getInstance("SSLv3");
			// 初始化SSL环境。第二个参数是告诉JSSE使用的可信任证书的来源，
			// 设置为null是从javax.net.ssl.trustStore中获得证书。第三个参数是JSSE生成的随机数，
			// 这个参数将影响系统的安全性，设置为null是个好选择，可以保证JSSE的安全性。
			sslc.init(kmf.getKeyManagers(), null, null);
		} catch (KeyManagementException ex) {
			ex.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		}
		// 用安全连接的工厂来创建安全连接套接字
		SSLServerSocketFactory sslssf = sslc.getServerSocketFactory();
		sslsocket = (SSLServerSocket) sslssf.createServerSocket();// 创建并进入监听
		SocketAddress sa = new InetSocketAddress("localhost", port);
		sslsocket.bind(sa);
		System.out.println("Listening...");
		SSLSocket ssocket = (SSLSocket) sslsocket.accept();// 接受客户端的连接
		System.out.println("Server Connection OK~");
		System.out.println("========================");
		System.out.println("");
		// 以下代码同socket通讯实例中的代码
		BufferedReader socketIn = new BufferedReader(new InputStreamReader(ssocket.getInputStream()));
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		PrintStream socketOut = new PrintStream(ssocket.getOutputStream());
		String s;
		while (true) {
			System.out.println("Please wait client 's message..");
			System.out.println("");
			s = socketIn.readLine();
			System.out.println("Client Message: " + s);
			if (s.trim().equals("BYE"))
				break;
			System.out.print("Server Message: ");
			s = userIn.readLine();
			socketOut.println(s);
			if (s.trim().equals("BYE"))
				break;
		}
		socketIn.close();
		socketOut.close();
		userIn.close();
		sslsocket.close();
	}

	public static void main(String[] args) {
		try {
			startSSLServer();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}
