import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import java.util.ArrayList;

/**
 * Superclass for various effects in the world
 * 
 * @author Andy
 * @version (a version number or a date)
 */
public class Effect extends Actor
{
    protected GreenfootImage image;
    protected int animationIndex;
    protected ArrayList<GreenfootImage> animation = new ArrayList<GreenfootImage>();
    //Percentage of the image size
    protected double imageScale;
    protected SimpleTimer animationTimer = new SimpleTimer();
    
    protected void fade (int actsLeft, int fadeLength){
        double percent = (double)actsLeft / fadeLength;
        if (percent > 1.0){
            return;
        }
        int trans = (int)(percent * 255);
        getImage().setTransparency(trans);
    }
    
    protected void loadAnimationFrames(String path)
    {
        for(int i = 0; i < new File("images/Effects/" + path).listFiles().length-1; i++)
        {
            animation.add(new GreenfootImage("images/Effects/" + path + "/" + i + ".png"));
            animation.get(i).scale((int)(animation.get(i).getWidth()*imageScale),(int)(animation.get(i).getHeight()*imageScale));
        }
    }
    
    /**
     * Simple Animations
     */
    protected int animate(ArrayList<GreenfootImage> animation, int index)
    {
        setImage(animation.get(index));
        index++;
        if(index > animation.size()-1)
        {
            index = 0;
        }
        return index;
    }
}
