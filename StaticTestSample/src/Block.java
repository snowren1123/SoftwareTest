import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Block extends JButton{
	
    public static int row = 4;
    public static int col = 4;
	private static int WIDTH;//每个块的宽度
	private static int HEIGHT;//每个块的高度
	private int num;//每个块的ID
	
	
	//初始化
	public Block (ImageIcon icon, int num, int width, int height){
		
		this.setIcon(icon);
		Block.HEIGHT = height;
		Block.WIDTH = width;
		this.setSize(WIDTH, HEIGHT);
		this.num = num;
		
	}
	
	
    public void move(String dir)//移动
    {
        Rectangle rec = this.getBounds();
        if (dir.equals("UP")) {
        	this.setLocation(rec.x, rec.y + HEIGHT);
		}
        else if (dir.equals("DOWN")) {
        	this.setLocation(rec.x, rec.y - HEIGHT);
		}
        else if (dir.equals("LEFT")) {
        	this.setLocation(rec.x - WIDTH, rec.y);
		}
        else if (dir.equals("RIGHT")) {
        	this.setLocation(rec.x + WIDTH, rec.y);
		}
    }

    public int getnum() {
        return num;
    }

    public int getX()
    {
        return this.getBounds().x;
    }

    public int getY()
    {
        return this.getBounds().y;
    }
	
	
}
