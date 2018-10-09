import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;


public class GamePanel extends JPanel implements MouseListener {
	
	private Block blocks[] = new Block[Block.row*Block.col];
	public static int ImageWidth;//读取的文件的宽和高
    public static int ImageHeight;
    Block blank = null;
	
	public GamePanel() {
		this.setLayout(null);
		init();
	}
	
	//初始化
    public void init(){
    	
        BufferedImage buf = null;
        BufferedImage bufnew = null;
        ImageIcon icon = null;
        int width = 0;
        int height = 0;
        try{
            buf = ImageIO.read(GameFrame.file);//读取文件图像
            ImageWidth = buf.getWidth();
            ImageHeight = buf.getHeight();
            width = ImageWidth/Block.col;
            height = ImageHeight/Block.row;
        }catch(Exception e){
            System.out.println(e);
        }
        
        int num = 0;
        for(int i = 0; i < Block.row; i++){
        	
            for(int j = 0; j < Block.col; j++){
            	
                num = i*Block.col+j;//当前button的num，也是在数组中的下标
                if(num < Block.row*Block.col-1){
                	
                    bufnew = buf.getSubimage(width*j, height*i, width, height);
                    icon = new ImageIcon(bufnew);
                    blocks[num] = new Block(icon, num, width, height);
                    blocks[num].setLocation(width*j, height*i);
                    
                }
                else{//空白格
                	blocks[num] = new Block(null, num, width, height);
                    blocks[num].setLocation(width*j, height*i);
                    blocks[num].setBackground(Color.white);
                }
                blank = blocks[blocks.length-1];
                
            }
        }
        

        for(int i = 0; i < blocks.length; i++){
        	
            this.add(blocks[i]);
            if(i < blocks.length-1)//空白格不添加监听机制
                blocks[i].addMouseListener(this);
            
        }
        
    }//初始化完成
    
    
    public void Start(){//游戏开始，打乱
        Random random = new Random();
        for(int i = 0 ; i < blocks.length ; i++){
        	
            int block1 = random.nextInt(blocks.length);
            int block2 = random.nextInt(blocks.length);
            int x = blocks[block1].getX();
            int y = blocks[block1].getY();
            blocks[block1].setLocation(blocks[block2].getX(), blocks[block2].getY());
            blocks[block2].setLocation(x, y);//交换两个block
        }
    }
    
    public boolean IsWin()//判断游戏玩家是否赢
    {
        for(int i = 0; i < blocks.length; i++)
        {
            int x = blocks[i].getX();
            int y = blocks[i].getY();
            if(x/(ImageWidth/Block.col) + y/(ImageHeight/Block.row)*Block.col != i)
            {
                return false;
            }
        }
        return true;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        Block t = (Block) e.getSource();
        int x = blank.getX();
        int y = blank.getY();
        if(t.getY() == y && t.getX() + ImageWidth/Block.col == x)
        {
            t.move("RIGHT");
            blank.move("LEFT");
        }
        else if(t.getY() == y && t.getX() - ImageWidth/Block.col == x)
        {
            t.move("LEFT");
            blank.move("RIGHT");
        }
        else if(t.getX() == x && t.getY() + ImageHeight/Block.row == y)
        {
            t.move("UP");
            blank.move("DOWN");
        }
        else if(t.getX() == x && t.getY() - ImageHeight/Block.row == y)
        {
            t.move("DOWN");
            blank.move("UP");
        }
        
        
        
        if(IsWin())
        {
            int choice = JOptionPane.showConfirmDialog(null, "可把你牛逼坏了，再爽一盘吧？", "过关了！", 
            												JOptionPane.YES_NO_OPTION);
            if(choice == 0)
            {
                this.Start();
            }
            else
                System.exit(1);
        }
    }

    
    
    
    
    
    
    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}
