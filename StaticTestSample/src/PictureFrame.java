import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PictureFrame extends JFrame {
	public PictureFrame() {
		super("emmm");
		Container contentpane = this.getContentPane();
		contentpane.setLayout(new FlowLayout());
		BufferedImage buf = null;
		try {
			buf = ImageIO.read(GameFrame.file);
		}catch (Exception e) {
			
		}
		ImageIcon icon = new ImageIcon(buf);
		JLabel label = new JLabel(icon);
		contentpane.add(label);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(500, 150, GamePanel.ImageWidth, GamePanel.ImageHeight+50);
	}
}
