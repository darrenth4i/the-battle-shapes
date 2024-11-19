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
    private int actTimer;
    public SimOverWorld(boolean circleWinner)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 700, 1, false); 
        isCircle = circleWinner;
        if(circleWinner)
        {   
            setBackground("Backgrounds/CircleVictory.png");
            cirWin.playLoop();
        }
        else
        {
            setBackground("Backgrounds/SquareVictory.png");
            squWin.playLoop();
        }
        addObject(new ReturnButton(13),512,650);
        addObject(new FullscreenTransition(), 512, 300);
    }
    public void act()
    {
        if(isCircle)
        {
            if(Greenfoot.getRandomNumber(30) > 0 && (actTimer > 10 || actTimer == 0))
            {
                actTimer = 0;
                setBackground("Backgrounds/CircleVictory.png");
            }
            else
            {
                actTimer++;
                setBackground("Backgrounds/CircleVictoryAlt.png");
            }
        }
        else
        {
            if(actTimer > 50)
            {
                setBackground("Backgrounds/SquareVictory.png");
            }
            else
            {
                setBackground("Backgrounds/SquareVictoryAlt.png");
            }
            actTimer++;
            if(actTimer > 100)
            {
                actTimer = 0;
            }
        }
    }
    
    public void stopped()
    {
        if(isCircle)
        {   
            cirWin.stop();
        }
        else
        {
            squWin.stop();
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
