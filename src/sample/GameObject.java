/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

public class GameObject {
    protected int x;
    protected int y;
    protected float angle;
    protected BufferedImage img;
    public GameObject(BufferedImage img, int x, int y ,float angle){
        this.img = img;
        this.x = x;
        this.y = y;
        this.angle=angle;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int a){
        this.x = a;
    }
    public void setY(int b){
        this.y = b;
    }
    public float getAngle() {
        return angle;
    }
    public void setAngle(float angle) {
        this.angle = angle;
    }
    public BufferedImage getImg() {
        return img;
    }
    public void setImg(BufferedImage img) {
        this.img = img;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
    }


}
