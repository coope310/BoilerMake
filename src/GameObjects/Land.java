package GameObjects;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Land extends Tile {
    private boolean hasCrop = false;
    private boolean readyToHarvest = false;
    private Plant crop;
    private int cropAge = 0;

    public boolean hasCrop() {return hasCrop;}
    public boolean readyToHarvest() {return readyToHarvest;}
    public Plant getCrop() {return crop;}

    static Image land0;
    static Image land1;
    static Image land2;
    static Image land3;
    static Image land4;
    static Image land5;


    public Land(Rectangle r) {
        super(r);
    }

    @Override
    public Graphics draw(Graphics g, ImageObserver it) {
        Color fontColor = Color.WHITE;
        Image i = land0;
        if(hasCrop) {
            i = land1;
            if (cropAge > 2 * (crop.getTimeToGrowInTime() / 5)) {
                i = land2;
            }
            if (cropAge > 3 * (crop.getTimeToGrowInTime() / 5)) {
                i = land3;
            }
            if (cropAge > 4 * (crop.getTimeToGrowInTime() / 5)) {
                i = land4;
                fontColor = (Color.BLACK);
            }
        }
        if(readyToHarvest) {
            i = land5;
            fontColor = (Color.BLACK);

        }



        g.drawImage(i, getBounds().x, getBounds().y, getBounds().width, getBounds().height, it);
        g.setColor(Color.BLACK);
        g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);

        if(hasCrop) {
            g.setColor(fontColor);
            g.setFont(new Font("TimesRoman", Font.BOLD, 28));
            int offset = 6;
            String letter = "";
            switch (crop) {
                case WHEAT:
                    letter = "W";
                    break;
                case CORN:
                    letter = "C";
                    break;
            }
            g.drawString(letter, getBounds().x + offset, getBounds().y + getBounds().height - offset);
        }

        return g;
    }

    public void update(double timePassed, int waterGiven) {
        if(hasCrop) {
            if (waterGiven >= (crop.getWaterCost() * timePassed)) {
                cropAge += timePassed;
            } else {
                cropAge -= timePassed;
            }
            if (cropAge >= crop.getTimeToGrowInTime()) {
                readyToHarvest = true;
            }
            if(cropAge < 0) {
                //plant dies!
                readyToHarvest = false;
                hasCrop = false;
                cropAge = 0;
            }

        }
    }

    public void age(double timePassed) {
        cropAge += timePassed;
        if(cropAge > 3*crop.getTimeToGrowInTime()) { //rots, plant goes away
            readyToHarvest = false;
            hasCrop = false;
            cropAge = 0;
        }
    }

    //returns amount of gold given for the crop harvest
    public int harvestCrop() {
        if(readyToHarvest) {
            readyToHarvest = false;
            hasCrop = false;
            cropAge = 0;
            return crop.getPrice();
        } else {
            return 0;
        }
    }

    public static void initGraphics(Toolkit t) {
        land0 = t.getImage("land.jpg");
        land1 = t.getImage("land1.jpg");
        land2 = t.getImage("land2.jpg");
        land3 = t.getImage("land3.jpg");
        land4 = t.getImage("land4.jpg");
        land5 = t.getImage("land5.jpg");
    }

    public void plantCrop(Plant p) {
        hasCrop = true;
        readyToHarvest = false;
        crop = p;
    }
}
