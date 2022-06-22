package com.tc.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SnakePanel extends JPanel implements KeyListener, ActionListener {
    int length;
    int[] snakeX = new int[600];
    int[] snakeY = new int[500];
    String fx;
    Boolean isStart = false;
    int foodx;
    int foody;
    Random random = new Random();
    Boolean isFail = false;
    int score;
    Timer timer = new Timer(100, this);

    public SnakePanel() {
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();


    }

    public void init() {
        length = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
        fx = "R";
        foodx = 25 + 25 * random.nextInt(33);
        foody = 75 + 25 * random.nextInt(23);
        score=0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        this.setBackground(Color.BLACK);//设置背景颜色
        Data.header.paintIcon(this, g, 25, 11);//绘制头部广告栏
        g.fillRect(25, 75, 850, 600);//绘制游戏区域
        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }
        Data.food.paintIcon(this, g, foodx, foody);

        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("length" + length, 750, 35);
        g.drawString("score" + score, 750, 50);

        if (isStart == false) {
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始游戏", 300, 300);
        }
        if (isFail) {
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格重新开始游戏", 300, 300);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFail) {
                isFail = false;
                init();
            } else {
                isStart = !isStart;
            }

        }
        if (keyCode == KeyEvent.VK_LEFT) {
            fx = "L";
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            fx = "R";
        }
        if (keyCode == KeyEvent.VK_UP) {
            fx = "U";
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            fx = "D";
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart&&isFail==false) {
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            if (fx.equals("R")) {
                snakeX[0] = snakeX[0] + 25;
                if (snakeX[0] > 850) {
                    snakeX[0] = 25;
                }
            } else if (fx.equals("L")) {
                snakeX[0] = snakeX[0] - 25;
                if (snakeX[0] < 25) {
                    snakeX[0] = 850;
                }
            } else if (fx.equals("U")) {
                snakeY[0] = snakeY[0] - 25;
                if (snakeY[0] < 75) {
                    snakeY[0] = 600;
                }
            } else if (fx.equals("D")) {
                snakeY[0] = snakeY[0] + 25;
                if (snakeY[0] > 600) {
                    snakeY[0] = 75;
                }
            }
            if (snakeX[0] == foodx && snakeY[0] == foody) {
                length++;
                score+=10;
                foodx = 25 + 25 * random.nextInt(34);
                foody = 75 + 25 * random.nextInt(24);

            }
            for (int i = length-1; i >0 ; i--) {
                if (snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i]){
                    isFail=true;
                }

            }

                repaint();
        }
        timer.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
