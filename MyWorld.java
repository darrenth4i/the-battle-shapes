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
        
        //addObject(new CHealer(3), 900, 500);
        
        addObject(new Wallet(false), 80, 560);
        addObject(new Wallet(true), 944, 560);
        
        //SpawnUnitButton Parameters
        //(Name, unitIndex, level, cost, cooldown), x, y)
        //unitIndex help:
        //0 = Fodder, 1 = Warrior, 2 = Tank, 3 = Ranger, 4 = Healer
        addObject(new SpawnUnitButton("SFodder", 0, 1, 100, 3000), 100, 80);
        addObject(new SpawnUnitButton("SWarrior", 1, 1, 0, 3000), 300, 80);
        addObject(new SpawnUnitButton("STank", 2, 1, 0, 3000), 200, 80);
        addObject(new SpawnUnitButton("SRanger", 3, 1, 0, 3000), 400, 80);
        addObject(new SpawnUnitButton("SHealer", 4, 1, 0, 3000), 500, 80);
        
        addObject(new SpawnUnitButton("CFodder", 5, 1, 100, 3000), 924, 80);
        addObject(new SpawnUnitButton("CFodder", 5, 2, 0, 3000), 924, 180);
        addObject(new SpawnUnitButton("CFodder", 5, 3, 0, 3000), 924, 280);
        addObject(new SpawnUnitButton("CWarrior", 6, 1, 0, 3000), 724, 80);
        addObject(new SpawnUnitButton("CWarrior", 6, 2, 0, 3000), 724, 180);
        addObject(new SpawnUnitButton("CWarrior", 6, 3, 0, 3000), 724, 280);
        addObject(new SpawnUnitButton("CTank", 7, 1, 0, 3000), 824, 80);
        addObject(new SpawnUnitButton("CTank", 7, 2, 0, 3000), 824, 180);
        addObject(new SpawnUnitButton("CTank", 7, 3, 0, 3000), 824, 280);
        addObject(new SpawnUnitButton("CRanger", 8, 1, 0, 3000), 624, 80);
        addObject(new SpawnUnitButton("CRanger", 8, 2, 0, 3000), 624, 180);
        addObject(new SpawnUnitButton("CRanger", 8, 3, 0, 3000), 624, 280);
        
        //Cursor shows up on top of everything
        setPaintOrder(Cursor.class);
        
        Greenfoot.setSpeed(50);
    }
}
