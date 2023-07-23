package TankGame;

import java.io.*;
import java.util.Vector;

public class Recorder {
    //定义变量记录我方击毁敌方坦克数
    private static int destoryTankNum = 0;
    //定义IO对象，用于输出数据到文件
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src\\myRecord.txt";
    //定义一个属性接收/指向MyPanel中敌方坦克的Vector
    private static Vector<EnemyTank> enemyTanks = null;
    //定义一个Node用于保存坦克信息
    private static Vector<Node> nodes = new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //当游戏退出时，将游戏数据保存到recordFile中
    public static void saveRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(destoryTankNum + "\r\n");//bw.newLine();
            //遍历敌方坦克的Vector并保存
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isLive()) {
                    //输出第i辆敌方坦克信息
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirection();
                    bw.write(record + "\r\n");
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //重启游戏，读取recordFile信息
    public static Vector<Node> loadRecord() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            destoryTankNum = Integer.parseInt(br.readLine());
            //循环读取文件，输入Nodes中
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }

    public static String getRecordFile() {
        return recordFile;
    }

    public static int getDestoryTankNum() {
        return destoryTankNum;
    }

    public static void setDestoryTankNum(int destoryTankNum) {
        Recorder.destoryTankNum = destoryTankNum;
    }

    public static void addDestoryTankNum() {
        Recorder.destoryTankNum++;
    }
}
