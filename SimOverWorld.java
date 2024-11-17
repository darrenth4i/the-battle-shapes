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
    public SimOverWorld(boolean circleWinner)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 700, 1, false); 
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
}
