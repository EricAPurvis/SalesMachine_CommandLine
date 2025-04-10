package loader;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageGetter {

	public static BufferedImage get(String path) {
		BufferedImage img=null;
		try {
			img = ImageIO.read(new File(path));
		}catch(Exception ex) {
			System.err.print("Failed To Get Image: "+path);
		}
		return(img);
	}
	
	public static BufferedImage spliceImage(BufferedImage img, int x, int y, int width, int height) {
		return(img.getSubimage(x, y, width, height));
	}
	
}
