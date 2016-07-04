package com.jlt.encrypt.crypto;

import java.io.File;
import java.io.InputStream;
import java.security.Key;

import com.jlt.encrypt.encrypt.Encryption;

/**
 * 消息摘要接口，摘要算法需要实现此接口，消息摘要不需要密钥，为null即可
 * @author wanna
 *
 */
public interface MessageDigester extends Encryption
{
	/**
	 * 对文件进行摘要处理，返回摘要结果
	 * @param file    要进行摘要处理的文件
	 * @param key     null 
	 * @return        摘要后的定长字符串
	 * @throws Exception
	 */
	public String encrypt(File file, Key key) throws Exception;
	
	/**
	 * 对输入流中的数据进行摘要处理
	 * @param plainText 存有数据的输入流
	 * @param key       null
	 * @return          摘要后的定长字符串
	 * @throws Exception
	 */
	public String encrypt(InputStream plainText,Key key) throws Exception;
}
