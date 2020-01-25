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
import java.util.Random;

import static GameObjects.Plant.CORN;
import static GameObjects.Plant.WHEAT;

public class ArcadeDemo extends AnimationPanel
{

    //Constants
    //-------------------------------------------------------
    Image title;
    Image timeImage;
    Image pauseImage;
    Image arrowDown;
    Image arrowRight;//i started to add arrows so you can select entire rows but i think that will take too long so nevermind

    Image sickle;

    double rainChance = 0.05;

    //Game Variables
    //-------------------------------------------------------
    int money = 5000;
    int water = 10000;

    int plantChoice = -1;

    double time = 0;
    boolean timeMoves = true;
    double timeRate = 1;

    Circle timeCircle = new Circle(10, 470, 45);
    Circle pauseCircle = new Circle(10, 420, 45);
    Rectangle harvestBounds = new Rectangle(10, 370, 45, 45);
    boolean harvestMode = false;

    //rain
    //upgrading your rain barrels increases this
    double baseRainAmount = 10;

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

    ArrayList<InventoryButtons> inventoryButtons = new ArrayList<>();
    //Constructor
    //-------------------------------------------------------
    public ArcadeDemo()
    {   //Enter the name and width and height.
        super("AgriHack", 900, 575);
        for(int i = 0; i < gridWidth; i++) {
            for(int j = 0; j < gridHeight; j++) {
                lands.add(new Land(new Rectangle(farmGrid.x + i*tileSize, farmGrid.y + j*tileSize, tileSize, tileSize)));
            }
        }

        for (int i = 0; i < 2; i++) {
            inventoryButtons.add(new InventoryButtons(new Rectangle(675, 80 + 60 * i, 60, 60 ), i));
        }
    }

    //The renderFrame method is the one which is called each time a frame is drawn.
    //-------------------------------------------------------
    protected Graphics renderFrame(Graphics g) {

        //background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        //stat variables
        g.setColor(Color.BLACK);

        g.drawString("Money:", 10, 20);
        g.drawString("$" + money, 10, 33);
        g.drawString("time: " + ((int) time*10)/10, 10, 55);
        g.drawString("water: " + water, 10, 70);

        //will it rain?
        if(timeMoves) {
            for (int i = 0; i < timeRate; i++) {
                double rainToday = Math.random();
                if (rainToday < rainChance) {
                    water += baseRainAmount * (0.5 + Math.random());
                }
            }
        }

        g.fillRect(farmGrid.x, farmGrid.y, farmGrid.width, farmGrid.height);
        for(Land l : lands) {
            l.draw(g, this);
            if(timeRate > 0) {
                if(l.hasCrop()) {
                    if (!l.readyToHarvest()) {
                        Plant p = l.getCrop();
                        int waterNeeded = p.getWaterCost();
                        if (waterNeeded < water) {
                            water -= waterNeeded;
                            l.update( timeRate, (int) Math.round((waterNeeded * timeRate)));
                        } else {
                            l.update(timeRate, 0);
                        }
                    }

                }
            }
        }

        g.drawImage(title, 575, 20, this);
        for (InventoryButtons ib : inventoryButtons) {
            if(s.upgradeList.get(ib.getPlantChoice()).getInventory() == 0) {
                ib.setSelected(false);
            }
            if (ib.isSelected()) {
                g.setColor(Color.RED);
            }
            g.drawRect(ib.getBounds().x, ib.getBounds().y, ib.getBounds().width, ib.getBounds().height);
            g.setColor(Color.BLACK);
        }

        for (Upgrade u : s.upgradeList) {
            u.draw(g, this);
            if (u.getCost() < 400) {
                g.setFont(new Font("TimesRoman", Font.BOLD, 18));
                g.drawString(u.getInventory() + "", u.getxPos() + 120, u.getyPos() + 40);
            }
        }

        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));

        farm.draw(g, this);

        //harvesting
        g.drawImage(sickle, harvestBounds.x, harvestBounds.y, harvestBounds.width, harvestBounds.height, this);
        if(harvestMode) {
            g.setColor(Color.BLACK);
            g.drawRect(harvestBounds.x, harvestBounds.y, harvestBounds.width, harvestBounds.height);
        }
        //TIME STUFF
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawImage(timeImage, timeCircle.getxPos(), timeCircle.getyPos(), timeCircle.getRadius(), timeCircle.getRadius(), this);
        if(timeMoves)
            time += timeRate/10;
        g.setColor(Color.BLACK);
        drawCenteredString(g, timeRate + "", timeCircle.toRect());
        //pause button
        g.drawImage(pauseImage, pauseCircle.getxPos(), pauseCircle.getyPos(), pauseCircle.getRadius(), pauseCircle.getRadius(), this);
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
        if (timeCircle.contains(mouseLoc)) {
            if (timeRate < 16) {
                timeRate *= 2;
            } else {
                timeRate = 0.5;
            }
        }

        if (harvestBounds.contains(mouseLoc)) {
            harvestMode = !harvestMode;
        }

        for (InventoryButtons ib : inventoryButtons) {
            if (ib.getBounds().contains(mouseLoc)) {
                if (ib.isSelected()) {
                    ib.setSelected(false);
                    plantChoice = -1;
                    break;
                }
                for (InventoryButtons ib2 : inventoryButtons) {
                    ib2.setSelected(false);
                }
                if (s.getUpgradeList().get(ib.getPlantChoice()).getInventory() > 0) {
                    plantChoice = ib.getPlantChoice();
                    ib.setSelected(true);
                }
            }
        }

        if (pauseCircle.contains(mouseLoc)) {
            timeMoves = !timeMoves;
        }

        for (Land l : lands) {
            if (l.getBounds().contains(mouseLoc)) {
                if (!harvestMode) {
                    if (plantChoice != -1) {
                        if (s.getUpgradeList().get(plantChoice).getInventory() <= 0) {
                            inventoryButtons.get(plantChoice).setSelected(false);
                            plantChoice = -1;
                            break;
                        }
                        switch (plantChoice) {
                            case 0:
                                l.plantCrop(WHEAT);
                                break;
                            case 1:
                                l.plantCrop(CORN);
                                break;
                        }
                        s.getUpgradeList().get(plantChoice).decreaseInventory();
                    }
                } else {
                    Plant crop = l.getCrop();
                    if (l.readyToHarvest()) {
                        l.harvestCrop();
                        money += crop.getPrice();
                    }
                }
            }

        }
            for (int i = 0; i < s.getUpgradeList().size(); i++) {
                Upgrade current = s.getUpgradeList().get(i);
                if (current.getHitbox().contains(mouseLoc)) {
                    money -= current.getCost();
                    switch (i) {
                        case 2:
                            baseRainAmount *= 1.5;
                            s.getUpgradeList().remove(i);
                    }
                    current.increaseInventory();
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

        char c = e.getKeyChar();
        if(c == 'h') {
            for(Land l : lands) {
                Plant crop = l.getCrop();
                if (l.readyToHarvest()) {
                    l.harvestCrop();
                    money += crop.getPrice();
                }
            }
        }

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
        timeImage = t.getImage("time.jpg");
        pauseImage = t.getImage("pause.jpg");
        arrowDown = t.getImage("arrowdown.jpg");
        arrowRight = t.getImage("arrowright.jpg");
        title = t.getImage("shopTitle.jpg");
        sickle = t.getImage("sickle.png");

    } //--end of initGraphics()--

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}//--end of ArcadeDemo class--
