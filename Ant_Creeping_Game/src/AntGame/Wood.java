package AntGame;

public class Wood {
    public static int LENGTH;
    public static int START;
    public static int END;

    public Wood(int length) {
        LENGTH = length;
        START = 0;
        END = LENGTH;
    }

    public static void setLENGTH(int LENGTH) {
        Wood.LENGTH = LENGTH;
    }

    public void isOut(Ant ant){
        if(ant.position <= this.START || ant.position > this.END){
            ant.isAlive = false;
        }
    }
}
