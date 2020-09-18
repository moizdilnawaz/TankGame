package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends JPanel {

    private boolean setDefeated = false;
    private int noOflife;
    private final TankHealth health;
    private BufferedImage noOfLives;
    private ArrayList<Point> points = new ArrayList<Point>();

    public boolean isSetDefeated() {
        return setDefeated;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public void decreaseNoOfLifes(){
            if((health.getHealth()<= 0)){
                noOflife-=1;
                health.setDamagefector(0);
                System.out.println(noOflife);
            }
    }
    public int getLife() {
        return noOflife;
    }
    public void setLife(int noOfLife) {
        this.noOflife = noOflife;
    }
    public TankHealth getHealth() {
        return health;
    }

    public BufferedImage getNoOfLives() {
        return noOfLives;
    }

    public void setNoOfLives(BufferedImage noOfLives) {
        this.noOfLives = noOfLives;
    }

    public Player(int noOfLife, TankHealth health) {

        this.noOflife = noOfLife;
        this.health = health;

    }
    void drawLifes(Graphics g ) {

        AffineTransform rotationIndex0 = AffineTransform.getTranslateInstance(points.get(0).x, points.get(0).y);
        AffineTransform rotationIndex1 = AffineTransform.getTranslateInstance(points.get(1).x, points.get(1).y);
        AffineTransform rotationIndex2 = AffineTransform.getTranslateInstance(points.get(2).x, points.get(2).y);

        Graphics2D g2d = (Graphics2D) g;

//        g2d.drawImage(noOfLives, points.get(0).x, points.get(0).y, null);
//        g2d.drawImage(noOfLives, points.get(1).x, points.get(1).y, null);
//        g2d.drawImage(noOfLives, points.get(2).x, points.get(2).y, null);
//            System.out.println(health.getHealth());
        switch(this.noOflife){
            case 3:
                System.out.println("Index = 0 X"+ points.get(0).x+ "y"+ points.get(0).y);
                System.out.println("Index = 0 X"+ points.get(1).x+ "y"+ points.get(1).y);
                System.out.println("Index = 0 X"+ points.get(2).x+ "y"+ points.get(2).y);
                g2d.drawImage(noOfLives, rotationIndex0, null);
                g2d.drawImage(noOfLives, rotationIndex1, null);
                g2d.drawImage(noOfLives, rotationIndex2, null);
                break;
            case 2:
                g2d.drawImage(noOfLives, rotationIndex1, null);
                g2d.drawImage(noOfLives, rotationIndex2, null);
                break;
            case 1:
                g2d.drawImage(noOfLives, rotationIndex2, null);
                break;
             default:
                 setDefeated =true;

        }
    }
}
