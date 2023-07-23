package TankGame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {

    //使用Vector保存多个子弹Shot
    private Vector<Shot> shots = new Vector<>();

    //用于接收MyPanel中创建的敌方所有坦克信息，判断是否碰撞
    private Vector<EnemyTank> enemyTanks = new Vector<>();

    public Vector<Shot> getShots() {
        return shots;
    }

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }

    public Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public EnemyTank(int x, int y, double speed) {
        super(x, y, speed);
    }

    public boolean isTouchEnemyTank() {
        //判断碰撞情况
        //判断当前敌方坦克方向
        switch (this.getDirection()) {
            case 0:
                //当前敌方坦克和其他所有敌方坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从Vector中取出一辆敌方坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和当前敌方坦克本身比较
                    if (enemyTank != this) {
                        //如果敌方坦克是上下方向
                        //x的范围是[enemyTank.getX(), enemyTank.getX() + 40]
                        //y的范围是[enemyTank.getY(), enemyTank.getY() + 50]
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            //当前坦克左上角坐标[this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 50) {
                                return true;
                            }
                            //当前坦克右上角坐标[this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 40 &&
                                    this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 50) {
                                return true;
                            }

                        } else if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 50 &&
                                    this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 50 &&
                                    this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            if (this.getX() + 50 >= enemyTank.getX() && this.getX() + 50 <= enemyTank.getX() + 40 &&
                                    this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 50) {
                                return true;
                            }
                            if (this.getX() + 50 >= enemyTank.getX() && this.getX() + 50 <= enemyTank.getX() + 40 &&
                                    this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 50) {
                                return true;
                            }

                        } else if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            if (this.getX() + 50 >= enemyTank.getX() && this.getX() + 50 <= enemyTank.getX() + 50 &&
                                    this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() + 50 >= enemyTank.getX() && this.getX() + 50 <= enemyTank.getX() + 50 &&
                                    this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() + 50 >= enemyTank.getY() && this.getY() + 50 <= enemyTank.getY() + 50) {
                                return true;
                            }
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 40 &&
                                    this.getY() + 50 >= enemyTank.getY() && this.getY() + 50 <= enemyTank.getY() + 50) {
                                return true;
                            }

                        } else if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 50 &&
                                    this.getY() + 50 >= enemyTank.getY() && this.getY() + 50 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 50 &&
                                    this.getY() + 50 >= enemyTank.getY() && this.getY() + 50 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 50) {
                                return true;
                            }
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40 &&
                                    this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 50) {
                                return true;
                            }

                        } else if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 50 &&
                                    this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 50 &&
                                    this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            //敌方坦克未销毁时，不断发射炮弹
            //设置敌方坦克一次最多能发射几颗炮弹
            if (isLive() && shots.size() < 3) {
                //判断坦克的方向，创建对应的炮弹
                switch (getDirection()) {
                    case 0:
                        shots.add(new Shot(getX() + 20, getY(), 0));
                        break;
                    case 1:
                        shots.add(new Shot(getX() + 50, getY() + 20, 1));
                        break;
                    case 2:
                        shots.add(new Shot(getX() + 20, getY() + 50, 2));
                        break;
                    case 3:
                        shots.add(new Shot(getX(), getY() + 20, 3));
                        break;
                }
                for (int i = 0; i < shots.size(); i++) {
                    Shot shot = shots.get(i);
                    new Thread(shot).start();
                }
            }

            //敌方坦克自由移动
            switch (getDirection()) {
                case 0:
                    for (int i = 0; i < (int) (Math.random() * 50); i++) {
                        if (!isTouchEnemyTank()) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < (int) (Math.random() * 50); i++) {
                        if (!isTouchEnemyTank()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < (int) (Math.random() * 50); i++) {
                        if (!isTouchEnemyTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < (int) (Math.random() * 50); i++) {
                        if (!isTouchEnemyTank()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            //随机改变方向
            setDirection((int) (Math.random() * 4));
            //一旦涉及并发程序，一定要考虑线程什么时候结束
            if (isLive() == false) {
                //如果被击中就退出线程
                break;
            }
        }
    }
}
