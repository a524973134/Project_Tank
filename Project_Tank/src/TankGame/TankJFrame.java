package TankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class TankJFrame extends JFrame {
    //定义Panel
    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TankJFrame tankJFrame = new TankJFrame();
    }

    public TankJFrame() throws HeadlessException {
        System.out.println("输入1：新游戏   输入2：继续游戏");
        String key = scanner.next();
        mp = new MyPanel(key);
        new Thread(mp).start();
        this.add(mp);
        //设置窗口大小
        this.setSize(1350, 780);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //在JFrame中增加响应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.saveRecord();
            }
        });
    }
}
