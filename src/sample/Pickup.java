package sample;

import sample.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pickup extends GameObject {
    boolean isPickedUp;
    public Pickup(BufferedImage img, int x, int y, float angle) {
        super(img, x, y, angle);
    }

    public boolean isPickedUp() {
        return isPickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        isPickedUp = pickedUp;
    }

    public void draw(Graphics g){
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(img,x,y,null);
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, super.img.getWidth(), super.img.getHeight());
    }
}
