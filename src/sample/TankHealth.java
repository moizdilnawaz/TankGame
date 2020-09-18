package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class TankHealth extends JPanel {
    private int MAXHEALTH = 10;

    private int health;
    private Rectangle healthbar;
    private int damagefector;

    public Rectangle getHealthbar() {
        return healthbar;
    }
    public void setHealthbar(Rectangle healthbar) {
        this.healthbar = healthbar;
    }
    public void reduceHealth(){
//        healthbar.
    }

    public TankHealth(int MAXHEALTH, int health, Rectangle healthbar) {
        this.MAXHEALTH = MAXHEALTH;
        this.health = health;
        this.healthbar = healthbar;
    }

    public int getMAXHEALTH() {
        return MAXHEALTH;
    }

    public void setMAXHEALTH(int MAXHEALTH) {
        this.MAXHEALTH = MAXHEALTH;
    }

    public int getDamagefector() {
        return damagefector;
    }

    public void setDamagefector(int damagefector) {
        this.damagefector = damagefector;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public void damage(int damagefector){
        if(!(this.healthbar.getBounds().width<= this.damagefector)) {
            this.damagefector += damagefector;
            health=this.healthbar.getBounds().width-this.damagefector;
            System.out.println("Helth"+health);;

        }
    }
    void drawRectangle(Graphics g  ,Color color) {
//            AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
            Graphics2D g2d = (Graphics2D) g;
            g2d.draw(this.healthbar.getBounds());
            g2d.setColor(color);
            g2d.fillRect(this.healthbar.getBounds().x,this.healthbar.getBounds().y , this.healthbar.getBounds().width-damagefector , this.healthbar.getBounds().height);
    }
}
