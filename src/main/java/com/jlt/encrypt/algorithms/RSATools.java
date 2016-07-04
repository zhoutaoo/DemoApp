package com.jlt.encrypt.algorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;

import javax.crypto.Cipher;

import com.jlt.encrypt.crypto.AsymmetryEncrypt;

/**
 * RSA非对称加密算法的实现类，实现了对字符串、文件、数据流的加密（公钥加密）、解密（私钥解密）、 数字签名（私钥加密）和签名验证（公钥验证）功能
 * 此加密算法操作的数据长度不能大于117位。
 * 
 * @author wanna
 * 
 */
public class RSATools implements AsymmetryEncrypt {
	private final static String algorithm = "RSA"; // 非对称加密算法RSA

	public static final String SIGNALGORITHM = "SHA1WithRSA"; // 数字签名算法

	private KeyPair rsaKeyPair; // RSA算法的密钥对

	private PublicKey pubKey; // 公钥

	private PrivateKey priKey; // 私钥

	private FileInputStream fis;

	private FileOutputStream fos;

	public RSATools() {
	}

	/**
	 * 解密
	 * 
	 * @param cipherText
	 *            密文信息
	 * @param key
	 *            解密操作所需的私钥，如果为null，则不能执行解密操作
	 * @return 解密后的明文信息
	 * @throws Exception
	 */
	public String decrypt(byte[] cipherText, Key key) throws Exception {
		if (cipherText == null || cipherText.length == 0) {
			return null;
		}
		if (key == null) {
			return null;
		}

		this.priKey = (PrivateKey) key; // 设置解密所用的私钥

		byte[] input = Base64.decode(cipherText); // 用Base64对密文解码

		return new String(this.basicDecrypt(input));
	}

	/**
	 * 解密
	 * 
	 * @param cipherText
	 *            密文信息
	 * @param key
	 *            解密操作所需的私钥，如果为null，则不能执行解密操作
	 * @return 解密后的明文信息 字符串形式
	 * @throws Exception
	 */
	public String decrypt(String cipherText, Key key) throws Exception {
		if (cipherText == null) {
			return null;
		}
		if (key == null) {
			return null;
		}

		this.priKey = (PrivateKey) key;

		byte[] input = Base64.decode(cipherText);

		return new String(this.basicDecrypt(input));
	}

	/**
	 * 解密 对文件进行解密操作，文件的扩展名必须为.fjm，解密后的明文文件所在路径与密文相同。 例： 密文 ：F:\1.txt.fjm 明文：
	 * F:\1.txt
	 * 
	 * @param cipherFile
	 *            待解密的文件
	 * @param key
	 *            解密所用的私钥
	 * @return 以文件形式存储的明文信息
	 * @throws Exception
	 */
	public File decrypt(File cipherFile, Key key) throws Exception {
		if (cipherFile == null) {
			return null;
		}
		if (!cipherFile.exists() || cipherFile.isDirectory()) {
			return null;
		}

		if (key == null) {
			return null;
		}

		this.priKey = (PrivateKey) key;

		try {
			String strPath = cipherFile.getPath();
			if (!strPath.substring(strPath.length() - 4).toLowerCase().equals(
					".fjm")) // 判断文件的扩展名是否是fjm，不是停止解密操作
			{
				return null;
			}

			fis = new FileInputStream(cipherFile);

			byte[] input = this.getByteFromStream(fis); // 获取文件流中的密文数据

			byte[] output = this.basicDecrypt(input); // 解密

			String outFilePath = strPath.substring(strPath.length() - 4);

			File fileOut = new File(outFilePath); // 存储明文信息的文件

			fos = new FileOutputStream(fileOut);

			for (int i = 0; i < output.length; i++) {
				fos.write((int) output[i]);
			}
			System.out.println("解密成功");
			return fileOut;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeStream();
		}

		return null;
	}

	/**
	 * 解密 对输入流中的密文信息进行解密
	 * 
	 * @param cipherText
	 *            存有密文数据的输入流
	 * @param key
	 *            解密的私钥，如果为null，则不能进行解密操作。
	 * @param plainPath
	 *            存储解密后的明文信息的文件路径
	 * @return 指定路径下，存有明文信息的文件
	 * @throws Exception
	 */
	public File decrypt(InputStream cipherText, Key key, String plainPath)
			throws Exception {
		if (cipherText == null || key == null) {
			return null;
		}
		if (plainPath == null || plainPath.equals("")) {
			return null;
		}
		this.priKey = (PrivateKey) key;
		byte[] input = this.getByteFromStream(cipherText);
		byte[] output = this.basicDecrypt(input);
		File outputFile = new File(plainPath);
		fos = new FileOutputStream(outputFile);
		for (int i = 0; i < output.length; i++) {
			fos.write((int) output[i]);
		}
		System.out.println("解密成功");
		return outputFile;
	}

	/**
	 * 加密
	 * 
	 * @param plainText
	 *            明文
	 * @param key
	 *            加密所用的公钥，如果为null则自动生成一个密钥对，使用其中的公钥进行加密
	 * @return 加密后的密文，字符串形式
	 * @throws Exception
	 */
	public String encrypt(byte[] plainText, Key key) throws Exception {
		if (plainText == null || plainText.length == 0) {
			return null;
		}
		this.setKeyPairWhenEncrypt(key);

		return Base64.encode(this.basicEncrypt(plainText));
	}

	/**
	 * 加密
	 * 
	 * @param plainText
	 *            明文
	 * @param key
	 *            加密所用的公钥，如果为null则自动生成一个密钥对，使用其中的公钥进行加密
	 * @return 加密后的密文，字符串形式
	 * @throws Exception
	 */
	public String encrypt(String plainText, Key key) throws Exception {
		if (plainText == null) {
			return null;
		}
		this.setKeyPairWhenEncrypt(key);

		byte[] input = plainText.getBytes();

		return Base64.encode(this.basicEncrypt(input));
	}

	/**
	 * 加密文件，加密后在file路径下生成一扩展名为的文件，用于存储密文信息 例： 明文： F:\1.txt 密文文件为：F:\1.txt.fjm
	 * 
	 * @param file
	 *            待加密的文件
	 * @param key
	 *            加密所用的公钥，如果为null则自动生成一个密钥对，使用其中的公钥进行加密
	 * @return 扩展名为.fjm、存有密文信息的文件
	 * @throws Exception
	 */
	public File encrypt(File file, Key key) throws Exception {

		if (file == null) {
			return null;
		}
		if (!file.exists() || file.isDirectory()) {
			return null;
		}

		try {
			this.setKeyPairWhenEncrypt(key);

			fis = new FileInputStream(file);

			byte[] input = this.getByteFromStream(fis);

			byte[] output = this.basicEncrypt(input);

			String cipherFilePath = file.getPath() + ".fjm"; // 在
																// file路径下创建一扩展名为.fjm的文件

			File fileOut = new File(cipherFilePath);

			fos = new FileOutputStream(fileOut);

			for (int i = 0; i < output.length; i++) {
				fos.write((int) output[i]);
			}
			System.out.println("加密成功");
			return fileOut;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			this.closeStream();
		}
	}

	/**
	 * 加密
	 * 
	 * @param plainText
	 *            存有明文信息的输入流
	 * @param key
	 *            加密所需公钥Key如果传null，则自动生成一个密钥对，用其中的公钥进行加密
	 * @param cipherPath
	 *            加密后，存放密文信息的文件路径
	 * @return 存有密文件信息的文件
	 * @throws Exception
	 */
	public File encrypt(InputStream plainText, Key key, String cipherPath)
			throws Exception {
		if (plainText == null) {
			return null;
		}
		if (cipherPath == null || cipherPath.equals("")) {
			return null;
		}
		try {
			this.setKeyPairWhenEncrypt(key);

			byte[] input = this.getByteFromStream(plainText);
			byte[] output = this.basicEncrypt(input);

			File outputFile = new File(cipherPath);
			fos = new FileOutputStream(outputFile);
			for (int i = 0; i < output.length; i++) {
				fos.write((int) output[i]);
			}
			System.out.println("加密成功");
			return outputFile;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.closeStream();
		}
	}

	/**
	 * 数字签名
	 * 
	 * @param plainText
	 *            明文
	 * @param key
	 *            数字签名所需私钥，如果key为null,则自动为其生成一个密钥对，用其私钥进行签名
	 * @return 字符串形式的签名信息
	 * @throws Exception
	 */
	public String digitalSignature(String plainText, Key key) throws Exception {
		try {
			if (plainText == null) {
				return null;
			}

			this.setKeyPairWhenSignature(key);

			byte[] input = plainText.getBytes();

			return Base64.encode(this.basicSignature(input));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 验证签名
	 * 
	 * @param plainText
	 *            明文字符串
	 * @param signMessage
	 *            签名信息
	 * @param key
	 *            验证签名所需的公钥，为null则不能进行验证
	 * @return 验证结果 true 签名正确 false签名错误
	 * @throws Exception
	 */
	public boolean validateSignature(String plainText, String signMessage,
			Key key) throws Exception {
		try {
			if (plainText == null || signMessage == null) {
				return false;
			}
			if (key == null) {
				return false;
			} else {
				this.pubKey = (PublicKey) key;
			}
			byte[] plain = plainText.getBytes();
			byte[] input = Base64.decode(signMessage);
			return this.basicValidateSign(plain, input);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 对文件进行数字签名，存有签名信息的文件扩展名为.sig，与file在相同目录下 例: file : F:\1.txt 签名信息文件：
	 * F:\1.txt.sig
	 * 
	 * @param file
	 *            待签名的文件
	 * @param key
	 *            数字签名所需公钥，传入null，则自动生成一组密钥对，用其私钥进行签名操作
	 * @return 存有签名信息的文件
	 * @throws Exception
	 */
	public File digitalSignature(File file, Key key) throws Exception {

		if (file == null) {
			return null;
		}
		if (!file.exists() || file.isDirectory()) {
			return null;
		}

		try {

			this.setKeyPairWhenSignature(key);
			fis = new FileInputStream(file);

			byte[] input = this.getByteFromStream(fis);

			byte[] output = this.basicSignature(input);

			// System.out.println("数字签名信息" + Base64.encode(output));

			String cipherFilePath = file.getPath() + ".sig"; // 存储签名信息的文件路径

			File fileOut = new File(cipherFilePath);

			fos = new FileOutputStream(fileOut);

			for (int i = 0; i < output.length; i++) {
				fos.write((int) output[i]);
			}
			System.out.println("数字签名成功");
			return fileOut;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			this.closeStream();
		}
	}

	/**
	 * 验证签名信息，对.sig存有签名信息的文件进行验证
	 * 
	 * @param plainText
	 *            明文
	 * @param signMessage
	 *            签名信息文件，扩展名为.sig
	 * @param key
	 *            验证签名所需公钥key
	 * @return 验证结果 true 签名正确 false签名错误
	 * @throws Exception
	 */
	public boolean validateSignature(File plainText, File signMessage, Key key)
			throws Exception {
		if (plainText == null) {
			return false;
		}
		if (!plainText.exists() || plainText.isDirectory()) {
			return false;
		}
		if (key == null) {
			return false;
		}

		this.pubKey = (PublicKey) key;

		FileInputStream fis2 = null;
		try {
			String signPath = signMessage.getPath();
			if (!signPath.substring(signPath.length() - 4).toLowerCase()
					.equals(".sig"))// 判断签名信息文件护展名是否为.sig
			{
				return false;
			}
			fis = new FileInputStream(plainText);

			byte[] plain = this.getByteFromStream(fis);

			fis2 = new FileInputStream(signMessage);

			byte[] input = this.getByteFromStream(fis2);

			return this.basicValidateSign(plain, input);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis2 != null) {
				fis.close();
			}
			this.closeStream();
		}
		return false;
	}

	/**
	 * 数字签名
	 * 
	 * @param plainStream
	 *            存有明文信息的输入流
	 * @param key
	 *            数字签名所需的私钥
	 * @param signPath
	 *            存放签名信息的文件路径
	 * @return 存有签名信息的文件
	 * @throws Exception
	 */
	public File digitalSignature(InputStream plainStream, Key key,
			String signPath) throws Exception {
		if (plainStream == null) {
			return null;
		}

		this.setKeyPairWhenSignature(key);

		try {
			byte[] input = this.getByteFromStream(plainStream);

			byte[] output = this.basicSignature(input);

			File outSignMessage = new File(signPath); // 存有签名信息的文件

			fos = new FileOutputStream(outSignMessage);
			for (int i = 0; i < output.length; i++) {
				fos.write((int) output[i]);
			}

			System.out.println("签名成功");
			return outSignMessage;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.closeStream();
		}
	}

	/**
	 * 验证签名信息
	 * 
	 * @param plainStream
	 *            存有明文信息的输入流
	 * @param signStream
	 *            存有签名信息的输入流
	 * @param key
	 *            验证签名所需的公钥key
	 * @return 验证结果 true 签名正确 false签名错误
	 * @throws Exception
	 */
	public boolean validateSignature(InputStream plainStream,
			InputStream signStream, Key key) throws Exception {
		if (plainStream == null || signStream == null || key == null) {
			return false;
		}
		this.pubKey = (PublicKey) key;

		byte[] plain = this.getByteFromStream(plainStream);
		byte[] input = this.getByteFromStream(signStream);

		System.out.println("验证结束");
		return this.basicValidateSign(plain, input);
	}

	/**
	 * 返回密钥对
	 */
	public KeyPair getKeyPair() throws Exception {
		return this.rsaKeyPair;
	}

	/**
	 * 返回私钥
	 */
	public Key getPrivateKey() throws Exception {
		return this.priKey;
	}

	/**
	 * 返回公钥
	 */
	public Key getPublicKey() throws Exception {
		return this.pubKey;
	}

	/**
	 * 生成RSA密钥对
	 * 
	 * @return 密钥对KeyPair
	 * @throws Exception
	 */
	private KeyPair generateKeyPair() throws Exception {
		KeyPair kPair = null;
		try {
			// 根据加密算法获得KeyPairGenerator对象
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
			// 设置密钥长度
			keyGen.initialize(1024);
			kPair = keyGen.generateKeyPair();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw e;
		}
		return kPair;
	}

	/**
	 * 加密操作时，设置加密公钥 如果key 为null，则自动生成一个密钥对，用其中的公钥进行加密 如果不为null，则作为公钥进行加密操作
	 * 
	 * @param key
	 *            密钥
	 * @return 加密所需公钥是否设置成功
	 * @throws Exception
	 */
	private Boolean setKeyPairWhenEncrypt(Key key) throws Exception {

		try {
			if (key == null) {
				this.rsaKeyPair = this.generateKeyPair();
				this.pubKey = this.rsaKeyPair.getPublic();
				this.priKey = this.rsaKeyPair.getPrivate();
			} else {
				this.pubKey = (PublicKey) key;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	/**
	 * 数字签名时，设置签名操作所用密钥 如果密钥key不为空，则作为私钥存储 如果为null,则自动生成一组密钥对，用其中的私钥进行签名操作
	 * 
	 * @param key
	 *            密钥
	 * @return 私钥是否设置成功
	 * @throws Exception
	 */
	private Boolean setKeyPairWhenSignature(Key key) throws Exception {

		try {
			if (key == null) {
				this.rsaKeyPair = this.generateKeyPair();
				this.pubKey = this.rsaKeyPair.getPublic();
				this.priKey = this.rsaKeyPair.getPrivate();
			} else {
				this.priKey = (PrivateKey) key;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	/**
	 * 实现基本的加密功能，供其它方法调用
	 * 
	 * @param input
	 *            要进行加密的字节数据
	 * @return 加密处理后的密文数据（字节数组形式）
	 * @throws Exception
	 */
	private byte[] basicEncrypt(byte[] input) throws Exception {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(algorithm);

			cipher.init(Cipher.ENCRYPT_MODE, this.pubKey); // 用公钥pubKey初始化此Cipher

			return cipher.doFinal(input); // 加密
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * 基本的解密方法，供其它方法调用
	 * 
	 * @param input
	 *            要进行解密的字节数据
	 * @return 解密处理后的密文数据（字节数组形式）
	 * @throws Exception
	 */
	private byte[] basicDecrypt(byte[] input) throws Exception {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(algorithm);

			cipher.init(Cipher.DECRYPT_MODE, this.priKey); // 用私钥priKey初始化此cipher

			return cipher.doFinal(input); // 解密
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 实现基本的数字签名功能，供其它方法调用
	 * 
	 * @param input
	 *            要进行签名的字节数据
	 * @return 字节数组形式的签名信息
	 * @throws Exception
	 */
	private byte[] basicSignature(byte[] input) throws Exception {
		try {
			Signature sig = Signature.getInstance(SIGNALGORITHM);

			sig.initSign(this.priKey); // 用此私钥初始化此签名对象Signature

			sig.update(input);

			return sig.sign(); // 签名
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 实现基本的签名验证功能,供其它方法调用
	 * 
	 * @param plain
	 *            明文的字节数据
	 * @param input
	 *            签名信息
	 * @return 验证结果 true 签名正确 false签名错误
	 * @throws Exception
	 */
	private boolean basicValidateSign(byte[] plain, byte[] input)
			throws Exception {
		try {
			Signature sig = Signature.getInstance(SIGNALGORITHM);

			sig.initVerify(this.pubKey); // 用公钥pubKey初始化此用于Signature对象。

			sig.update(plain);

			return sig.verify(input); // 签名验证
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取输入流中的数据，以字节数组形式返回
	 * 
	 * @param is
	 *            输入流
	 * @return 以字节数组形式返回输入流中的数据
	 * @throws Exception
	 */
	private byte[] getByteFromStream(InputStream is) throws Exception {
		int length = -1;
		ArrayList<Byte> temp = new ArrayList<Byte>();

		while ((length = is.read()) != -1) {
			temp.add((byte) length);
		}

		byte[] out = new byte[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			Byte byt = (Byte) temp.get(i);
			out[i] = byt.byteValue();
		}
		return out;
	}

	/**
	 * 关闭流
	 * 
	 * @throws Exception
	 */
	private void closeStream() throws Exception {
		if (fis != null) {
			fis.close();
		}
		if (fos != null) {
			fos.close();
		}
	}

}
