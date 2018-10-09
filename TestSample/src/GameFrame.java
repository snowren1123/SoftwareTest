import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CORBA.PUBLIC_MEMBER;


public class GameFrame extends JFrame implements ActionListener {
	public JPanel pane1 = new JPanel();
    public JButton button1 = new JButton("游戏开始");
    public JButton button2 = new JButton("查看原图");
    public JButton button3 = new JButton("游戏结束");
    public static File file = new File("xyjy.jpg");

	public GameFrame() {
		
		super("java项目");
		
		
		//菜单的设置
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu menu = new JMenu("设置");
		menuBar.add(menu);
		JMenuItem item1 = new JMenuItem("图片选取");
		JMenuItem item2 = new JMenuItem("行列数");
		item1.addActionListener(this);
		item2.addActionListener(this);
		menu.add(item1);
		menu.add(item2);
		
		JMenu menu2 = new JMenu("帮助");
		menuBar.add(menu2);
		JMenuItem item3 = new JMenuItem("关于游戏");
		item3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(final ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "这是一个拼图小游戏，点击游戏开始完成拼图挑战吧！\n（可点击查看原图帮助你完成游戏）", "详情", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menu2.add(item3);
		
		Container contentpane = this.getContentPane();
		GamePanel gamePanel = new GamePanel();
        contentpane.add(gamePanel,BorderLayout.CENTER);
        
		//“游戏开始”“查看原图”“游戏结束”三个按钮的panel
		pane1.setLayout(new FlowLayout());
        pane1.add(button1);
        pane1.add(button2);
        pane1.add(button3);
        button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(final ActionEvent e)
            {
                gamePanel.Start();
            }
        });
        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(final ActionEvent e)
            {
                new PictureFrame();
            }
        });
        button3.addActionListener(new ActionListener()
        {
            public void actionPerformed(final ActionEvent e)
            {
                System.exit(0);
            }
        });
        contentpane.add(pane1,BorderLayout.NORTH);
        
        
        this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 150, GamePanel.ImageWidth, GamePanel.ImageHeight+150);
		
	}
	
	
	public void actionPerformed(ActionEvent e){
	     if(e.getSource() instanceof JMenuItem){
	    	 String arg = e.getActionCommand();
	         
	    	 if(arg.equals("图片选取")) {
	        	 JFileChooser fileChooser;
	        	 fileChooser =new JFileChooser();
	             FileNameExtensionFilter filter =new FileNameExtensionFilter("图像文件(只能是PNG或JPG)", "JPG","PNG");  
	             fileChooser.setFileFilter(filter);  
	             int i=fileChooser.showOpenDialog(getContentPane());  
	             file = fileChooser.getSelectedFile();
	             new GameFrame();
	             this.dispose();
	         }
	         
	         
	         else if(arg.equals("行列数")){
	        	 //行读取
	        	 for (int flag = 1;flag == 1;) {
	        		 String ROW = JOptionPane.showInputDialog(null, "请输入一个整数", "行数", JOptionPane.PLAIN_MESSAGE);
		        	 if (ROW == null)  { flag = 0; }
		        	 else {
		        		 try {
			        		    Block.row = Integer.parseInt(ROW);
			        		    flag = 0;
			        		}  catch (NumberFormatException wrong) {
			        			flag = 1;
			        		    wrong.printStackTrace();
			        		    JOptionPane.showMessageDialog(null, "输入一个整数！！！！！！傻子！！！");
			        		}
		        	 } }
	        	 //列读取
	        	 for (int flag = 1;flag == 1;) {
	        		 String COL = JOptionPane.showInputDialog(null, "请输入一个整数", "列数", JOptionPane.PLAIN_MESSAGE);
		        	 if(COL == null) { flag = 0; }
		        	 else {
		        		 try {
			        		    Block.col = Integer.parseInt(COL);
			        		    flag = 0;
			        		}  catch (NumberFormatException wrong) {
			        			flag = 1;
			        		    wrong.printStackTrace();
			        		    JOptionPane.showMessageDialog(null, "输入一个整数！！！！！！傻子！！！");
			        		}
					} }
	        	 new GameFrame();
	        	 this.dispose(); 
	         }
	         
	      }
	 } 

	
	
	public static void main(String[] args) {
		new GameFrame();
	}

}
