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
        
        addObject(new Cursor(), 0, 0);
        
        addObject(new Offense(false, 500, 100), 100, 425);
        addObject(new Offense(true, 500, 100), 924, 425);
        
        addObject(new CTank(2), 900, 500);
        
        addObject(new SpawnUnitButton("SFodder", 1, 0, 3000), 100, 80);
        addObject(new SpawnUnitButton("SWarrior", 1, 0, 3000), 200, 80);
        addObject(new SpawnUnitButton("STank", 1, 0, 3000), 300, 80);
        
        
        addObject(new SpawnUnitButton("CFodder", 1, 0, 3000), 800, 80);
        addObject(new SpawnUnitButton("CFodder", 2, 0, 3000), 800, 180);
        addObject(new SpawnUnitButton("CFodder", 3, 0, 3000), 800, 280);
        addObject(new SpawnUnitButton("CWarrior", 1, 0, 3000), 900, 80);
        addObject(new SpawnUnitButton("CWarrior", 2, 0, 3000), 900, 180);
        addObject(new SpawnUnitButton("CWarrior", 3, 0, 3000), 900, 280);
        
        //Cursor shows up on top of everything
        setPaintOrder(Cursor.class);
    }
}
