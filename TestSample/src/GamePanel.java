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
	public static int ImageWidth;//��ȡ���ļ��Ŀ�͸�
    public static int ImageHeight;
    Block blank = null;
	
	public GamePanel() {
		this.setLayout(null);
		init();
	}
	
	//��ʼ��
    public void init(){
    	
        BufferedImage buf = null;
        BufferedImage bufnew = null;
        ImageIcon icon = null;
        int width = 0;
        int height = 0;
        try{
            buf = ImageIO.read(GameFrame.file);//��ȡ�ļ�ͼ��
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
            	
                num = i*Block.col+j;//��ǰbutton��num��Ҳ���������е��±�
                if(num < Block.row*Block.col-1){
                	
                    bufnew = buf.getSubimage(width*j, height*i, width, height);
                    icon = new ImageIcon(bufnew);
                    blocks[num] = new Block(icon, num, width, height);
                    blocks[num].setLocation(width*j, height*i);
                    
                }
                else{//�հ׸�
                	blocks[num] = new Block(null, num, width, height);
                    blocks[num].setLocation(width*j, height*i);
                    blocks[num].setBackground(Color.white);
                }
                blank = blocks[blocks.length-1];
                
            }
        }
        

        for(int i = 0; i < blocks.length; i++){
        	
            this.add(blocks[i]);
            if(i < blocks.length-1)//�հ׸���Ӽ�������
                blocks[i].addMouseListener(this);
            
        }
        
    }//��ʼ�����
    
    
    public void Start(){//��Ϸ��ʼ������
        Random random = new Random();
        for(int i = 0 ; i < blocks.length ; i++){
        	
            int block1 = random.nextInt(blocks.length);
            int block2 = random.nextInt(blocks.length);
            int x = blocks[block1].getX();
            int y = blocks[block1].getY();
            blocks[block1].setLocation(blocks[block2].getX(), blocks[block2].getY());
            blocks[block2].setLocation(x, y);//��������block
        }
    }
    
    public boolean IsWin()//�ж���Ϸ����Ƿ�Ӯ
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
            int choice = JOptionPane.showConfirmDialog(null, "�ɰ���ţ�ƻ��ˣ���ˬһ�̰ɣ�", "�����ˣ�", 
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
