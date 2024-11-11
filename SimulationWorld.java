import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SimulationWorld extends World
{
    private GreenfootImage bg = new GreenfootImage("Backgrounds/battlecatsbg.png");
    
    private int towerX = 100;
    private int towerY = 270;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public SimulationWorld(String sU1, String sU2, String sU3, String sU4, String sU5, String cU1, String cU2, String cU3, String cU4, String cU5, int[] sTowerVariables, int[] cTowerVariables)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 700, 1, false); 
        setBackground(bg);
        switch(sTowerVariables[1])
        {
            case 0:
                addObject(new Defense(false, 500, 100, sTowerVariables[2], sTowerVariables[0]), towerX, 270);
                break;
            case 1:
                addObject(new Offense(false, 500, 100, sTowerVariables[2], sTowerVariables[0]), towerX, 270);
                break;
            case 2:
                addObject(new Support(false, 500, 100, sTowerVariables[2], sTowerVariables[0]), towerX, 270);
                break;
        }
        switch(cTowerVariables[1])
        {
            case 0:
                addObject(new Defense(true, 500, 100, cTowerVariables[2], cTowerVariables[0]), 1024 - towerX, 270);
                break;
            case 1:
                addObject(new Offense(true, 500, 100, cTowerVariables[2], cTowerVariables[0]), 1024 - towerX, 270);
                break;
            case 2:
                addObject(new Support(true, 500, 100, cTowerVariables[2], cTowerVariables[0]), 1024 - towerX, 270);
                break;
        }
        System.out.println(sTowerVariables[1] + " " + cTowerVariables[1]);
        
        //addObject(new CHealer(3), 900, 500);
        
        addObject(new Wallet(false), 900, 80);
        addObject(new Wallet(true), 100, 615);
        
        //SpawnUnitButton Parameters
        //(Name, unitIndex, level, cost, cooldown, canUpgrade, lastButton, visible), x, y)
        //IMPORTANT: ONE STAGE of ONE UNIT MUST have "true" as the 6th parameter + include 7th param. Other buttons may only have four
        //This is to cache images of unitStage 1, 2, and 3 for all Units (get rid of freezing)
        //eg. CU1 level 1 must have the 6th and 7th param
        addObject(new SpawnUnitButton(sU1, 0, 1, 3000, true), 100, 70);
        addObject(new SpawnUnitButton(sU2, 1, 1, 3000, true), 200, 70);
        addObject(new SpawnUnitButton(sU3, 2, 1, 3000, true), 300, 70);
        addObject(new SpawnUnitButton(sU4, 3, 1, 3000,true), 400, 70);
        addObject(new SpawnUnitButton(sU5, 4, 1, 3000, true), 500, 70);
        
        addObject(new SpawnUnitButton(cU1, 0, 1, 3000, true, true, true), 900, 605);
        addObject(new SpawnUnitButton(cU2, 1, 1, 3000, true), 800, 605);
        addObject(new SpawnUnitButton(cU3, 2, 1, 3000, true), 700, 605);
        addObject(new SpawnUnitButton(cU4, 3, 1, 3000, true), 600, 605);
        addObject(new SpawnUnitButton(cU5, 4, 1, 3000, true), 500, 605);
        
        addObject(new Cursor(true), 1024, 700);
        addObject(new Cursor(false), 0, 0);
       
        //Cursor shows up on top of everything
        setPaintOrder(FullscreenTransition.class, Cursor.class, UI.class, Effect.class,TowerProjectile.class);
        
        addObject(new FullscreenTransition(), 512, 300);
        
        Greenfoot.setSpeed(50);
    }
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public SimulationWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 700, 1, false); 
        setBackground(bg);
        
        addObject(new Offense(false, 500, 100, 0, 1000), 100, 320);
        addObject(new Offense(true, 500, 100, 0, 1000), 900, 320);
        
        //addObject(new CHealer(3), 900, 500);
        
        addObject(new Wallet(false), 900, 50);
        addObject(new Wallet(true), 100, 650);
        
        //SpawnUnitButton Parameters
        //(Name, unitIndex, level, cost, cooldown, canUpgrade, lastButton, visible), x, y)
        //IMPORTANT: ONE STAGE of ONE UNIT MUST have "true" as the 6th parameter + include 7th param. Other buttons may only have four
        //This is to cache images of unitStage 1, 2, and 3 for all Units (get rid of freezing)
        //eg. CU1 level 1 must have the 6th and 7th param
        addObject(new SpawnUnitButton("SFodder", 0, 1, 3000, true), 100, 55);
        addObject(new SpawnUnitButton("STank", 1, 1, 3000, true), 200, 55);
        addObject(new SpawnUnitButton("SWarrior", 2, 1, 3000, true), 300, 55);
        addObject(new SpawnUnitButton("SRanger", 3, 1, 3000,true), 400, 55);
        addObject(new SpawnUnitButton("SHealer", 4, 1, 3000, true), 500, 55);
        
        addObject(new SpawnUnitButton("CFodder", 0, 1, 3000, true, true, true), 900, 625);
        addObject(new SpawnUnitButton("CTank", 1, 1, 3000, true), 800, 625);
        addObject(new SpawnUnitButton("CWarrior", 2, 1, 3000, true), 700, 625);
        addObject(new SpawnUnitButton("CRanger", 3, 1, 3000, true), 600, 625);
        addObject(new SpawnUnitButton("CHealer", 4, 1, 3000, true), 500, 625);
        
        addObject(new Cursor(true), 0, 0);
        addObject(new Cursor(false), 0, 0);
       
        //Cursor shows up on top of everything
        setPaintOrder(FullscreenTransition.class, Cursor.class, UI.class, Effect.class,TowerProjectile.class);
        
        addObject(new FullscreenTransition(), 512, 300);
        
        Greenfoot.setSpeed(50);
    }
}
