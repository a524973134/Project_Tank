package TankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我方坦克
    MyTank myTank = null;

    //定义敌方坦克，放入Vector集合中，因为是多线程的
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 6;

    //定义存放Node对象的Vector
    Vector<Node> nodes = new Vector<>();

    //定义爆炸
    Vector<Bomb> bombs = new Vector<>();

    //构造器
    //我方坦克、敌方坦克、敌方坦克子弹
    public MyPanel(String key) {
        //先判断是否有已保存的文件
        if (new File(Recorder.getRecordFile()).exists()) {
            nodes = Recorder.loadRecord();
        } else {
            System.out.println("不存在保存的记录，只能开始新游戏");
            key = "1";
        }

        switch (key) {
            case "1":
                //重置击毁坦克数
                Recorder.setDestoryTankNum(0);
                //初始化我方坦克
                myTank = new MyTank(100, 600, 5);
                //将MyPanel对象的enemyTanks传给Recorder对象的enemyTanks
                Recorder.setEnemyTanks(enemyTanks);
                //初始化敌方坦克
                for (int i = 0; i < enemyTankSize; i++) {
                    //创建一辆敌方坦克
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0, 3);
                    //将该文件中的enemyTanks传给每一辆敌方坦克
                    enemyTank.setEnemyTanks(enemyTanks);
                    //设置方向
                    enemyTank.setDirection(2);
                    //启动敌方坦克线程，随机移动
                    new Thread(enemyTank).start();
                    //给enemyTank加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 50, enemyTank.getDirection());
                    //加入enemyTank的Shot的Vector进行管理
                    enemyTank.getShots().add(shot);
                    //启动shot对象
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2":
                //初始化我方坦克
                myTank = new MyTank(100, 600, 5);
                //将MyPanel对象的enemyTanks传给Recorder对象的enemyTanks
                Recorder.setEnemyTanks(enemyTanks);
                //初始化敌方坦克
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    //创建一辆敌方坦克
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY(), 3);
                    //将该文件中的enemyTanks传给每一辆敌方坦克
                    enemyTank.setEnemyTanks(enemyTanks);
                    //设置方向
                    enemyTank.setDirection(node.getDirection());
                    //启动敌方坦克线程，随机移动
                    new Thread(enemyTank).start();
                    //给enemyTank加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 50, enemyTank.getDirection());
                    //加入enemyTank的Shot的Vector进行管理
                    enemyTank.getShots().add(shot);
                    //启动shot对象
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("请输入1或2");
                break;
        }
    }

    //右侧显示积分信息
    public void showInfo(Graphics g) {
        //画出玩家成绩
        g.setColor(Color.black);
        Font songTi = new Font("宋体", Font.BOLD, 25);
        g.setFont(songTi);

        g.drawString("您累计击毁敌方坦克：", 1020, 30);
        drawTank(1020, 60, g, 0, 1);
        g.setColor(Color.black);
        g.drawString(" x " + Recorder.getDestoryTankNum() + "", 1080, 95);

    }

    @Override
    public void paint(Graphics g) {
        //设置画笔
        super.paint(g);
        //设置画布大小
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色
        showInfo(g);

        //绘制我方坦克
        if (myTank != null && myTank.isLive()) {
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirection(), 0);
        }

        //绘制敌方坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出集合中坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //当敌方坦克isLive才绘制敌方坦克
            if (enemyTank.isLive() == true) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 1);
                for (int j = 0; j < enemyTank.getShots().size(); j++) {
                    //取出第i辆坦克的第j颗子弹
                    Shot shot = enemyTank.getShots().get(j);
                    //绘制第i辆坦克的第j颗子弹
                    if (shot.isLive()) {
                        g.fill3DRect(shot.getX(), shot.getY(), 1, 1, false);
                    } else {
                        //子弹isLive == false, 应当将该子弹从Vector移除
                        enemyTank.getShots().remove(shot);
                    }
                }
            }
        }

        //绘制我方子弹
//        if (myTank.shot != null && myTank.shot.isLive() == true) {
//            g.fill3DRect(myTank.shot.getX(), myTank.shot.getY(), 1, 1, false);
//            //this.repaint();
//        }

        //绘制多颗炮弹，将炮弹从Vector集合中取出
        for (int i = 0; i < myTank.getShots().size(); i++) {
            Shot shot = myTank.getShots().get(i);
            if (shot != null && shot.isLive() == true) {
                g.fill3DRect(shot.getX(), shot.getY(), 1, 1, false);
                //this.repaint();
            } else {
                //如果shot已经销毁，则从集合中移除
                myTank.getShots().remove(shot);
            }
        }
    }

    /**
     * 编写绘制坦克的方法
     *
     * @param x         左上角坐标
     * @param y         左上角坐标
     * @param g         画笔
     * @param direction 朝向
     * @param type      类型
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        switch (type) {
            case 0: //我方坦克
                g.setColor(Color.CYAN);
                break;
            case 1: //敌方坦克
                g.setColor(Color.YELLOW);
                break;
        }

        switch (direction) {
            case 0://↑
                g.fill3DRect(x, y, 10, 50, false);//左履带
                g.fill3DRect(x + 30, y, 10, 50, false);//右履带
                g.fill3DRect(x + 10, y + 5, 20, 40, false);//中部
                g.fillOval(x + 10, y + 15, 20, 20);//圆
                g.drawLine(x + 20, y, x + 20, y + 25);//炮管
                break;
            case 1://→
                g.fill3DRect(x, y, 50, 10, false);//上履带
                g.fill3DRect(x, y + 30, 50, 10, false);//下履带
                g.fill3DRect(x + 5, y + 10, 40, 20, false);//中部
                g.fillOval(x + 15, y + 10, 20, 20);//圆
                g.drawLine(x + 25, y + 20, x + 50, y + 20);//炮管
                break;
            case 2://↓
                g.fill3DRect(x, y, 10, 50, false);//左履带
                g.fill3DRect(x + 30, y, 10, 50, false);//右履带
                g.fill3DRect(x + 10, y + 5, 20, 40, false);//中部
                g.fillOval(x + 10, y + 15, 20, 20);//圆
                g.drawLine(x + 20, y + 25, x + 20, y + 50);//炮管
                break;
            case 3://←
                g.fill3DRect(x, y, 50, 10, false);//上履带
                g.fill3DRect(x, y + 30, 50, 10, false);//下履带
                g.fill3DRect(x + 5, y + 10, 40, 20, false);//中部
                g.fillOval(x + 15, y + 10, 20, 20);//圆
                g.drawLine(x, y + 20, x + 25, y + 20);//炮管
                break;
            default:
                System.out.println("暂无");
        }
    }

    //判断某颗炮弹是否击中坦克
    public void isHitTank(Shot s, Tank Tank) {
        //判断击中
        switch (Tank.getDirection()) {
            case 0:
            case 2:
                if (s.getX() > Tank.getX() && s.getX() < Tank.getX() + 40
                        && s.getY() > Tank.getY() && s.getY() < Tank.getY() + 50) {
                    s.setLive(false);
                    Tank.setLive(false);
                    //我方子弹击中敌方坦克时，将敌方坦克从集合中移除
                    enemyTanks.remove(Tank);
                    if (Tank instanceof EnemyTank) {
                        Recorder.addDestoryTankNum();
                    }
                }
                break;
            case 1:
            case 3:
                if (s.getX() > Tank.getX() && s.getX() < Tank.getX() + 50
                        && s.getY() > Tank.getY() && s.getY() < Tank.getY() + 40) {
                    s.setLive(false);
                    Tank.setLive(false);
                    enemyTanks.remove(Tank);
                    if (Tank instanceof EnemyTank) {
                        Recorder.addDestoryTankNum();
                    }
                }
                break;
        }
    }

    //遍历我方所有炮弹，判断是否击中
    public void isAnyShotHitTank() {
        for (int i = 0; i < myTank.getShots().size(); i++) {
            Shot shot = myTank.getShots().get(i);
            //注意if两个条件的先后顺序
            if (shot != null && shot.isLive() == true) {
                for (int j = 0; j < enemyTanks.size(); j++) {
                    EnemyTank enemyTank = enemyTanks.get(j);
                    isHitTank(shot, enemyTank);
                }
            }
        }
    }

    //敌方坦克是否击中我方坦克
    public void isHitMyTank() {
        //遍历所有敌方坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历i坦克所有炮弹
            for (int j = 0; j < enemyTank.getShots().size(); j++) {
                Shot shot = enemyTank.getShots().get(j);
                //判断敌方第i辆坦克的第j颗炮弹是否击中我方坦克
                if (myTank.isLive() && shot.isLive()) {
                    isHitTank(shot, myTank);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            myTank.setDirection(0);
            myTank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            myTank.setDirection(1);
            myTank.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            myTank.setDirection(2);
            myTank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            myTank.setDirection(3);
            myTank.moveLeft();
        }

        //如果用户按下J，就触发射击方法
        if (e.getKeyCode() == KeyEvent.VK_J) {
            myTank.shotEnemyTank();
        }
        //将整个MyPanel设置成线程并且不断刷新后，就无需设置按键后重绘了
        //this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//将MyPanel设置为线程，不断重绘页面
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //判断我方某颗炮弹是否击中敌方坦克
            isAnyShotHitTank();
            //判断敌方某颗炮弹是否击中我方坦克
            isHitMyTank();
            this.repaint();
        }
    }
}
