package com.tc.snake;

import javax.swing.*;

public class StartSnakes {
    public static void main(String[] args) {
        //绘制一个静态窗口
        JFrame frame = new JFrame("贪吃蛇");
        frame.setBounds(10, 10, 900, 720);//设置窗口大小
        frame.setResizable(false);//设置窗口不能改变
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭事件，让游戏关闭
        frame.add(new SnakePanel());
        frame.setVisible(true);//设置窗口显示出来
    }


}
