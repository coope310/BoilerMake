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
    static Image rice;
    static Image tobacco;
    static Image water;
    static Image rainBarrels;
    private int cost;
    Rectangle hitbox;

    public Upgrade(int xPos, int yPos, int cost) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.cost = cost;
        if (cost < 300) {
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

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void increaseInventory() {
        inventory++;
    }

    public void decreaseInventory() {
        inventory--;
    }

    public Graphics draw(Graphics g, ImageObserver it) {
        switch (cost) {
            case 25:
                g.drawImage(beans, xPos, yPos, it);
                break;
            case 75:
                g.drawImage(wheat, xPos, yPos, it);
                break;
            case 100:
                g.drawImage(corn, xPos, yPos, it);
                break;
            case 325:
                g.drawImage(rice, xPos, yPos, it);
                break;
            case 400:
                g.drawImage(tobacco, xPos, yPos, it);
                break;
            case 1250:
                g.drawImage(water, xPos, yPos, it);
        }
        if (cost % 500 == 0) {
            g.drawImage(rainBarrels, xPos, yPos, it);
        }
        return g;
    }

    public static void initGraphics(Toolkit t) {
        wheat = t.getImage("wheatU.jpg");
        corn = t.getImage("cornU.jpg");
        beans = t.getImage("bean.jpg");
        rice = t.getImage("rice.jpg");
        tobacco = t.getImage("tobacco.jpg");
        water = t.getImage("water.jpg");
        rainBarrels = t.getImage("rainBarrels.jpg");
    }
}
