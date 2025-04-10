package apparatus;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window extends JFrame{

	public Window() {
		this.setTitle("Sales Machine");
		this.setSize(1920, 1080);
		this.setUndecorated(true);
		
//		this.setIconImage(new ImageIcon(getClass().getResource("res/MajesticStylesEmblem.png")).getImage());
		try {
			this.setIconImage(ImageIO.read(new File("res/MajesticStylesEmblem.png")));
		}catch(Exception ex) {
			System.out.println("Icon Failed");
		}
		
		this.setLocationRelativeTo(null);
		
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(true);
		
		this.setVisible(true);
	}
	
}
