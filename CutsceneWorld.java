import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import java.util.ArrayList;
/**
 * Write a description of class CutsceneWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CutsceneWorld extends World
{
    private Text text = new Text("Loading", 50);
    private LoadingSpinner loadingSpinner = new LoadingSpinner();
    private Text percentage = new Text("0/241", 50);
    
    
    private ArrayList<GreenfootImage> animation = new ArrayList<GreenfootImage>();
    private SimpleTimer animationTimer = new SimpleTimer();
    private int index = 0;
    
    private FullscreenTransition transition = new FullscreenTransition(true);
    
    //Currently loaded frame
    private int frameNum = 0;
    /**
     * Constructor for objects of class CutsceneWorld.
     * 
     */
    public CutsceneWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 700, 1, false); 
        prepare();
        addObject(new FullscreenTransition(false), 512, 300);
    }
    
    public void act()
    {
        if(frameNum == 240)
        {
            addObject(transition, 512, 1200);
        }
        if(frameNum < 241)
        {
            loadImage();
        }
        else if(!transition.getisExiting()&&transition.getWorld() != null)
        {
            setBackground(animation.get(0));
            removeObject(text);
            removeObject(loadingSpinner);
            removeObject(percentage);
        }
        else if(transition.getWorld() == null)
        {
            index = animate(animation, index);
        }
    }
    
    private void loadImage()
    {
        animation.add(new GreenfootImage("images/Cutscene/" + frameNum + ".png"));
        frameNum++;
        percentage.updateText(frameNum + "/241", 50);
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(text,512,300);
        addObject(loadingSpinner,512,400);
        addObject(percentage,512,500);
    }
    protected int animate(ArrayList<GreenfootImage> animation, int index)
    {
        if(animationTimer.millisElapsed() < 40){
            return index;
        }
        switch(index)
        {
            case 0:
            case 13:
            case 29:
            case 102:
            case 103:
            case 116:
            case 145:
            case 197:
                if(animationTimer.millisElapsed() < 1200)
                {
                    return index;
                }
                break;
            case 240:
                addObject(new FullscreenTransition(new SelectionWorld()), 512, 1200);
                break;
        }
        setBackground(animation.get(index));
        index++;
        if(index > animation.size()-1)
        {
            index = 240;
        }
        animationTimer.mark();
        return index;
    }
}
