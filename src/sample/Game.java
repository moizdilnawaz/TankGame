/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

/**
 * @author anthony-pc
 */
public class Game extends JPanel implements Runnable {

    private BufferedImage world;
    public Player player1;
    public Player player2;
    public Tank t1;
    public Tank t2;
    TankControl tc1;
    TankControl tc2;
    private Launcher lf;
    private long tick = 0;
    public Wall wall;
    BufferedImage wallImage = null;
    public WallsPopulator wallsPopulator;
    BufferedImage unBreakablWallImage = null;
    public int scorePlayer1 = 0;
    public int scorePlayer2 = 0;
    public boolean gameOver=false;
    Icon imgIcon;
    JLabel label;
    Pickup pickupObject ;
    public Game(Launcher lf) {
        this.lf = lf;
    }

    public ArrayList<Wall> walls = new ArrayList<Wall>();
//    public ArrayList<Wall> unBreakabaleWalls = new ArrayList<Wall>();
    public BufferedImage bulletImage;
    public int wallsCount = 5;

    @Override
    public void run() {
        try {
            this.resetGame();
            while (true) {
                this.tick++;
                this.t1.update(); // update tank
                this.t2.update();
                this.repaint();   // redraw game
                Thread.sleep(1000 / 144); //sleep for a few milliseconds
                /*
                 * simulate an end game event
                 * we will do this with by ending the game when drawn 2000 frames have been drawn
                 */
                if (this.gameOver) {
                    Thread.sleep(5000);
                    this.lf.setFrame("end");
                    return;
                }
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }

    /**
     * Reset game to its initial state.
     */
    public void resetGame() {
        this.gameOver= false;
        this.tick = 0;
        this.t1.setX(300);
        this.t1.setY(300);
        this.t2.setX(400);
        this.t2.setY(350);
    }
    public Game(){
        new Thread(this).start();

    }
    /**
     * Load all resources for Tank Wars Game. Set all Game Objects to their
     * initial state as well.
     */
    public void gameInitialize() {
        this.world = new BufferedImage(GameConstants.GAME_SCREEN_WIDTH,
                GameConstants.GAME_SCREEN_HEIGHT,
                BufferedImage.TYPE_INT_RGB);
//        this.world = null;

//        try {
//            this.world = read(new File("E:\\TankGame\\TankWarResources\\background.bmp"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        bulletImage = null;
        BufferedImage t1img = null;
        BufferedImage t2img = null;
        BufferedImage blastImage = null;
        BufferedImage noofLivesImage = null;
        BufferedImage pickUpImage = null;
        try {
            /*
             * note class loaders read files from the out folder (build folder in Netbeans) and not the
             * current working directory.
             */
//            t1img = read(Objects.requireNonNull(TRE.class.getClassLoader().getResource("tank1.png")));
//            noofLivesImage= read(getClass().getResourceAsStream("Res\\heart.png"));
//            InputStream in = getClass().getResourceAsStream("/res/heart.png");



//            noofLivesImage = read(new File("E:\\TankGame\\Res\\heart.png"));
            noofLivesImage= ImageIO.read(getClass().getResourceAsStream("/res/heart.png"));
            blastImage= ImageIO.read(getClass().getResourceAsStream("/res/explosion_small.gif"));
//            blastImage= ImageIO.read(getClass().getResourceAsStream("/res/heart.png"));
            t1img = ImageIO.read(getClass().getResourceAsStream("/res/tank1.png"));
            t2img = ImageIO.read(getClass().getResourceAsStream("/res/tank2.png"));
            bulletImage = ImageIO.read(getClass().getResourceAsStream("/res/weapon.gif"));
            wallImage = ImageIO.read(getClass().getResourceAsStream("/res/wall1.gif"));
            unBreakablWallImage = ImageIO.read(getClass().getResourceAsStream("/res/wall2.gif"));
            pickUpImage = ImageIO.read(getClass().getResourceAsStream("/res/pickupa.png"));
//            this.world = read(new File("E:\\TankGame\\TankWarResources\\background.bmp"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        wallsPopulator = new WallsPopulator();

        t1 = new Tank(300, 300, 0, 0, 0, t1img, this);
        player1 = new Player(3 ,new TankHealth(10 , 10 ,new Rectangle()));
        player1.setNoOfLives(noofLivesImage);
        t2 = new Tank(300, 300, 0, 0, 0, t2img, this);
        player2 = new Player(3 ,new TankHealth(10 , 10 ,new Rectangle()));
        player2.setNoOfLives(noofLivesImage);
        this.wall = new Wall(200, 300, 0, wallImage, this);
        wallsPopulator();
        tc1 = new TankControl(t1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
        player1.getHealth().getHealthbar().setRect(tc1.getT1().getX(),tc1.getT1().getY()-30,70,10);
        player1.getPoints().add(new Point(45,50));
        player1.getPoints().add(new Point(85,50));
        player1.getPoints().add(new Point(125,50));
        player1.getHealth().setDamagefector(0);
        tc2 = new TankControl(t2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_NUMPAD0);
        player2.getHealth().getHealthbar().setRect(tc2.getT1().getX(),tc2.getT1().getY()-30,70,10);
//        player2.setPoints(new ArrayList<Point>());
        player2.getPoints().add(new Point(800,50));
        player2.getPoints().add(new Point(850,50));
        player2.getPoints().add(new Point(900,50));
        player2.getHealth().setDamagefector(0);
        int randomX = (int)(500* Math.round(Math.random()));
        int randomY = (int)(500* Math.round(Math.random()));
        pickupObject =new Pickup(pickUpImage,randomX* 100,randomY*100,0);
//        tc1.setBullet(new Bullet(tc1.getT1().getX(), tc1.getT1().getY(), tc1.getT1().getVx(), tc1.getT1().getVy(),tc1.getT1().getAngle(), bulletImage, tc1, this));
        tc1.setBullet(new Bullet(320, 317, 0, 0, tc1.getT1().getAngle(), bulletImage, tc1, this, 1));
        tc1.getBullet().setExplosionImage(blastImage);
        tc2.setBullet(new Bullet(tc2.getT1().getX(), tc2.getT1().getY(), tc2.getT1().getVx(), tc2.getT1().getVy(), tc2.getT1().getAngle(), bulletImage, tc2, this , 2));
        tc2.getBullet().setExplosionImage(blastImage);
        this.setBackground(Color.WHITE);
        this.lf.getJf().addKeyListener(tc1);
        this.lf.getJf().addKeyListener(tc2);
    }
    public void wallsPopulator() {
        wallsPopulator.setWallsdown(new ArrayList<Boolean>());
        int verticalWallDifrence = 30;
        walls.add(new Wall(20, 20, 0, wallImage, this));
        wallsPopulator.getWallsdown().add(false);
        int lastY = 20;
        for (int i = 0; i <= 20; i++) {
            walls.add(new Wall(20, lastY + verticalWallDifrence, 0, wallImage, this));
            walls.get(walls.size()-1).setBreakable(true);
            lastY = lastY + verticalWallDifrence;
            wallsPopulator.getWallsdown().add(false);
        }
        lastY = 20;
        walls.add(new Wall(995, 20, 0, wallImage, this));
        wallsPopulator.getWallsdown().add(false);
        for (int i = 0; i <= 20; i++) {
            walls.add(new Wall(995, lastY + verticalWallDifrence, 0, wallImage, this));
            walls.get(walls.size()-1).setBreakable(true);
            lastY = lastY + verticalWallDifrence;
            wallsPopulator.getWallsdown().add(false);
        }
///x axis
        int lastX = 20;
        int horizontalWallDifrence = 32;
        for (int i = 0; i <= 30; i++) {
            walls.add(new Wall(lastX + horizontalWallDifrence, 20, 0, wallImage, this));
            walls.get(walls.size()-1).setBreakable(true);
            lastX = lastX + horizontalWallDifrence;
            wallsPopulator.getWallsdown().add(false);
        }
        lastX = 20;
        for (int i = 0; i <= 30; i++) {
            walls.add(new Wall(lastX + horizontalWallDifrence, 640, 0, wallImage, this));
            walls.get(walls.size()-1).setBreakable(true);
            lastX = lastX + horizontalWallDifrence;
            wallsPopulator.getWallsdown().add(false);
        }
        lastY = 280;
        for (int i = 0; i <= 10; i++) {
            walls.add(new Wall(555, lastY + verticalWallDifrence, 0, unBreakablWallImage, this));
            walls.get(walls.size()-1).setBreakable(false);
            lastY = lastY + verticalWallDifrence;
            wallsPopulator.getWallsdown().add(false);
        }
        lastY = 280;
        for (int i = 0; i <= 10; i++) {
            walls.add(new Wall(585, lastY + verticalWallDifrence, 0, unBreakablWallImage, this));
            walls.get(walls.size()-1).setBreakable(false);
            lastY = lastY + verticalWallDifrence;
            wallsPopulator.getWallsdown().add(false);
        }
        wallsPopulator.setWalls(walls);
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D buffer = world.createGraphics();

        player1.getHealth().getHealthbar().setRect(tc1.getT1().getX(),tc1.getT1().getY()-30,70,10);
        player2.getHealth().getHealthbar().setRect(tc2.getT1().getX(),tc2.getT1().getY()-30,70,10);
        tc1.getBullet().setLocation(tc1.getT1().getX() + 20, tc1.getT1().getY() + 17, tc1.getT1().getVx(), tc1.getT1().getVy(), tc1.getT1().getAngle());
        tc2.getBullet().setLocation(tc2.getT1().getX() + 20, tc2.getT1().getY() + 17, tc2.getT1().getVx(), tc2.getT1().getVy(), tc2.getT1().getAngle());
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, GameConstants.GAME_SCREEN_WIDTH, GameConstants.GAME_SCREEN_HEIGHT);
        try {
            Dimension d = getSize();
            buffer.drawImage(ImageIO.read(getClass().getResourceAsStream("/res/background.bmp")), 0, 0, d.width + 30, d.height + 30, null);

//            System.out.println(Objects.requireNonNull(Game.class.getClassLoader().getResource("/Res/tank1.png")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        detectCollision();
        for (int i = 0; i < walls.size(); i++) {
            if (!wallsPopulator.getWalls().get(i).isColided && wallsPopulator.getWalls().get(i).isBreakable() ){
                if (!wallsPopulator.getWallsdown().get(i)) {
                    wallsPopulator.getWalls().get(i).drawImage(buffer);
                }else {

                    if(tc1.getBullet().isWallColided()) {
                        buffer.drawImage(tc1.getBullet().getExplosionImage(), tc1.getBullet().getColisionX(), tc1.getBullet().getColisionY(), null);
                        tc1.getBullet().setWallColided(false);
                    }
                    if(tc2.getBullet().isWallColided()) {
                        buffer.drawImage(tc2.getBullet().getExplosionImage(), tc2.getBullet().getColisionX(), tc2.getBullet().getColisionY(), null);
                        tc2.getBullet().setWallColided(false);
                    }
                }
            }
        }
        for (Wall value : walls) {
            if (!value.isBreakable()) {
                value.drawImage(buffer);
            }
        }
        Font font  = buffer.getFont().deriveFont(40.0f);
        buffer.setFont(font);
        buffer.setColor(new Color(0,255,0));
        buffer.drawString(""+scorePlayer1,455,100);
        buffer.setColor(new Color(255,0,0));
        buffer.drawString(""+scorePlayer2,550,100);
        player1.getHealth().drawRectangle(buffer,Color.RED);
        player2.getHealth().drawRectangle(buffer,Color.RED);
        player1.drawLifes(buffer);
        player2.drawLifes(buffer);
        tc1.getBullet().drawImage(buffer);
        tc2.getBullet().drawImage(buffer);
        this.t1.drawImage(buffer);
        this.t2.drawImage(buffer);
//        if(!t1.getBounds().intersects(pickupObject.getBounds()) || !t2.getBounds().intersects(pickupObject.getBounds()) && !pickupObject.isPickedUp ){
//            pickupObject.draw(buffer);
//        }
        if (t1.getBounds().intersects(pickupObject.getBounds()) ) {
            t1.setR(4);
            pickupObject.setPickedUp(true);
        }
        else if (t2.getBounds().intersects(pickupObject.getBounds()) ) {
            t2.setR(4);
            pickupObject.setPickedUp(true);
        }

        if(!pickupObject.isPickedUp()){
            pickupObject.draw(buffer);
        }
        if (player1.isSetDefeated()){
            font  = buffer.getFont().deriveFont(100.0f);
            buffer.setFont(font);
            buffer.setColor(new Color(0,0,255));
            buffer.drawString("Tank 2 Won the Game",450,450);
            gameOver=true;
        }else if(player2.isSetDefeated()) {
            font  = buffer.getFont().deriveFont(40.0f);
            buffer.setFont(font);
            buffer.setColor(new Color(0,0,255));
            buffer.drawString("Tank 1 Won the Game",450,450);
            gameOver=true;
        }
        g2.drawImage(world, -20, -20, null);
    }
    public void detectCollision() {
        for (int i = 0; i < walls.size(); i++) {
            walls.get(i).detectCollision();
        }
    }
}