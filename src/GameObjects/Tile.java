package GameObjects;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Tile {
    private Rectangle bounds;
    static Image i;

    public Tile(Rectangle r) {
        bounds = r;
    }

    public Graphics draw(Graphics g, ImageObserver it) {
        g.drawImage(i, bounds.x, bounds.y, it);
        return g;
    }

    public static void initGraphics(Toolkit t) {
        i = t.getImage("tile.jpg");
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
