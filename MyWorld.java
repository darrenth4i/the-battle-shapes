import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private GreenfootImage bg = new GreenfootImage("Backgrounds/battlecatsbg.png");
        
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 600, 1); 
        bg.scale(1024,800);
        setBackground(bg);
        
        addObject(new CWarrior(3), 900, 500);
        addObject(new SWarrior(1), 100, 500);
        addObject(new SWarrior(1), 110, 500);
        addObject(new SWarrior(1), 120, 500);
        addObject(new SWarrior(1), 130, 500);
        addObject(new SWarrior(1), 140, 500);
        addObject(new SWarrior(1), 150, 500);
        
    }
}
