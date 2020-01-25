package animation;
/**
 * Class ArcadeDemo
 * This class contains demos of many of the things you might
 * want to use to make an animated arcade game.
 *
 * Adapted from the AppletAE demo from years past.
 */

import GameObjects.*;
import util.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class ArcadeDemo extends AnimationPanel
{

    //Constants
    //-------------------------------------------------------
    //Game Variables
    //-------------------------------------------------------
    int money = 5000;
    int water = 100;

    int time = 0;
    double timeRate = 1;

    Circle timeCircle = new Circle(10, 470, 50);
    //Instance Variables
    //-------------------------------------------------------
    Shop s = new Shop();
    ArrayList<Land> lands = new ArrayList<>();
    Rectangle farmGrid = new Rectangle(75, 20, 500, 500);
    int gridWidth = 10;
    int gridHeight = 10;
    int tileSize = 50;
    int farmSize = 200;
    Farm farm = new Farm(new Rectangle(farmGrid.x + farmGrid.width/2-farmSize/2, farmGrid.y + farmGrid.height/2-farmSize/2, farmSize, farmSize));
    //Constructor
    //-------------------------------------------------------
    public ArcadeDemo()
    {   //Enter the name and width and height.
        super("AgriHack", 750, 575);
        for(int i = 0; i < gridWidth; i++) {
            for(int j = 0; j < gridHeight; j++) {
                lands.add(new Land(new Rectangle(farmGrid.x + i*tileSize, farmGrid.y + j*tileSize, tileSize, tileSize), "empty"));
            }
        }
    }

    //The renderFrame method is the one which is called each time a frame is drawn.
    //-------------------------------------------------------
    protected Graphics renderFrame(Graphics g) {
        g.setColor(Color.BLACK);

        g.drawString("Money:", 10, 20);
        g.drawString("$" + money, 10, 33);
        g.fillRect(farmGrid.x, farmGrid.y, farmGrid.width, farmGrid.height);
        for(Land l : lands) {
            l.draw(g, this);
        }

        for (Upgrade u : s.upgradeList) {
            u.draw(g, this);
            if (u.getCost() != 0) {
                g.setFont(new Font("TimesRoman", Font.BOLD, 18));
                g.drawString(u.getInventory() + "", u.getxPos() + 120, u.getyPos() + 40);
            }
        }
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));

        farm.draw(g, this);

        //TIME STUFF
        g.setColor(Color.WHITE);
        timeCircle.draw(g);
        time += timeRate;
        g.setColor(Color.BLACK);
        g.drawString(timeRate + "", 25, 500);
        return g;
    }//--end of renderFrame method--





    /**
     * NOT MY CODE "BORROWED" FROM STACK OVERFLOW
     * Draw a String centered in the middle of a Rectangle.
     *
     * @param g The Graphics instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     */
    public void drawCenteredString(Graphics g, String text, Rectangle rect) {
        // Get the FontMetrics
        //imma alter it so that i don't have to pass the font, only planning to use one font throughout the entire thing
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Draw the String
        g.drawString(text, x, y);
    }


    //-------------------------------------------------------
    //Respond to Mouse Events
    //-------------------------------------------------------
    public void mouseClicked(MouseEvent e) {
        Point mouseLoc = new Point(e.getX(), e.getY());
        if(timeCircle.contains(mouseLoc)) {
            if(timeRate < 16) {
                timeRate *= 2;
            } else {
                timeRate = 0.5;
            }
        }

        for (Upgrade u : s.getUpgradeList()) {
            if (u.getHitbox().contains(mouseLoc)) {
                money -= u.getCost();
                u.increaseInventory();
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        Point mouseLoc = new Point(e.getX(), e.getY());

    }

    //-------------------------------------------------------
    //Respond to Keyboard Events
    //-------------------------------------------------------
    public void keyTyped(KeyEvent e)
    {

        char c = e.getKeyChar();
    }

    public void keyPressed(KeyEvent e)
    {

        int v = e.getKeyCode();

    }

    public void keyReleased(KeyEvent e)
    {
        int v = e.getKeyCode();
    }


    //-------------------------------------------------------
    //Initialize Graphics
    //-------------------------------------------------------
//-----------------------------------------------------------------------
/*  Image section...
 *  To add a new image to the program, do three things.
 *  1.  Make a declaration of the Image by name ...  Image imagename;
 *  2.  Actually make the image and store it in the same directory as the code.
 *  3.  Add a line into the initGraphics() function to load the file.
//-----------------------------------------------------------------------*/
    public void initGraphics()
    {
        Toolkit t = Toolkit.getDefaultToolkit();
        Tile.initGraphics(t);
        Land.initGraphics(t);
        Farm.initGraphics(t);
        Upgrade.initGraphics(t);

    } //--end of initGraphics()--

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}//--end of ArcadeDemo class--
