package AntGame;

public class Ant {
    public int velocity;
    protected int position;

    public int getVelocity() {
        return velocity;
    }

    public int getPosition() {
        return position;
    }

    public boolean getDirection() {
        return direction;
    }
    public boolean getAlive() {
        return isAlive;
    }

    protected boolean direction;  // true向右, false向左
    protected boolean isAlive;

    public Ant() {
        this.velocity = 1;
        this.isAlive = true;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void changeDirection() {
        direction = !direction;
    }

    public String displayDirection() {
        if (this.direction) {
            return "right";
        } else {
            return "left";
        }
    }

    public void creep() {
        if (isAlive) {
            if (direction) {
                position += velocity;
            } else {
                position -= velocity;
            }
        }
    }
}
