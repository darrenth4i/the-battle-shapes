import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import java.util.ArrayList;

/**
 * Write a description of class LoadingSpinner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoadingSpinner extends Graphic
{
    private ArrayList<GreenfootImage> animation = new ArrayList<GreenfootImage>();
    private SimpleTimer animationTimer = new SimpleTimer();
    private int index = 0;
    
    public LoadingSpinner()
    {
        super();
        loadAnimationFrames("images/UIElements/Loading/");
    }
    
    /**
     * Act - do whatever the LoadingSpinner wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        index = animate(animation, index);
    }
    
    /**
     * Loads in every frame for every animation
     * 
     * @param path - The file path for the unit
     */
    protected void loadAnimationFrames(String path)
    {
        for(int i = 0; i < new File(path).listFiles().length-1; i++)
        {
            animation.add(new GreenfootImage(path + i + ".png"));
        }
    }
    
    /**
     * Simple Animations
     * 
     * @param animation - Sprites used for the animation
     * @param index - The current frame
     * @return index + 1
     */
    protected int animate(ArrayList<GreenfootImage> animation, int index)
    {
        if(animationTimer.millisElapsed() < 20){
            return index;
        }
        setImage(animation.get(index));
        index++;
        if(index > animation.size()-1)
        {
            index = 0;
        }
        animationTimer.mark();
        return index;
    }
}
