package TankGame;

import java.util.Vector;

public class MyTank extends Tank {
    //只能发射一颗炮弹
    private Shot shot = null;
    //可以发射多颗炮弹
    private Vector<Shot> shots = new Vector<>();

    public MyTank(int x, int y, double speed) {
        super(x, y, speed);
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

    public Vector<Shot> getShots() {
        return shots;
    }

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }

    //按下按键射击方法
    public void shotEnemyTank() {
        //设置我方坦克最多同时打几颗炮弹
        if (shots.size() == 5) {
            return;
        }
        switch (getDirection()) {//坦克方向
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 50, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 50, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        //把新创建的shot放入Vector中
        shots.add(shot);
        //启动射击线程，子弹飞行
        new Thread(shot).start();
    }
}
