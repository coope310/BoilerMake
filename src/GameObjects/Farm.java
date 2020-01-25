package GameObjects;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Farm {
    static Image i;
    private Rectangle bounds;
    
    public Farm(Rectangle r) {
        bounds = r;
    }
    
    public Graphics draw(Graphics g, ImageObserver it) {
        g.drawImage(i, bounds.x, bounds.y, 200, 200, it);

        return g;
    }
    public static void initGraphics(Toolkit t) {
        i = t.getImage("farm.jpg");

    }

    public Rectangle getBounds() {
        return bounds;
    }
}
