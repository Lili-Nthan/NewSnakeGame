package com.msb.game;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author Li
 * @version 1.0
 * @ClassName Images
 * @Date 2022/6/27 0027 17:49
 */
public class Images {
    //将图片封装成一个对象
    public static URL bodyURL = Images.class.getResource("/images/body.png");
    public static ImageIcon bodyImg = new ImageIcon(bodyURL);


    public static URL headURL = Images.class.getResource("/images/head.png");
    public static ImageIcon  headImg = new ImageIcon(headURL);

    public static URL upURL = Images.class.getResource("/images/up.png");
    public static ImageIcon  upImg = new ImageIcon(upURL);

    public static URL downURL = Images.class.getResource("/images/down.png");
    public static ImageIcon  downImg = new ImageIcon(downURL);

    public static URL leftURL = Images.class.getResource("/images/left.png");
    public static ImageIcon leftImg = new ImageIcon(leftURL);

    public static URL rightURL = Images.class.getResource("/images/right.png");
    public static ImageIcon rightImg = new ImageIcon(rightURL);

    public static URL foodURL = Images.class.getResource("/images/food.png");
    public static ImageIcon foodImg = new ImageIcon(foodURL);


}
