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
    private int secondActTimer;
    public SimOverWorld(boolean circleWinner)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 700, 1, false); 
        isCircle = circleWinner;
        if(circleWinner)
        {   
            setBackground("Backgrounds/CircleVictory.png");
            cirWin.stop();
            cirWin.playLoop();
        }
        else
        {
            setBackground("Backgrounds/SquareVictory.png");
            squWin.stop();
            squWin.playLoop();
        }
        addObject(new ReturnButton(13),512,650);
        addObject(new FullscreenTransition(), 512, 300);
    }
    public void act()
    {
        if(secondActTimer < 3)
        {
            secondActTimer++;
            return;
        }
        else
        {
            secondActTimer = 0;
        }
        if(isCircle)
        {
            if(Greenfoot.getRandomNumber(100) > 0 && (actTimer > 30 || actTimer == 0))
            {
                actTimer = 0;
                setBackground("Backgrounds/CircleVictory.png");
            }
            else
            {
                if(Greenfoot.getRandomNumber(4) > 0)
                {
                    actTimer++;
                    setBackground("Backgrounds/CircleVictoryAlt.png");
                }
                else
                {
                    if(Greenfoot.getRandomNumber(5) > 0)
                    {
                        setBackground("Backgrounds/CircleVictoryAlt2.png");
                    }
                    else
                    {
                        setBackground("Backgrounds/CircleVictoryAlt3.png");
                    }
                }
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
            cirWin.pause();
            setBackground("Backgrounds/CircleVictoryAlt3.png");
        }
        else
        {
            squWin.pause();
        }
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
    
    public void stopCirMusic()
    {
        cirWin.stop();
    }
    
    public void stopSquMusic()
    {
        squWin.stop();
    }
}
