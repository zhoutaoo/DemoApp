package com.jlt.encrypt.algorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.jlt.encrypt.algorithms.Base64;
import com.jlt.encrypt.crypto.SymmetryEncrypt;

/**
 * Desede加密算法，实现了对字符串，文件，数据流的加解密功能，加密时密如为null,可自动生成Des密钥，供加密使用，解密操作必须传入密钥
 * 否则不能进行解密操作
 * 
 * @author wanna
 * 
 */
public class DesedeTools implements SymmetryEncrypt {

	private final static String algorithm = "DESede"; // 对称加密算法

	private SecretKey desKey; // 密钥

	private FileInputStream fis;

	private FileOutputStream fos;

	public DesedeTools() {
	}

	/**
	 *加密
	 * 
	 * @param plainText
	 *            明文 字节数组形式
	 * @param key
	 *            加密的密钥，如果为null，会自动生成一个密钥供加密使用，可用getKey（）方法获得此密钥
	 * @return 密文
	 * @throws Exception
	 */
	public String encrypt(byte[] plainText, Key key) throws Exception {
		if (plainText == null || plainText.length == 0) {
			return null;
		}
		this.setDesKey(key); // 根据传入密钥情况设置加密密钥

		return Base64.encode(this.basicEncrypt(plainText)); // Base64进行编码转换
	}

	/**
	 * 加密
	 * 
	 * @param plainText
	 *            明文
	 * @param key
	 *            加密的密钥，如果为null，会自动生成一个密钥供加密使用，可用getKey（）方法获得此密钥
	 * @return 密文
	 * @throws Exception
	 */
	public String encrypt(String plainText, Key key) throws Exception {
		if (plainText == null) {
			return null;
		}
		this.setDesKey(key);

		byte[] input = plainText.getBytes();

		return Base64.encode(this.basicEncrypt(input));
	}

	/**
	 * 加密 ，对文件进行加密，加密后，密文信息存放在护展名为.djm，与file相同目录下的文件中。 例： file: F:\1.txt 密文 ：
	 * F:\1.txt.djm
	 * 
	 * @param file
	 *            明文
	 * @param key
	 *            加密所需的密钥，如果key为null，则自动生成一个密钥用于加密，加密后可通过getKey（）方法获得此密钥
	 * @return 存放有密文信息的文件
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
			this.setDesKey(key);
			fis = new FileInputStream(file);
			byte[] input = this.getByteFromStream(fis); // 获取输入流中的数据

			byte[] output = this.basicEncrypt(input);

			String cipherFilePath = file.getPath() + ".djm";

			File fileOut = new File(cipherFilePath);

			fos = new FileOutputStream(fileOut);

			for (int i = 0; i < output.length; i++) {
				fos.write((int) output[i]); // 将密文信息写入文件
			}
			System.out.println("Des加密文件成功");
			return fileOut; // 密文
		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			this.closeStream(); // 关闭流
		}
	}

	/**
	 * 加密，对输入流中的数据进行加密处理，加密后，将密文信息存储在指定文件(cipherPath)中
	 * 
	 * @param plainText
	 *            输入流
	 * @param key
	 *            加密密钥，如果为null，则自动生成一个密钥
	 * @param cipherPath
	 *            加密后，存放密文信息的文件路径
	 * @return 存放密文信息的文件
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
			this.setDesKey(key);

			byte[] input = this.getByteFromStream(plainText); // 获取输入流中的数据信息

			byte[] output = this.basicEncrypt(input); // 加密

			File fileOut = new File(cipherPath);

			fos = new FileOutputStream(fileOut);

			for (int i = 0; i < output.length; i++) {
				fos.write((int) output[i]);
			}
			System.out.println("流加密成功！");
			return fileOut;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.closeStream();
		}
	}

	/**
	 * 
	 * 解密
	 * 
	 * @param cipherText
	 *            密文
	 * @param key
	 *            解密密钥
	 * @return 明文信息
	 * @throws Exception
	 */
	public String decrypt(byte[] cipherText, Key key) throws Exception {
		if (cipherText == null || cipherText.length == 0) {
			return null;
		}
		if (key == null) {
			return null;
		} else {
			this.desKey = (SecretKey) key;
		}
		byte[] input = Base64.decode(cipherText); // 用Base64对加密后的字节数组进行解码
		return new String(this.basicDecrypt(input));
	}

	/**
	 * 解密 如果解密密钥key为null，则不能进行解密操作
	 * 
	 * @param cipherText
	 *            密文
	 * @param key
	 *            解密所需的密钥Key
	 * @return 明文信息（字符串形式）
	 * @throws Exception
	 */
	public String decrypt(String cipherText, Key key) throws Exception {
		if (cipherText == null) {
			return null;
		}
		if (key == null) {
			return null;
		} else {
			this.desKey = (SecretKey) key;
		}

		byte[] input = Base64.decode(cipherText);

		return new String(this.basicDecrypt(input));
	}

	/**
	 * 解密，对扩展名为.djm，存有密文信息的文件进行解密操作，解密存放明文件信息的文件与cipherFile在同一目录下 例： cipherFile
	 * F;\1.txt.djm 解密后存放明文信息的文件为 F:\1.txt
	 * 
	 * @param cipherFile
	 *            存有密文信息的文件 扩展名为.djm
	 * @param key
	 *            解密密钥
	 * @return 存有明文件信息的文件
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

		this.desKey = (SecretKey) key; // 设置解密密钥

		try {
			String strPath = cipherFile.getPath();
			if (!strPath.substring(strPath.length() - 4).toLowerCase().equals(
					".djm")) {
				// 只对扩展名为.djm，存储密文信息的文件进行解密处理
				return null;
			}

			fis = new FileInputStream(cipherFile);

			byte[] input = this.getByteFromStream(fis);

			byte[] output = this.basicDecrypt(input);

			String outFilePath = strPath.substring(strPath.length() - 4);

			File fileOut = new File(outFilePath); // 解密后 存储明文信息的文件

			fos = new FileOutputStream(fileOut);

			for (int i = 0; i < output.length; i++) {
				fos.write((int) output[i]);
			}
			System.out.println("文件解密成功");
			return fileOut;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeStream();
		}

		return null;
	}

	/**
	 * 解密 对输入流中的密文数据解密，解密后的明文存储在指定文件中（plainPath）
	 * 
	 * @param cipherText
	 *            存有密文信息的输入流
	 * @param key
	 *            解密所需的密钥
	 * @param plainPath
	 *            解密后，明文所存放的文件路径
	 * @return 解密后，存有明文信息的文件
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
		try {
			this.desKey = (SecretKey) key;

			byte[] input = this.getByteFromStream(cipherText); // 获取输入流中的数据

			byte[] output = this.basicDecrypt(input); // 解密

			File outputFile = new File(plainPath);
			fos = new FileOutputStream(outputFile);

			for (int i = 0; i < output.length; i++) {
				fos.write((int) output[i]);
			}
			System.out.println("流解密成功");
			return outputFile;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.closeStream();
		}
	}

	/**
	 * 获得加密/解密操作的密钥
	 * 
	 * @return 加密/解密操作所用的密钥Key
	 * @throws Exception
	 */
	public Key getKey() throws Exception {
		return desKey;
	}

	/**
	 * 解密处理后的密文数据（字节数组形式）
	 * 
	 * @param input
	 *            要进行加密的字节数据
	 * @return 加密处理后的密文数据（字节数组形式）
	 * @throws Exception
	 */
	private byte[] basicEncrypt(byte[] input) throws Exception {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(algorithm); // 鍒涘缓鍔犲瘑鎵?闇?鐨凜ipher绫?

			cipher.init(Cipher.ENCRYPT_MODE, this.desKey); // 鐢ㄥ瘑閽ュ垵濮嬪寲姝?
															// cipher銆?

			return cipher.doFinal(input); // 瀹屾垚鍔犲瘑杩愮畻
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 *实现基本的解密功能，供其它方法调用
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

			cipher.init(Cipher.DECRYPT_MODE, this.desKey); // 鐢ㄥ瘑閽ュ垵濮嬪寲姝?
															// cipher銆?

			return cipher.doFinal(input); //

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 设置加密密钥，如果key为null，则调用generateDesKey（）方法生成Des密钥Key供加密使用
	 * 如果非null,则使用传入的密钥进行加密操作
	 * 
	 * @param key
	 * @throws Exception
	 */
	private void setDesKey(Key key) throws Exception {
		if (key == null) {
			desKey = (SecretKey) this.generateDesKey();
		} else {
			desKey = (SecretKey) key;
		}
	}

	/**
	 * 生成Desede算法的密钥
	 * 
	 * @return
	 */
	private Key generateDesKey() throws Exception {

		// KeyGenerator锟?
		KeyGenerator keyGen = null;
		SecretKey sekey = null;
		try {
			keyGen = KeyGenerator.getInstance(algorithm); // 根据加密算法获得KeyGenerator对象，密钥生成器
			keyGen.init(168); // 初始化密钥长度
			sekey = keyGen.generateKey(); // 生成 密钥
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw e;
		}

		return sekey;
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
