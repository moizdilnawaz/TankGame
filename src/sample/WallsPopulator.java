package sample;

import java.awt.*;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public class WallsPopulator {
    private ArrayList<Boolean> wallsdown;
    private ArrayList<Wall> walls;

    public WallsPopulator(ArrayList<Boolean> wallsdown, ArrayList<Wall> walls) {
        this.wallsdown = wallsdown;
        this.walls = walls;
    }

    public WallsPopulator() {
    }

    public ArrayList<Boolean> getWallsdown() {
        return wallsdown;
    }

    public void setWallsdown(ArrayList<Boolean> wallsdown) {
        this.wallsdown = wallsdown;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    public void draw(Graphics g) {
        Graphics2D buffer = (Graphics2D) g;
        for (Wall wall : walls) {
            wall.drawImage(buffer);
        }
    }
}
