package com.jlt.encrypt.encrypt;

import java.security.Key;

/**
 * 加密组件的最上层接口，提供两种最基础的加密方法
 * @author wanna
 *
 */
public interface Encryption
{
	/**
	 * 加密
	 * @param plainText 明文，字节数组形式
	 * @param key       加密操作所需的密钥
	 * @return          加密后的结果
	 * @throws Exception
	 */
	public String encrypt(byte[] plainText,Key key) throws Exception;
	
	/**
	 * 加密
	 * @param plainText 明文，字符串形式
	 * @param key       加密所需密钥
	 * @return          加密结果（密文）
	 * @throws Exception
	 */
	public String encrypt(String plainText, Key key) throws Exception;
	
}
