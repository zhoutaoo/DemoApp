package com.jlt.encrypt.crypto;

import java.io.File;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyPair;

import com.jlt.encrypt.encrypt.Encryption;

/**
 * 非对称加密算法的上层接口，提供加/解密、数字签名/验证操作所需的方法
 * 加密/解密操作（公钥加密，私钥解密）
 * 数字签名/验证（私钥加密，公钥验证）
 * @author wanna
 *
 */
public interface AsymmetryEncrypt extends Encryption
{
	/**
	 * 加密文件
	 * @param file  待加密文件
	 * @param key    加密所需公钥
	 * @return  存有密文信息的文件，扩展名为.fjm，路径与file相同
	 * 例： 明文  ：F:\1.txt   
	 *      密文  ：F:\1.txt.fjm
	 * @throws Exception
	 */
	public File encrypt(File file, Key key) throws Exception;
	
	/**
	 * 加密 ，对输入流中的数据进行加密，加密后将密文信息存储在指定路径cipherPath的文件中
	 * @param plainText   输入流
	 * @param key         加密所需的公钥
	 * @param cipherPath  指定的存储密文信息的文件路径
	 * @return            指定路径下，存储密文信息的文件
	 * @throws Exception
	 */
	public File encrypt(InputStream plainText, Key key,String cipherPath) throws Exception;

	/**
	 * 解密
	 * @param cipherText 存有密文件信息的字节数组
	 * @param key        解密所需的私钥
	 * @return           解密后的明文字符串
	 * @throws Exception
	 */
	public String decrypt(byte[] cipherText, Key key) throws Exception;

	/**
	 * 对字符串进行解密操作
	 * @param cipherText  密文字符串
	 * @param key         解密所需的私钥
	 * @return            明文字符串
	 * @throws Exception
	 */
	public String decrypt(String cipherText, Key key) throws Exception;

	/**
	 * 解密，对扩展名为.fjm的密文文件进行解密
	 * @param cipherFile  存有密文信息的文件扩展名为.fjm
	 * @param key        解密所需的私钥
	 * @return          存有明文信息的文件
	 *     例 ：cipherFile 为 F:\1.txt.fjm
	 *          解密后        F:\1.txt    存放明文信息  
	 * @throws Exception
	 */
	public File decrypt(File cipherFile, Key key) throws Exception;

	/**
	 * 解密 ，对文件流进行解密操作
	 * @param cipherText   存有密文信息的输入流
	 * @param key          解密所需的私钥
	 * @param plainPath    存放明文信息文件的指定路径
	 * @return  指定路径下，存放明文信息的文件
	 * @throws Exception
	 */
	public File decrypt(InputStream cipherText, Key key,String plainPath) throws Exception;
	
	/**
	 * 数字签名
	 * @param plainText 明文字符串
	 * @param key       签名所需的私钥
	 * @return          签名后的信息字符串
	 * @throws Exception
	 */
	public String digitalSignature(String plainText, Key key) throws Exception;
	
	/**
	 * 签名验证
	 * @param plainText   明文字符串
	 * @param signMessage 签名信息
	 * @param key         签名验证的公钥
	 * @return            验证结果 
	 * 					   true  签名正确
	 *                     false 签名错误
	 * @throws Exception
	 */
	public boolean validateSignature(String plainText, String signMessage, Key key) throws Exception;
	
	/**
	 * 数字签名
	 * @param file   待签名文件
	 * @param key    签名所需的私钥
	 * @return       存有签名信息的文件,扩展名为.sig
	 *              例 file       F:\1.txt
	 *              签名信息文件   F:\1.txt.sig
	 *       
	 * @throws Exception
	 */
	public File digitalSignature(File file, Key key) throws Exception;
	
	/**
	 * 签名验证
	 * @param plainText   	明文
	 * @param signMessage 	在有签名信息的文件，扩展名为.sig
	 * @param key         	签名验证所需公钥
	 * @return           	验证结果 
	 * 						true  签名正确
	 *                     	false 签名错误
	 * @throws Exception
	 */
	public boolean validateSignature(File plainText, File signMessage, Key key) throws Exception;
	
	/**
	 * 数字签名
	 * @param plainStream 要进行数字签名的数据流
	 * @param key         数字签名所需的私钥
	 * @param signPath    签名后，存放签名信息的文件路径
	 * @return   存有签名信息的文件
	 * @throws Exception
	 */
	public File digitalSignature(InputStream plainStream, Key key,String signPath) throws Exception;
	
	/**
	 * 签名验证
	 * @param plainStream  存有明文信息的输入流
	 * @param signStream   存有签名信息的输入流
	 * @param key          签名验证所需的公钥
	 * @return            
	 * @throws Exception
	 */
	public boolean validateSignature(InputStream plainStream, InputStream signStream, Key key) throws Exception;

	/**
	 * 获得公钥
	 * @return
	 * @throws Exception
	 */
	public Key getPublicKey() throws Exception;
	
	/**
	 * 获得私钥
	 * @return
	 * @throws Exception
	 */
	public Key getPrivateKey() throws Exception;
	
	/**
	 * 获得加密算法的密钥对
	 * @return
	 * @throws Exception
	 */
	public KeyPair getKeyPair() throws Exception;
	
	
}
