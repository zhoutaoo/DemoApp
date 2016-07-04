package com.jlt.encrypt.crypto;

import java.io.File;
import java.io.InputStream;
import java.security.Key;

import com.jlt.encrypt.encrypt.Encryption;

/**
 * 对称加密算法的公用接口，实现对称加密算法的加解密功能
 * @author Wanna
 *
 */
public interface SymmetryEncrypt extends Encryption
{
	/**
	 * 对文件进行加密处理
	 * @param file  要加密的文件
	 * @param key   加密操作的密钥
	 * @return     存有密文信息的文件 ，file路径下扩展名为.djm的文件
	 * 				例file    F:\1.txt
	 *               密文     F:\1.txt.djm
	 * @throws Exception
	 */
	public File encrypt(File file, Key key) throws Exception;
	
	/**
	 * 对输入流中的数据进行加密
	 * @param plainText 存有加密数据的输入流
	 * @param key       加密密钥
	 * @param cipherPath 加密后的，存放密文信息的文件所在路径
	 * @return  指定路径下的，存有密文信息的文件
	 * @throws Exception
	 */
	public File encrypt(InputStream plainText,Key key,String cipherPath) throws Exception;
	
	
	/**
	 * 解密
	 * @param cipherText   密文
	 * @param key         解密密钥
	 * @return     以字符串形式返回明文信息
	 * @throws Exception
	 */
	public String decrypt(byte[] cipherText, Key key) throws Exception;
	
	/**
	 * 解密
	 * @param cipherText  密文信息
	 * @param key         解密密钥
	 * @return        以字符串形式返回明文信息
	 * @throws Exception
	 */
	public String decrypt(String cipherText, Key key) throws Exception;
	
	/**
	 * 解密，对扩展名为.djm的密文文件进行解密操作
	 * @param cipherFile  明文
	 * @param key         解密密钥
	 * @return     存有明文信息的文件
	 * 例：  密文文件   F:\1.txt.djm
	 *       返回：     F:\1.txt 
	 * @throws Exception
	 */
	public File decrypt(File cipherFile, Key key) throws Exception;

	/**
	 * 解密
	 * @param cipherText  存有密文件信息的输入流
	 * @param key         解密密钥
	 * @param plainPath   解密后，将明文信息存放在此路径下
	 * @return    存有明文信息的文件  
	 * @throws Exception
	 */
	public File decrypt(InputStream cipherText, Key key,String plainPath) throws Exception;

	/**
	 * 获得加密/解密操作所使用的密钥Key
	 * @return  加/解密密钥Key
	 * @throws Exception
	 */
	public Key getKey() throws Exception;
}
