/**
 * 创建日期：2013-2-6
 * 作   者： 周涛
 *
 * 
 */
package com.jlt.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * @author 周涛
 * 
 */
public class Zxing {
	public static final int BLACK = 0xFF000000;
	public static final int WHITE = 0xFFFFFFFF;
	public static String imgPath = "D://test.png";
	public static String str = "好好学习天天向上！hello123";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// qrCode();
		Zxing.encode();
		Zxing.decode();
	}

	public static void qrCode() {
		QRCodeWriter writer = new QRCodeWriter();
		try {
			BitMatrix matrix = writer.encode(str, BarcodeFormat.QR_CODE, 200, 200);
			MatrixToImageWriter.writeToFile(matrix, "png", new File(imgPath));
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void encode() {
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 200, 200, hints);
			File file = new File(imgPath);
			writeToFile(bitMatrix, "png", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		ImageIO.write(image, format, file);
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	// 解码
	public static void decode() {
		try {
			BufferedImage image = ImageIO.read(new File(imgPath));
			if (image == null) {
				System.out.println("Could not decode image");
				return;
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
			hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
			// 解码设置编码方式为：utf-8，
			Result result = new MultiFormatReader().decode(bitmap, hints);
			String resultStr = result.getText();
			System.out.println("解析后内容：" + resultStr);
		} catch (IOException ioe) {
			System.out.println(ioe.toString());
		} catch (ReaderException re) {
			System.out.println(re.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
