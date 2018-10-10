package AntGame;

import java.awt.*;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class PlayRoom extends JFrame {
    private static final long serialVersionUID = 1L;
    protected static boolean[][] dirs = new boolean[32][5];
    protected static int[] pos = {3, 8, 11, 16, 25};
    private ControlJPanel controlJPanel = null;
    private PaintActiveAntsJPanel paintActiveAntsJPanel = null;
    private boolean isSuspend = false;// isSuspend是否暂停

    private JLabel textTimeLabel = null, textCountLabel = null;

    CreepGame creepGame = new CreepGame();
    protected int number_dirs;

    public PlayRoom() {
        super("动态演示蚂蚁爬杆行为");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭JFrame时，退出程序
        this.setBounds(200, 100, 1000, 300);//设置窗体的长宽各为:900 200,让其显示在距屏幕左上方坐标(200,100)处
        this.setLayout(new GridLayout(2, 1));//网格2行1列
        // new 对象
        controlJPanel = new ControlJPanel();
        paintActiveAntsJPanel = new PaintActiveAntsJPanel();
        creepGame.situation(0);
        this.getContentPane().add(controlJPanel);
        this.getContentPane().add(paintActiveAntsJPanel);
        setVisible(true);
    }

    public void init_Directions(){
        for(int i = 0; i < dirs.length; i++){
            int sum = i;
            for(int j = dirs[i].length-1; j >= 0; j--){
                dirs[i][j] = sum % 2 != 0;
                sum /= 2;
            }
        }
    }

    public void setDirs(int number_dirs) {
        for (int i = 0; i < creepGame.ants.length; i++) {
            creepGame.ants[i].setDirection(dirs[number_dirs][i]);
        }
    }

    // 控制面板
    public class ControlJPanel extends JPanel implements ActionListener {
        private static final long serialVersionUID = 1L;
        protected JButton jButton_Pause = null, jButton_Restart = null;// 暂停/开始按钮
        protected JTextField textSituation = null;
        protected JLabel situation = null;
        protected JLabel display_dirs = null;

        public ControlJPanel() {
            this.setLayout(null);// 绝对定位
            // new 对象
            jButton_Pause = new JButton("Start");// 暂停
            jButton_Restart = new JButton("Restart");// 暂停
            textSituation = new JTextField(20);
            JLabel label = new JLabel("请输入：");
            textTimeLabel = new JLabel();
            textCountLabel = new JLabel();
            situation = new JLabel();
            situation.setText("第--种情况: ");
            display_dirs = new JLabel();
            display_dirs.setText("5种蚂蚁的方向: " + " 1:   " + "--"
                    + "    2:   " + "--"
                    + "    3:   " + "--"
                    + "    4:   " + "--"
                    + "    5:   " + "--");

            // 设置位置
            jButton_Pause.setBounds(100, 75, 90, 30);
            jButton_Restart.setBounds(200, 75, 80, 30);
            label.setBounds(100, 20, 75, 30);
            textSituation.setBounds(170, 20, 100, 30);
            textTimeLabel.setBounds(550, 20, 100, 30);
            textCountLabel.setBounds(650, 20, 100, 30);
            setJLabel();

            situation.setBounds(450, 20, 80, 30);
            display_dirs.setBounds(450, 65, 580, 30);
            // jButton_verify加监听
            jButton_Pause.addActionListener(this);
            jButton_Restart.addActionListener(this);
            textSituation.addActionListener(this);
            // 加组件
            this.add(situation);
            this.add(display_dirs);
            this.add(jButton_Pause);
            this.add(jButton_Restart);
            this.add(label);
            this.add(textSituation);
            this.add(textTimeLabel);
            this.add(textCountLabel);
        }

        public void setJLabel() {
            textTimeLabel.setText("time: " + (creepGame.time - 1) + "s");
            textCountLabel.setText("count: " + creepGame.count + "s");
        }

        public void updateDisplay() {
            situation.setText("第" + (number_dirs + 1) + "种情况: ");
            display_dirs.setText("5种蚂蚁的方向: " + " 1:   " + creepGame.ants[0].displayDirection()
                    + "    2:   " + creepGame.ants[1].displayDirection()
                    + "    3:   " + creepGame.ants[2].displayDirection()
                    + "    4:   " + creepGame.ants[3].displayDirection()
                    + "    5:   " + creepGame.ants[4].displayDirection());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Pause")) {
                jButton_Pause.setText("Continue");
                isSuspend = true;
            }
            if (e.getActionCommand().equals("Start") || e.getActionCommand().equals("Continue")) {
                jButton_Pause.setText("Pause");
                isSuspend = false;
            }
            if (e.getActionCommand().equals("Restart")) {
                paintActiveAntsJPanel.repaint();
                paintActiveAntsJPanel.start();
                jButton_Pause.setText("Pause");
                isSuspend = false;
            }
            if (e.getSource() == textSituation) {
                try {
                    number_dirs = Integer.parseInt(textSituation.getText()) - 1;
                    if (number_dirs < 0 || number_dirs > 31) {
                        number_dirs = 0;
                    }
                    setDirs(number_dirs);
                    this.updateDisplay();
                    paintActiveAntsJPanel.start();
                    creepGame.situation(number_dirs);
                    jButton_Pause.setText("Start");
                    textSituation.setText(null);
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    // PaintActiveAntsJPanel 画活动的蚂蚁
    public class PaintActiveAntsJPanel extends JPanel implements ActionListener {
        private static final long serialVersionUID = 1L;
        private int width = 30;// 设置像素为1；一个像素等于10个int
        protected Timer timer = null;

        public PaintActiveAntsJPanel() {
            timer = new Timer(1000, this);
            start();
        }

        public void start() {
            creepGame.count = 0;// 重新开始
            isSuspend = true;
            timer.restart();
            timer.setDelay(1000);
        }

        public void end() {
            timer.stop();
            controlJPanel.jButton_Pause.setText("Start");
        }

        @Override
        public void paint(Graphics g) {
            if (creepGame.count == creepGame.time) {
                end();
            }
            super.paint(g);// 擦掉原来的
            controlJPanel.updateDisplay();
            for (int j = 1, k = 0; j <= 30; j++) {// 画30个位置
                g.drawLine(40, 60, 940, 60);
                while (k < 5 && creepGame.ants_direction[creepGame.count][k] == -2) {
                    k++;
                }
                if (creepGame.pole[creepGame.count][j] != 0 && k < 5) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    // 从本地读取一张图片
                    Image image;
                    if (creepGame.ants_direction[creepGame.count][k] == 1) {
                        String filepath1 = "1.jpg";
                        image = Toolkit.getDefaultToolkit().getImage(filepath1);
                    } else {
                        String filepath1 = "2.jpg";
                        image = Toolkit.getDefaultToolkit().getImage(filepath1);
                    }
                    k++;
                    // 绘制图片（如果宽高传的不是图片原本的宽高, 则图片将会适当缩放绘制）
                    g2d.drawImage(image, 10 + j * width, 30, width, width, this);
                }
            }

            for (int i = 0; i < 5; i++) {
                if (creepGame.ants_direction[creepGame.count][i] == 0) {
                    creepGame.ants[i].direction = false;
                }
                if (creepGame.ants_direction[creepGame.count][i] == 1) {
                    creepGame.ants[i].direction = true;
                }
            }
            controlJPanel.updateDisplay();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.repaint();
            controlJPanel.setJLabel();
            if (isSuspend) {// 如果暂停，那么记录当前位置
                return;
            }
            creepGame.count++;
            controlJPanel.setJLabel();
        }
    }

    public static void main(String[] args) {
        // 初始化方向组合
        PlayRoom playroom = new PlayRoom();
        playroom.init_Directions();
    }
}