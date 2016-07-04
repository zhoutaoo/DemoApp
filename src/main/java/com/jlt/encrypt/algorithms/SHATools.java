package com.jlt.encrypt.algorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.jlt.encrypt.crypto.MessageDigester;

/**
 * SHA摘要算法的实现类
 * 
 * @author Wanna
 * 
 */

public class SHATools implements MessageDigester {
	private static final String algorithm = "SHA"; // 数字摘要算法

	private MessageDigest SHAmes;

	public MessageDigest getSHAmes() {
		return SHAmes;
	}

	public void setSHAmes(MessageDigest ames) {
		SHAmes = ames;
	}

	/**
	 * 构造方法，创建MessageDigest对象，供消息摘要使用
	 * 
	 */
	public SHATools() {
		try {
			SHAmes = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 消息摘要，对字节数组操作，无需密钥key，传入null即可,
	 */
	public String encrypt(byte[] plainText, Key key) throws Exception {
		if (plainText == null || plainText.length == 0) {
			return null;
		}
		if (key != null) {
			return null; // 消息摘要不需要传入密钥
		}

		return byte2hex(this.basicDigester(plainText));
		// return new String(this.DigestePlain(plainText));

	}

	/**
	 * 消息摘要，对字符串操作，无需密钥key，传入null即可,
	 */
	public String encrypt(String plainText, Key key) throws Exception {
		if (plainText == null) {
			return null;
		}
		if (key != null) {
			return null;
		}

		byte[] input = plainText.getBytes();

		return byte2hex(this.basicDigester(input));
		// return new String(this.DigestePlain(input));
	}

	/**
	 * 消息摘要，对文件操作，无需密钥key，传入null即可,
	 */
	public String encrypt(File file, Key key) throws Exception {
		if (file == null) {
			return null;
		}
		if (!file.exists() || file.isDirectory()) {
			return null;
		}
		if (key != null) {
			return null;
		}

		byte[] input = new byte[(int) file.length()];
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
			for (int i = 0; i < file.length(); i++) {
				input[i] = (byte) fis.read();
			}
			// return new String(this.DigestePlain(input));
			return byte2hex(this.basicDigester(input));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			fis.close();
		}
	}

	/**
	 * 消息摘要，对输入流中的数据操作，无需密钥key，传入null即可,
	 */
	public String encrypt(InputStream plainText, Key key) throws Exception {
		if (plainText == null) {
			return null;
		}
		if (key != null) {
			return null;
		}
		int length = -1;
		ArrayList<Byte> temp = new ArrayList<Byte>();
		try {
			while ((length = plainText.read()) != -1) {
				temp.add((byte) length);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		byte[] input = new byte[temp.size()];

		for (int i = 0; i < temp.size(); i++) {
			Byte bt = (Byte) temp.get(i);
			input[i] = bt.byteValue();
		}
		// return new String(this.DigestePlain(input));
		return byte2hex(this.basicDigester(input));
	}

	/**
	 * 消息摘要的基本方法，供其他方法调用。
	 * 
	 * @param plain
	 *            要进行摘要处理的字节数据
	 * @return 摘要计算后的数据
	 */
	private byte[] basicDigester(byte[] input) {
		MessageDigest md = this.getSHAmes();

		md.update(input);

		return md.digest();
	}

	/**
	 * 将字节转换成字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
}
