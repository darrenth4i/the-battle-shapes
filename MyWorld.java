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
        super(1024, 700, 1, false); 
        setBackground(bg);
        
        addObject(new Cursor(), 0, 0);
        
        addObject(new Offense(false, 500, 100), 100, 320);
        addObject(new Offense(true, 500, 100), 900, 320);
        
        //addObject(new CHealer(3), 900, 500);
        
        addObject(new Wallet(false), 900, 50);
        addObject(new Wallet(true), 100, 650);
        
        //SpawnUnitButton Parameters
        //(Name, level, cost, cooldown), x, y)
        //IMPORTANT: FIRST STAGE of LAST UNIT TYPE MUST have "true" as the fifth parameter. Other buttons may only have four
        //This is to cache images of unitStage 1, 2, and 3 for all Units (get rid of freezing)
        //eg. CHealer level 1 must have the fifth parameter... Likely will not have to change since we plan only 5 units per team?
        addObject(new SpawnUnitButton("SFodder", 1, 100, 3000, true), 100, 55);
        addObject(new SpawnUnitButton("SFodder", 2, 100, 3000, true), 100, 125);
        addObject(new SpawnUnitButton("SFodder", 3, 100, 3000, true), 100, 195);
        addObject(new SpawnUnitButton("STank", 1, 100, 3000, true), 200, 55);
        addObject(new SpawnUnitButton("SWarrior", 1, 100, 3000, true), 300, 55);
        addObject(new SpawnUnitButton("SWarrior", 2, 100, 3000, true), 300, 125);
        addObject(new SpawnUnitButton("SRanger", 1, 100, 3000,true), 400, 55);
        addObject(new SpawnUnitButton("SHealer", 1, 100, 3000, true), 500, 55);
        
        addObject(new SpawnUnitButton("CFodder", 1, 100, 3000, true), 900, 625);
        addObject(new SpawnUnitButton("CTank", 1, 100, 3000, true), 800, 625);
        addObject(new SpawnUnitButton("CWarrior", 1, 100, 3000, true), 700, 625);
        addObject(new SpawnUnitButton("CRanger", 1, 100, 3000, true), 500, 625);
        addObject(new SpawnUnitButton("CHealer", 1, 100, 3000, true), 600, 625);

        //preload assets
        addObject(new SpawnUnitButton("CFodder", 2, 100, 3000, false), 900, -500);
        addObject(new SpawnUnitButton("CFodder", 3, 100, 3000, false), 900, -500);
        addObject(new SpawnUnitButton("CTank", 2, 100, 3000, false), 800, -500);
        addObject(new SpawnUnitButton("CTank", 3, 100, 3000, false), 800, -500);
        addObject(new SpawnUnitButton("CWarrior", 2, 100, 3000, false), 700, -500);
        addObject(new SpawnUnitButton("CWarrior", 3, 100, 3000, false), 700, -500);
        addObject(new SpawnUnitButton("CRanger", 2, 100, 3000, false), 500, -500);
        addObject(new SpawnUnitButton("CRanger", 3, 100, 3000, false), 500, -500);
        addObject(new SpawnUnitButton("CHealer", 2, 100, 3000, true, true, false), 600, -500);
        addObject(new SpawnUnitButton("CHealer", 3, 100, 3000, false), 600, -500);
        
        //Cursor shows up on top of everything
        setPaintOrder(FullscreenTransition.class, Cursor.class, UI.class, Effect.class,TowerProjectile.class);
        
        addObject(new FullscreenTransition(), 512, 300);
        
        Greenfoot.setSpeed(50);
    }
}
