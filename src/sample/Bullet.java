package sample;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject implements LimitObjects {

    private int vx;
    private int vy;
    private int speed = 6;
    private final int R = 2;
    private final float ROTATIONSPEED = 3.0f;
    private int player = 0;
    private boolean isColided=false;
    private boolean isWallColided = false;
    private TankControl tankControl;

    private boolean firePressed;
    private Game game;

    private BufferedImage explosionImage;
    private int colisionX;
    private int colisionY;



    public int getColisionX() {
        return colisionX;
    }

    public void setColisionX(int colisionX) {
        this.colisionX = colisionX;
    }

    public int getColisionY() {
        return colisionY;
    }

    public void setColisiony(int colisionY) {
        this.colisionY = colisionY;
    }


    public BufferedImage getExplosionImage() {
    return explosionImage;
}

    public void setExplosionImage(BufferedImage explosionImage) {
        this.explosionImage = explosionImage;
    }
    public boolean isWallColided() {
        return isWallColided;
    }

    public void setWallColided(boolean wallColided) {
        isWallColided = wallColided;
    }

    public TankControl getTankControl() {
        return tankControl;
    }

    public void setTankControl(TankControl tankControl) {
        this.tankControl = tankControl;
    }

    public Bullet(TankControl tankControl , int x, int y, float angle, BufferedImage img) {
        super(img,x,y,angle);
        this.tankControl = tankControl;
    }

    public Bullet(int x, int y, int vx, int vy, float angle, BufferedImage img, TankControl tankControl, Game game , int player) {
        super(img,x,y,angle);
        this.vx = vx;
        this.vy = vy;
        this.tankControl = tankControl;
        this.game = game;

        this.player = player;
    }
    public boolean isFirePressed() {
        return firePressed;
    }
    public void setFirePressed(boolean firePressed) {
        this.firePressed = firePressed;
    }

    void update() {
        if (this.firePressed) {
            System.out.println("Fire Pressed");
            this.shoot();
        }
        if (collision()) {
            System.out.println("collision Detected");
//            isWallColided = true;
        }
    }
    public void setLocation(int x, int y, int vx, int vy, float angle) {
        if (!this.firePressed) {
            super.x = x;
            super.y = y;
            this.vx = vx;
            this.vy = vy;
            super.angle = angle;
        }
    }
    private void shoot() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx * speed;
        y += vy * speed;
        checkBorder(super.x, super.y);
    }
    @Override
    public void checkBorder(int x, int y) {
        {
            if (x < 10) {
//                x = 10;
                x = tankControl.getT1().getX();
                this.firePressed = false;
            }
            if (x >= GameConstants.GAME_SCREEN_WIDTH - 22) {
//                x = GameConstants.GAME_SCREEN_WIDTH - 22;
                x = tankControl.getT1().getX();
                this.firePressed = false;
            }
            if (y < 20) {
//                y = 20;
                y = tankControl.getT1().getY();
                this.firePressed = false;
            }
            if (y >= GameConstants.GAME_SCREEN_HEIGHT - 22) {
//                y = GameConstants.GAME_SCREEN_HEIGHT - 22   ;
                y = tankControl.getT1().getY();
                this.firePressed = false;
            }
        }
    }
    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }
    void drawImage(Graphics g) {
        update();
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), super.getImg().getWidth() / 2.0, super.getImg().getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
    }
   public boolean collision() {
         for (int i = 0; i < game.walls.size(); i++) {
            if (game.walls.get(i).getBounds().intersects(getBounds()) && game.walls.get(i).isBreakable()) {
                game.wallsPopulator.getWallsdown().set(i, true);

                colisionX=x;
                colisionY=y;

                x = tankControl.getT1().getX();
                y = tankControl.getT1().getY();
                this.firePressed = false;
                isWallColided= true;
            }
            if (game.walls.get(i).getBounds().intersects(getBounds()) && !game.walls.get(i).isBreakable()) {
                colisionX=x;
                colisionY=y;

                x = tankControl.getT1().getX();
                y = tankControl.getT1().getY();

                this.firePressed = false;
                isWallColided= true;
            }
        }
        if (game.t2.getBounds().intersects( getBounds()) && !game.t1.getBounds().intersects(getBounds()) && player== 1) {
            game.scorePlayer1 += 5;
            game.player2.getHealth().damage(GameConstants.bullet1DamageFactor);
            if(game.player2.getHealth().getHealth()<=0) {
                game.player2.decreaseNoOfLifes();
                game.player2.getHealth().setDamagefector(0);
            }
            colisionX=x;
            colisionY=y;
            x = tankControl.getT1().getX();
            y = tankControl.getT1().getY();
            isWallColided= true;
            this.firePressed = false;
            return true;
        }
        else if(!this.getBounds().intersects(tankControl.getT1().getBounds()) && game.t1.getBounds().intersects(this.getBounds()) && player == 2){
            game.scorePlayer2 += 5;
            game.player1.getHealth().damage(GameConstants.bullet1DamageFactor);
            if(game.player1.getHealth().getHealth()<=0) {
                game.player1.decreaseNoOfLifes();
                game.player1.getHealth().setDamagefector(0);
            }
            colisionX=x;
            colisionY=y;
            x = tankControl.getT1().getX();
            y = tankControl.getT1().getY();
            isWallColided= true;
            this.firePressed = false;
            return true;
        }
        return false;
    }
}