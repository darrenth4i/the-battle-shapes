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
        
        addObject(new Offense(false, 500, 100), 100, 425);
        addObject(new Offense(true, 500, 100), 924, 425);
        
        addObject(new SpawnUnitButton("CFodder", 1, 0, 3000), 924, 80);
        addObject(new SpawnUnitButton("CFodder", 2, 0, 3000), 924, 150);
        addObject(new SpawnUnitButton("CFodder", 3, 0, 3000), 924, 220);
        addObject(new SpawnUnitButton("CWarrior", 1, 0, 3000), 824, 80);
        addObject(new SpawnUnitButton("CWarrior", 2, 0, 3000), 824, 150);
        addObject(new SpawnUnitButton("CWarrior", 3, 0, 3000), 824, 220);
        
        addObject(new SpawnUnitButton("SFodder", 1, 0, 3000), 100, 80);
        addObject(new SpawnUnitButton("SWarrior", 1, 0, 3000), 200, 80);
    }
}
