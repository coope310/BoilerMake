package animation;
/**
 * Class ArcadeDemo
 * This class contains demos of many of the things you might
 * want to use to make an animated arcade game.
 *
 * Adapted from the AppletAE demo from years past.
 */

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.applet.AudioClip;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Rectangle;


public class ArcadeDemo extends AnimationPanel
{

    //Constants
    //-------------------------------------------------------
    //Instance Variables
    //-------------------------------------------------------


    //Constructor
    //-------------------------------------------------------
    public ArcadeDemo()
    {   //Enter the name and width and height.
        super("AgriHack", 750, 550);
    }

    //The renderFrame method is the one which is called each time a frame is drawn.
    //-------------------------------------------------------
    protected Graphics renderFrame(Graphics g) {


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
        Toolkit toolkit = Toolkit.getDefaultToolkit();

    } //--end of initGraphics()--

    //-------------------------------------------------------
    //Initialize Sounds
    //-------------------------------------------------------
//-----------------------------------------------------------------------
/*  Music section...
 *  To add music clips to the program, do four things.
 *  1.  Make a declaration of the AudioClip by name ...  AudioClip clipname;
 *  2.  Actually make/get the .wav file and store it in the same directory as the code.
 *  3.  Add a line into the initMusic() function to load the clip.
 *  4.  Use the play(), stop() and loop() functions as needed in your code.
//-----------------------------------------------------------------------*/
    AudioClip themeMusic;
    AudioClip bellSound;

    public void initMusic()
    {
        themeMusic = loadClip("under.wav");
        bellSound = loadClip("ding.wav");
        //if(themeMusic != null)
        //themeMusic.loop(); //This would make it play!
    }

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}//--end of ArcadeDemo class--
