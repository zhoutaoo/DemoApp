package com.jlt.encrypt.algorithms;

import java.security.Key;

import com.jlt.encrypt.crypto.MessageDigester;
import com.jlt.encrypt.crypto.SymmetryEncrypt;

/**
 * 测试类
 * 
 * @author wanna
 * 
 */
public class Test {
	public static void main(String[] args) {
		Test t = new Test();
		//t.SHATest();
		 t.DESTest();
		// t.RSATest();
	}

	public void SHATest() {
		MessageDigester md = new SHATools();
		String str1 = "zhoutao";
		byte[] b = new byte[0];
		b=str1.getBytes();
		//File f = new File("E:\\55.txt");
		try {
			System.out.println("str1:"+md.encrypt(str1, null));
			System.out.println(b.length);
			System.out.println("b:"+md.encrypt(b, null));
			//System.out.println(md.encrypt(f, null));

			//FileInputStream fis = new FileInputStream(f);
			//System.out.println(md.encrypt(fis, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void DESTest() {
		SymmetryEncrypt se = new DesedeTools();
		try {
			// 对字符串加解密成功
			String str1 = "zhoutao";
			String Mstr1 = se.encrypt(str1, null);
			System.out.println("str1 encrypt:"+Mstr1);
			Key k1 = se.getKey();
			System.out.println("str1 decrypt:"+se.decrypt(Mstr1.getBytes(), k1));
			
			String str2 = "zhoutao";
			String Mstr2 = se.encrypt(str2, null);
			System.out.println("str2 encrypt:"+Mstr2);
			System.out.println("str2 decrypt:"+se.decrypt(Mstr2.getBytes(), k1));

			// 对文件加解密成功
			/*File f = new File("F:\\55.txt");
			File f2 = se.encrypt(f, null);
			Key k2 = se.getKey();
			se.decrypt(f2, k2);*/

			/*
			 * //对数据流加解密成功 InputStream is =new FileInputStream(new
			 * File("E:\\55.txt")); File f3 = se.encrypt(is, null, "E:\\5.dfs");
			 * Key k3 = se.getKey(); InputStream is2 = new FileInputStream(f3);
			 * se.decrypt(is2, k3, "D:\\5.txt");
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void RSATest() {
		RSATools rt = new RSATools();
		try {

			// 对字符串加解密成功
			String str1 = "zhoutao";
			System.out.println("str1:"+str1);

			String mstr1 = rt.encrypt(str1.getBytes(), null);
			System.out.println("str1 encrypt:"+mstr1);

			Key pubk1 = rt.getPublicKey();
			Key prik1 = rt.getPrivateKey();

			String jstr1 = rt.decrypt(mstr1.getBytes(), prik1);
			System.out.println("str1 decrypt:"+jstr1);

			String str2 = "254251028";
			System.out.println("str2:"+str2);
			String mstr2 = rt.encrypt(str2, pubk1);
			System.out.println("str2 encrypt:"+mstr2);
			System.out.println("str2 decrypt:"+rt.decrypt(mstr2, prik1));

			String str3 = "254251028";
			System.out.println("str3:"+str3);
			String mstr3 = rt.encrypt(str3, pubk1);
			System.out.println("str3 encrypt:"+mstr3);
			System.out.println("str3 decrypt:"+rt.decrypt(mstr3, prik1));
			// 对文件加解密成功
			/*
			 * File f = new File("F:\\55.txt"); System.out.println(f.length());
			 * File ef = rt.encrypt(f, null); Key prik2= rt.getPrivateKey();
			 * rt.decrypt(ef, prik2);
			 */

			// 对数据流加解密成功
			/*
			 * InputStream iss = new FileInputStream(new File("F:\\55.txt"));
			 * File f5 = rt.encrypt(iss, null, "E:\\5.fjm"); InputStream iss2 =
			 * new FileInputStream(f5); Key key = rt.getPrivateKey();
			 * rt.decrypt(iss2, key, "E:\\5555.txt");
			 */
			/*
			 * 对字符串签名/验证成功 String str3 ="146849sdfs锟斤拷";
			 * System.out.println(str3);
			 * 
			 * String sstr3 =rt.digitalSignature(str3, null); Key pubkey3 =
			 * rt.getPublicKey(); Key prikey3 = rt.getPrivateKey();
			 * System.out.println(sstr3);
			 * System.out.println(rt.validateSignature(str3, sstr3, pubkey3));
			 */
			/*
			 * // 对文件签名/验证成功 File f3 = new File("F:\\55.txt"); File sf3=
			 * rt.digitalSignature(f3, null); Key pk3= rt.getPublicKey();
			 * System.out.println(rt.validateSignature(f3, sf3, pk3));
			 */

			// 对数据流签名/验证成功
			/*
			 * InputStream is = new FileInputStream(new File("E:\\55.txt"));
			 * File f4 = rt.digitalSignature(is, null,null); Key pk4 =
			 * rt.getPublicKey(); InputStream is2 = new FileInputStream(new
			 * File("E:\\55.txt")); InputStream sign = new FileInputStream(f4);
			 * System.out.println(rt.validateSignature(is2,sign,pk4));
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}