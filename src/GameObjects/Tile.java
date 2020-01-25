package GameObjects;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Tile {
    private int xPos;
    private int yPos;
    private int size;
    static Image i;

    public Graphics draw(Graphics g, ImageObserver it) {
        g.drawImage(i, xPos, yPos, it);
        return g;
    }

    public static void initGraphics(Toolkit t) {
        i = t.getImage("tile.jpg");
    }
}
