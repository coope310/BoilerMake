package GameObjects;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Upgrade {
    private int xPos;
    private int yPos;
    private int inventory;
    static Image wheat;
    static Image corn;
    static Image beans;
    private int cost;
    Rectangle hitbox;

    public Upgrade(int xPos, int yPos, int cost) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.cost = cost;
        if (cost != 0) {
            inventory = 0;
        }
        hitbox = new Rectangle(xPos, yPos, 100, 60);
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getCost() {
        return cost;
    }

    public int getInventory() {
        return inventory;
    }

    public void increaseInventory() {
        inventory++;
    }

    public void decreaseInventory() {
        inventory--;
    }

    public Graphics draw(Graphics g, ImageObserver it) {
        switch (cost) {
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
        wheat = t.getImage("wheatU.jpg");
        corn = t.getImage("cornU.jpg");
    }
}
