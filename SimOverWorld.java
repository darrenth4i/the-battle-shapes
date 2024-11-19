import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SimOverWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SimOverWorld extends World
{
    /**
     * Constructor for objects of class SimOverWorld.
     * 
     */
    private GreenfootSound cirWin = new GreenfootSound("sounds/CircleVictory.mp3");
    private GreenfootSound squWin = new GreenfootSound("sounds/SquareVictory.mp3");
    private boolean isCircle;
    public SimOverWorld(boolean circleWinner)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 700, 1, false); 
        isCircle = circleWinner;
        if(circleWinner)
        {   
            setBackground("Backgrounds/CircleVictory.png");
        }
        else
        {
            setBackground("Backgrounds/SquareVictory.png");
        }
        addObject(new ReturnButton(13),512,650);
        addObject(new FullscreenTransition(), 512, 300);
    }
    public void started()
    {
        if(isCircle)
        {   
            cirWin.playLoop();
        }
        else
        {
            squWin.playLoop();
        }
    }
    
    public void stopped()
    {
        if(isCircle)
        {   
            cirWin.pause();
        }
        else
        {
            squWin.pause();
        }
    }
    
    public void stopCirMusic()
    {
        cirWin.stop();
    }
    
    public void stopSquMusic()
    {
        squWin.stop();
    }
}
