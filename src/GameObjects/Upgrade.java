package GameObjects;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Upgrade {
    private int xPos;
    private int yPos;
    static Image title;
    static Image wheat;
    static Image corn;
    static Image beans;
    private int cost;
    Rectangle hitbox;

    public Upgrade(int xPos, int yPos, int cost) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.cost = cost;
        hitbox = new Rectangle(xPos, yPos, 100, 60);
    }

    public Graphics draw(Graphics g, ImageObserver it) {
        switch (cost) {
            case 0:
                g.drawImage(title, xPos, yPos, it);
                break;
            case 75:
                g.drawImage(wheat, xPos, yPos, it);
                break;
            case 100:
                g.drawImage(corn, xPos, yPos, it);
                break;
        }
        return g;
    }

    public static void initGraphics(Toolkit t) {
        title = t.getImage("shopTitle.jpg");
        wheat = t.getImage("wheatU.jpg");
        corn = t.getImage("cornU.jpg");
    }
}
