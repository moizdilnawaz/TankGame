/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;


/**
 * @author anthony-pc
 */
public class TankControl extends JPanel implements KeyListener {

    private final Tank t1;
    private Bullet bullet;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shootkey;
    //    private boolean isShooted;

    public TankControl(Tank t1, int up, int down, int left, int right, int shootkey) {
        this.t1 = t1;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shootkey = shootkey;
//        healtbarTank1.
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }
    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == up) {
            this.t1.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.t1.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.t1.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.t1.toggleRightPressed();
        }
        if (keyPressed == shootkey) {
            System.out.println("Shooted ----------------------------------------------------------------------------------");
//            setShooted(true);
            bullet.setFirePressed(true);
//            bullet.
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        AffineTransform rotation = AffineTransform.getTranslateInstance(t1.getX(), t1.getY());
        rotation.rotate(Math.toRadians(t1.getAngle()), this.bullet.getImg().getWidth() / 2.0, this.bullet.getImg().getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bullet.getImg(), rotation, null);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased == up) {
            this.t1.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.t1.unToggleDownPressed();
        }
        if (keyReleased == left) {
            this.t1.unToggleLeftPressed();
        }
        if (keyReleased == right) {
            this.t1.unToggleRightPressed();
        }
    }

    public Tank getT1() {
        return t1;
    }

//    public void setT1(Tank t1) {
//        this.t1 = t1;
//    }

    public Bullet getBullet() {
        return bullet;
    }

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public int getRight() {
        return right;
    }

    public int getLeft() {
        return left;
    }

    public int getShootkey() {
        return shootkey;
    }
//    public boolean isShooted() {
//        return isShooted;
//    }
//    public void setShooted(boolean shooted) {
//        isShooted = shooted;
//    }
}
