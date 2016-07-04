package com.jlt.encryption;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

/**
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class CoderTest {

	@Test
	public void encryptBASE64() throws Exception {
		String inputStr = "admin";
		System.err.println("BASE64编码");
		System.out.println("原文:" + inputStr);
		byte[] inputData = inputStr.getBytes();
		String code = Coder.encryptBASE64(inputData);
		System.out.println("BASE64编码后:" + code);
		byte[] output = Coder.decryptBASE64(code);
		String outputStr = new String(output);
		System.out.println("BASE64解码后:" + outputStr);
		// 验证BASE64加密解密一致性
		assertEquals(inputStr, outputStr);
	}

	@Test
	public void encryptMD5() throws Exception {
		String inputStr = "admin";
		byte[] inputData = inputStr.getBytes();
		// 验证MD5对于同一内容加密是否一致
		assertArrayEquals(Coder.encryptMD5(inputData),
				Coder.encryptMD5(inputData));
	}

	@Test
	public void encryptSHA() throws Exception {
		String inputStr = "admin";
		byte[] inputData = inputStr.getBytes();
		// 验证SHA对于同一内容加密是否一致
		assertArrayEquals(Coder.encryptSHA(inputData),
				Coder.encryptSHA(inputData));
	}

	@Test
	public void encryptHMAC() throws Exception {
		String inputStr = "admin";
		System.out.println("原文:" + inputStr);
		byte[] inputData = inputStr.getBytes();
		String key = Coder.initMacKey();
		System.out.println("Mac密钥:" + key);
		// 验证HMAC对于同一内容，同一密钥加密是否一致
		assertArrayEquals(Coder.encryptHMAC(inputData, key),
				Coder.encryptHMAC(inputData, key));
		BigInteger mac = new BigInteger(Coder.encryptHMAC(inputData, inputStr));
		System.out.println("HMAC:" + mac.toString(16));
	}
}
