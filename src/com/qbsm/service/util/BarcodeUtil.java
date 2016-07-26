package com.qbsm.service.util;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code39Encoder;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

public class BarcodeUtil {
	public static void createBarcode(String filePath, String barcode) {  		
		try {  
			JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(), 
		    		  WidthCodedPainter.getInstance(), 
		    		  EAN13TextPainter.getInstance());  
		      localJBarcode.setEncoder(Code39Encoder.getInstance());  
		      localJBarcode.setPainter(WideRatioCodedPainter.getInstance());  
		      localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());
		      localJBarcode.setShowCheckDigit(false);
			BufferedImage bufferedImage = localJBarcode.createBarcode(barcode);  
			saveToPNG(bufferedImage, new StringBuffer(filePath).append(".png").toString());
		} catch (Exception localException) {  
			localException.printStackTrace();  
		}  
	}  

	public static void saveToJPEG(BufferedImage paramBufferedImage, String paramString) {  
		saveToFile(paramBufferedImage, paramString, "jpeg");  
	}  

	public static void saveToPNG(BufferedImage paramBufferedImage, String filePath) {  
		saveToFile(paramBufferedImage, filePath, "png");  
	}  

	public static void saveToGIF(BufferedImage paramBufferedImage, String paramString) {  
		saveToFile(paramBufferedImage, paramString, "gif");  
	}  

	public static void saveToFile(BufferedImage bufferedImage, String filePath, String type) { 
		try {  
			FileOutputStream localFileOutputStream = new FileOutputStream(filePath);  
			ImageUtil.encodeAndWrite(bufferedImage, type, localFileOutputStream, 96, 96);  
			localFileOutputStream.close();  
		} catch (Exception localException) {  
			localException.printStackTrace();  
		}
	}  

}
