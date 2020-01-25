package util;

import java.awt.*;

public class Circle {
    private int xPos;
    private int yPos;
    private int radius;

    public Circle(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    public Graphics draw(Graphics g) {
        g.fillArc(xPos, yPos, radius, radius, 0, 360);
        return g;
    }

    public boolean contains(Point p) {
        double distance = p.distance(new Point(xPos, yPos));
        return distance < radius;
    }

}
