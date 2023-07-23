package TankGame;

public class Tank {
    private int x;
    private int y;
    private double speed;
    private int direction;//坦克朝向 0123 上右下左
    private boolean isLive = true;


    //坦克移动方法
    public void moveUp() {
        if (getY() > 0) {
            y -= speed;
        }
    }

    public void moveRight() {
        if (getX() + 50 < 1000) {
            x += speed;
        }
    }

    public void moveDown() {
        if (getY() + 50 < 750) {
            y += speed;
        }
    }

    public void moveLeft() {
        if (getX() > 0) {
            x -= speed;
        }
    }

    public Tank(int x, int y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
