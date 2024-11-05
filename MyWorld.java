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
        super(1000, 700, 1);
        setBackground(bg);
        
        addObject(new Cursor(), 0, 0);
        
        addObject(new Offense(false, 500, 100), 100, 320);
        addObject(new Offense(true, 500, 100), 900, 320);
        
        addObject(new Wallet(false), 900, 50);
        addObject(new Wallet(true), 100, 650);
        
        addObject(new SpawnUnitButton("SFodder", 1, 100, 3000), 100, 55);
        addObject(new SpawnUnitButton("STank", 1, 0, 3000), 200, 55);
        addObject(new SpawnUnitButton("SWarrior", 1, 0, 3000), 300, 55);
        addObject(new SpawnUnitButton("SRanger", 1, 0, 3000), 400, 55);
        addObject(new SpawnUnitButton("SHealer", 1, 0, 3000), 500, 55);
        
        addObject(new SpawnUnitButton("CFodder", 1, 100, 3000), 900, 575);
        addObject(new SpawnUnitButton("CFodder", 2, 0, 3000), 900, 645);
        addObject(new SpawnUnitButton("CFodder", 3, 0, 3000), 900, 505);
        addObject(new SpawnUnitButton("CTank", 1, 0, 3000), 800, 575);
        addObject(new SpawnUnitButton("CTank", 2, 0, 3000), 800, 645);
        addObject(new SpawnUnitButton("CTank", 3, 0, 3000), 800, 505);
        addObject(new SpawnUnitButton("CWarrior", 1, 0, 3000), 700, 575);
        addObject(new SpawnUnitButton("CWarrior", 2, 0, 3000), 700, 645);
        addObject(new SpawnUnitButton("CWarrior", 3, 0, 3000), 700, 505);
        addObject(new SpawnUnitButton("CRanger", 1, 0, 3000), 600, 575);
        addObject(new SpawnUnitButton("CRanger", 2, 0, 3000), 600, 645);
        addObject(new SpawnUnitButton("CRanger", 3, 0, 3000), 600, 505);
        addObject(new SpawnUnitButton("CHealer", 1, 0, 3000), 500, 575);
        addObject(new SpawnUnitButton("CHealer", 2, 0, 3000), 500, 645);
        addObject(new SpawnUnitButton("CHealer", 3, 0, 3000), 500, 505);
        
        //Cursor shows up on top of everything
        setPaintOrder(Cursor.class);
        
        Greenfoot.setSpeed(50);
    }
}
