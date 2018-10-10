package AntGame;

import AntGame.Ant;

public class CreepGame {
    public boolean isGame;
    public static Ant[] ants;
    public static Wood wood;

    protected int[][] pole = new int[29][32];// 最长时间27，杆长30，就有30个位置。
    protected int count = 0;// 每一步的时间，时间戳？
    protected int time = 0;// 每一种情况运行的时间
    protected int[][] ants_direction = new int[29][5];


    public CreepGame() {
        this.isGame = true;
        ants = new Ant[5];
        for (int i = 0; i < 5; i++) {
            ants[i] = new Ant();
        }
        wood = new Wood(30);
    }

    public void checkCollision(){
        // 相撞或者相遇
        for (int j = 0; j < ants.length - 1; j++) {
            if (ants[j].isAlive && ants[j + 1].isAlive
                    && ants[j].direction && !ants[j + 1].direction) {
                if (ants[j + 1].position == ants[j].position
                        || (ants[j + 1].position - ants[j].position) == 1) {
                    ants[j].changeDirection();
                    ants[j + 1].changeDirection();
                }
            }
        }
    }

    public void situation(int i) {
        // 初始化
        for (int j = 0; j < pole.length; j++) {
            for (int k = 0; k < pole[j].length; k++) {
                pole[j][k] = 0;
            }
        }
        for (int j = 0; j < ants.length; j++) {
            ants[j].setAlive(true);
            ants[j].setPosition(PlayRoom.pos[j]);
            ants[j].setDirection(PlayRoom.dirs[i][j]);
            if (ants[j].direction) ants_direction[0][j] = 1;
            else ants_direction[0][j] = 0;
        }
        time = 0;
        // 蚂蚁移动
        while (true) {
            // 记录数据， 初始化
            for (int j = 0; j < ants.length; j++) {
                pole[time][ants[j].position] = 1;
            }
            time++;
            // 移动
            for (int j = 0; j < ants.length; j++) {
                ants[j].creep();
                wood.isOut(ants[j]);
            }
            for (int j = 0; j < ants_direction[j].length; j++) {
                if (!ants[j].isAlive) {
                    ants_direction[time][j] = -2;
                } else {
                    if (ants[j].direction) ants_direction[time][j] = 1;
                    else ants_direction[time][j] = 0;
                }
            }

            checkCollision();

            // 蚂蚁是否全部掉下
            boolean isEnd = true;
            for (int j = 0; j < ants.length; j++) {
                if (ants[j].isAlive) {
                    isEnd = false;
                }
            }
            // 是否结束
            if (isEnd) {
                break;
            }
        }
    }

    public void setGameOver() {
        this.isGame = false;
    }
}