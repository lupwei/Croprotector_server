package servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class PictureClass {
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();  
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder(); 
    
    static String ImageToBase64(String url) throws IOException {
    	File f=new File(url);
		BufferedImage bi=ImageIO.read(f);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(bi, "jpg", baos);
		byte[] bytes=baos.toByteArray();
		return encoder.encodeBuffer(bytes).trim();
    }
    
    static void Base64ToImage(String base64Str,String url) throws IOException {
    	byte[] bytes1=decoder.decodeBuffer(base64Str);
    	ByteArrayInputStream bais=new ByteArrayInputStream(bytes1);
    	BufferedImage bi1=ImageIO.read(bais);
    	File f=new File(url);
    	ImageIO.write(bi1, "jpg", f);
    }
}
