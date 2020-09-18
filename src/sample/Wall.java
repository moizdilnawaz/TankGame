package sample;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Wall extends GameObject {

//    private int x;
//    private int y;
//    private float angle;
//    private BufferedImage img;
    private Game game;
    public boolean isColided;
    private boolean breakable;
    public boolean isBreakable() {
        return breakable;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }



    public Wall(Game game ,BufferedImage img, int x, int y ,float angle) {
        super(img,x,y,angle);
        this.game = game;

    }

    public Wall(int x, int y, int angle, BufferedImage img, Game game) {
        super(img,x,y,angle);
        this.x = x;
        this.y = y;
        this.img = img;
        this.angle = angle;
        this.game = game;
    }



    public void setImg(BufferedImage img) {
        this.img = img;
    }


    void update() {

    }
    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
    }
    public boolean detectCollision() {
        if (game.t1.getBounds().intersects(getBounds())) {
            isColided = true;
            return true;
        } else
            return false;
    }
}