package GameObjects;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Land extends Tile {
    private String type;
    static Image i;

    public Land(Rectangle r, String t) {
        super(r);
        type = t;
    }

    @Override
    public Graphics draw(Graphics g, ImageObserver it) {
        g.drawImage(i, getBounds().x, getBounds().y, it);
        return g;
    }
    public static void initGraphics(Toolkit t) {
        i = t.getImage("land.jpg");
    }

}
