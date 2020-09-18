package sample;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


/**
 * @author anthony-pc
 */
public class Tank extends GameObject{
    private int vx;
    private int vy;


    private int R = 2;
    private final float ROTATIONSPEED = 2.0f;
    public boolean isColided = false;
    private Game game;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;


    public Tank(Game game ,BufferedImage img, int x, int y ,float angle) {
        super(img,x,y,angle);
        this.game = game;
    }
    Tank(int x, int y, int vx, int vy, int angle, BufferedImage img, Game game) {
        super(img,x,y,angle);
        this.vx = vx;
        this.vy = vy;
        this.game = game;

    }
    public int getVx() {
        return vx;
    }
    public void setVx(int vx) {
        this.vx = vx;
    }
    public int getVy() {
        return vy;
    }
    public void setVy(int vy) {
        this.vy = vy;
    }
    void toggleUpPressed() {
        this.UpPressed = true;
    }
    void toggleDownPressed() {
        this.DownPressed = true;
    }
    void toggleRightPressed() {
        this.RightPressed = true;
    }
    void toggleLeftPressed() {
        this.LeftPressed = true;
    }
    void unToggleUpPressed() {
        this.UpPressed = false;
    }
    void unToggleDownPressed() {
        this.DownPressed = false;
    }
    void unToggleRightPressed() {
        this.RightPressed = false;
    }
    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }
    void update() {
        if(game.t1.getBounds().intersects(game.t2.getBounds()) ){
            this.moveBackwards();
            game.t2.moveBackwards();
        }

        if (this.UpPressed && !this.LeftPressed && !this.RightPressed ) {
            this.moveForwards();
        }
        if (this.DownPressed && !this.LeftPressed && !this.RightPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed && !this.UpPressed && !this.DownPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed && !this.UpPressed && !this.DownPressed) {
            this.rotateRight();
        }
        if (collision()) {
            System.out.println("collision Detected");
            isColided = true;
        }
    }
    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    private void rotateLeft() {
        super.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        super.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {

        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        collision();
        checkBorder();

    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        collision();
        checkBorder();
    }


    private void checkBorder() {
//?        if(this.getBounds().intersects()){

//        }
        if (x < 30) {
            x = 30;
        }
        if (x >= GameConstants.GAME_SCREEN_WIDTH - 88) {
            x = GameConstants.GAME_SCREEN_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameConstants.GAME_SCREEN_HEIGHT - 80) {
            y = GameConstants.GAME_SCREEN_HEIGHT - 80;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), super.img.getWidth() / 2.0, super.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(super.img, rotation, null);
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, super.img.getWidth(), super.img.getHeight());
    }
    private boolean collision() {
        for (int i = 0;i<game.wallsPopulator.getWalls().size();i++)
        if(this.getBounds().intersects(game.wallsPopulator.getWalls().get(i).getBounds()) && !game.wallsPopulator.getWalls().get(i).isBreakable())
        {
            moveBackwards();
        }
        return game.wall.getBounds().intersects(this.getBounds());
    }
}