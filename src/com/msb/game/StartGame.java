package com.msb.game;

import javax.swing.*;
import java.awt.*;

/**
 * @author Li
 * @version 1.0
 * @ClassName StartGame
 * @Date 2022/6/27 0027 21:28
 */
public class StartGame {
    //这是一个main方法，是程序的入口
    public static void main(String[] args) {
        //创建一个窗体
        JFrame jf = new JFrame();
        //给窗体设置一个标题
        jf.setTitle("小游戏 大逻辑");
        //设置窗体弹出的坐标
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        jf.setBounds(800,800,800,800);
        //设置窗体不可调节
        jf.setResizable(false);
        //关闭窗口程序随之关闭
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //创建面板
        GamePanel gp = new GamePanel();
        //将面板放入窗体
        jf.add(gp);
        //默认情况下窗体是隐藏效果，必须将窗体进行显现
        jf.setVisible(true);
    }
}
