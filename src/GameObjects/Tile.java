package GameObjects;

import GameObjects.GameObject;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Tile implements GameObject {
    private int xPos;
    private int yPos;
    private int size;
    private Image i;

    public Graphics draw(Graphics g, ImageObserver it) {
        g.drawImage(i, xPos, yPos, it);
        return g;
    }

    @Override
    public void initGraphics(Toolkit t) {
        t.getImage("tile.jpg");
    }
}
