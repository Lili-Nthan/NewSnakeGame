package com.msb.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * @author Li
 * @version 1.0
 * @ClassName GamePanel
 * @Date 2022/6/29 0029 18:21
 */
public class GamePanel extends JPanel {


    /**
     * 定义变量
     * 定义蛇的长度
     * 定义两个数组，专门存储蛇的X,Y坐标
     * 蛇走的方向
     * 游戏状态开始 暂停
     * 加入定时器
     * 定义事物的X与Y坐标
     * 定义一个积分
     * 生存状态
     */
    int length;
    int[] snakeX = new int[200];
    int[] snakeY = new int[200];
    String direction;
    boolean isStart = false; //默认暂停
    Timer timer;
    int foodX;
    int foodY;
    int score = 0;
    boolean isDie = false; //默认情况小蛇没有死亡

    public void init() {
        //初始化蛇的长度
        length = 3;

        //初始化蛇头坐标
        snakeX[0] = 175;
        snakeY[0] = 275;

        //初始化第一节身体坐标
        snakeX[1] = 150;
        snakeY[1] = 275;
        //初始化第二季身体坐标
        snakeX[2] = 125;
        snakeY[2] = 275;

        //初始化蛇头的方向
        direction = "R";

        //初始化食物的坐标
        foodX = 300;
        foodY = 200;
    }

    public GamePanel() {
        init();
        //将交点定位在当前的操作面板上
        this.setFocusable(true);
        //加入监听
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {  //监听键盘的按下操作
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                System.out.println(keyCode);
                if (keyCode == KeyEvent.VK_SPACE) {    //监听空格
                    if (isDie){
                        init();
                        isDie = false;
                    }else{
                        isStart = !isStart;
                        repaint();  //重绘
                        System.out.println("点击了空格");
                    }

                }
                if (keyCode == KeyEvent.VK_UP) {
                    direction = "U";
                }
                if (keyCode == KeyEvent.VK_LEFT) {
                    direction = "L";
                }
                if (keyCode == KeyEvent.VK_DOWN) {
                    direction = "D";
                }
                if (keyCode == KeyEvent.VK_RIGHT) {
                    direction = "R";
                }


            }
        });
        timer = new Timer(100, new ActionListener() {
            /**
             * ActionListener
             *事件监听，相当于每100毫秒是否发生了一个动作
             * 具体的动作放入actionPerformed
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStart && isDie ==false) {   //游戏是开始状态的时候才动
                    //后一节身子走到前一节身子的位置
                    for (int i = length - 1; i > 0; i--) {
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }
                    //动头
                    if ("R".equals(direction)) {
                        snakeX[0] += 25;
                    }
                    if ("L".equals(direction)) {
                        snakeX[0] -= 25;
                    }
                    if ("U".equals(direction)) {
                        snakeY[0] -= 25;
                    }
                    if ("D".equals(direction)) {
                        snakeY[0] += 25;
                    }

                    //防止蛇超出边界
                    if (snakeX[0] > 750) {
                        snakeX[0] = 25;
                    }
                    if (snakeX[0] < 25) {
                        snakeX[0] = 750;
                    }
                    if (snakeY[0] < 100) {
                        snakeY[0] = 725;
                    }
                    if (snakeY[0] > 725) {
                        snakeY[0] = 100;
                    }

                    //检测碰撞动作
                    if (snakeX[0] == foodX && snakeY[0] == foodY) {
                        //蛇长度加1
                        length++;
                        //食物坐标改变: 随机生成坐标--->>细节：坐标必须是25的倍数
                        foodX = (new Random().nextInt(30) + 1) * 25; //[1,30]*25--->>[25.750]
                        foodY = ((new Random().nextInt(26)) + 4) * 25;//[0,25]+4------>[100,725]
                        //每吃食物积分加10
                        score += 10;
                    }
                    //死亡判定
                    for (int i = 1; i < length; i++) {
                        if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                            isDie = !isDie;

                        }
                    }
                    //重绘
                    repaint();
                }
            }
        });
        //定时器启动
        timer.start();
    }

    /**
     * paintComponent 这个方法比较特殊，这个方法就属于图形版的main方法
     * 自动调用
     *
     * @param g
     */
    @Override

    protected void paintComponent(Graphics g) {
        //背景填充色
        super.paintComponent(g);
        this.setBackground(new Color(208, 220, 226));
        /**
         * 画头部图片
         * paintIcon 四个参数：this指的是当前面板，
         *                   g指的是画笔
         *                   x，y对应的坐标
         */
        Images.headImg.paintIcon(this, g, 10, 20);
        //调节画笔颜色：】
        g.setColor(new Color(219, 226, 219));
        //画一个矩形
        g.fillRect(10, 70, 770, 685);

        /**
         * 画小蛇
         * 画蛇头
         * 画第身子
         */
        if ("R".equals(direction)) {
            Images.rightImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if ("L".equals(direction)) {
            Images.leftImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if ("U".equals(direction)) {
            Images.upImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if ("D".equals(direction)) {
            Images.downImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        //  Images.rightImg.paintIcon(this,g,snakeX[0],snakeY[0]);
        for (int i = 1; i < length; i++) {
            Images.bodyImg.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        /**
         * 如果游戏暂停，界面中间应该有一句提示语
         */
        if (isStart == false) {
            //画文字
            g.setColor(new Color(114, 98, 255));
            //字体 加粗  字号
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            //画文字
            g.drawString("点击空格开始游戏", 250, 330);

        }

        //画食物
        Images.foodImg.paintIcon(this, g, foodX, foodY);
        //画积分
        g.setColor(new Color(255, 248, 248));
        //字体 加粗  字号
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        //画文字
        g.drawString("积分：" + score, 620, 40);

        //画入死亡状态
        if (isDie) {
            //画入死亡状态
            g.setColor(new Color(255, 82, 68));
            //字体 加粗  字号
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            //画文字
            g.drawString("小蛇死亡，游戏停止，按下空格重新开始游戏", 200, 300);
        }


    }

}
