package GameObjects;

import java.awt.*;
import java.awt.image.ImageObserver;

public interface GameObject {
    public Graphics draw(Graphics g, ImageObserver it);
    public void initGraphics(Toolkit t);
}
