package TankGame;

public class Shot implements Runnable {
    private int x;
    private int y;
    private int shotDirection;
    private int speed = 5;
    private boolean isLive = true;

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.shotDirection = direction;
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

    public int getShotDirection() {
        return shotDirection;
    }

    public void setShotDirection(int direction) {
        this.shotDirection = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() {//子弹飞行方法
        while (true) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //根据方向改变x/y坐标
            switch (shotDirection) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            //当子弹碰到边界时销毁
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive())) {
                isLive = false;
                break;
            }
        }
    }
}
