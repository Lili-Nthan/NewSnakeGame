package com.msb.game;

import java.net.URL;

/**
 * @author Li
 * @version 1.0
 * @ClassName TestURL
 * @Date 2022/6/30 0030 11:30
 */
public class TestURL {
    public static void main(String[] args) {
        URL url = Images.class.getResource("/");
        System.out.println(url);
    }
}
