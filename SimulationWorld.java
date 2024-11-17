import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Collections;
import java.util.ArrayList;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SimulationWorld extends World
{
    private GreenfootImage bg = new GreenfootImage("Backgrounds/bgui.png");
    private SongSelection song = new SongSelection("LiterallyNothing.png");
    private FullscreenTransition loadingScreen = new FullscreenTransition();
    private ArrayList<GreenfootSound> soundTrack;
    private int musicIndex;
    private int towerX = 100;
    private int towerY = 270;
    private int acts;
    private boolean isActed;
    
    private double exactX;
    private double exactY;
    private double preciseRotation;
    private boolean staticRotation = false;
    private double cosRotation;
    private double sinRotation;
    
    //End Simulation Variables
    protected ToSimOverWorld exitButton;
    
    /**
     * Take user set values and pass it to SimulationWorld
     * 
     */
    public SimulationWorld(String sU1, String sU2, String sU3, String sU4, String sU5, String cU1, String cU2, String cU3, String cU4, String cU5, int[] sTowerVariables, int[] cTowerVariables, boolean cIsSmart, boolean sIsSmart)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 700, 1, false); 
        setBackground(bg);
        switch(sTowerVariables[1])
        {
            case 0:
                addObject(new Defense(false, sTowerVariables[2], sTowerVariables[0]), towerX, 290);
                break;
            case 1:
                addObject(new Offense(false, sTowerVariables[2], sTowerVariables[0]), towerX, 290);
                break;
            case 2:
                addObject(new Support(false, sTowerVariables[2], sTowerVariables[0]), towerX, 290);
                break;
        }
        switch(cTowerVariables[1])
        {
            case 0:
                addObject(new Defense(true, cTowerVariables[2], cTowerVariables[0]), 1024 - towerX, 290);
                break;
            case 1:
                addObject(new Offense(true, cTowerVariables[2], cTowerVariables[0]), 1024 - towerX, 290);
                break;
            case 2:
                addObject(new Support(true, cTowerVariables[2], cTowerVariables[0]), 1024 - towerX, 290);
                break;
        }
        
        addObject(new Wallet(false), 900, 75);
        addObject(new Wallet(true), 130, 625);
        
        //SpawnUnitButton Parameters
        //String u, int uIndex, int stage, int cooldown, boolean canUpgrade, boolean lastButton, boolean visible
        //(Name, unitIndex, level, cost, cooldown, canUpgrade, lastButton), x, y)
        //IMPORTANT: ONE STAGE of ONE UNIT MUST have "true" as the 6th parameter + include 7th param. Other buttons may only have four
        //This is to cache images of unitStage 1, 2, and 3 for all Units (get rid of freezing)
        //eg. CU1 level 1 must have the 6th and 7th param
        addObject(new SpawnUnitButton(sU1, 0, 1, 3000, true), 100, 59);
        addObject(new SpawnUnitButton(sU2, 1, 1, 3000, true), 200, 59);
        addObject(new SpawnUnitButton(sU3, 2, 1, 3000, true), 300, 59);
        addObject(new SpawnUnitButton(sU4, 3, 1, 3000,true), 400, 59);
        addObject(new SpawnUnitButton(sU5, 4, 1, 3000, true), 500, 59);
        
        addObject(new SpawnUnitButton(cU1, 0, 1, 3000, true, true), 924, 612);
        addObject(new SpawnUnitButton(cU2, 1, 1, 3000, true), 824, 612);
        addObject(new SpawnUnitButton(cU3, 2, 1, 3000, true), 724, 612);
        addObject(new SpawnUnitButton(cU4, 3, 1, 3000, true), 624, 612);
        addObject(new SpawnUnitButton(cU5, 4, 1, 3000, false), 524, 612);
        
        addObject(new UpgradeButton("wallet", true), 330, 596);
        addObject(new UpgradeButton("tower", true), 330, 655);
        addObject(new UpgradeButton("wallet", false), 690, 43);
        addObject(new UpgradeButton("tower", false), 690, 102);
        
        //Parameters Cursor(circle, random)
        addObject(new Cursor(true, !cIsSmart), 1024, 700);
        addObject(new Cursor(false, !sIsSmart), 0, 0);
        
        addObject(new Notification(false, 198, "Take these Meteors!"), -200, 167);
        addObject(new Notification(true, 826, "Explode!!!"), 1300, 535);
       
        //make sure nothing overlaps in correctly
        setPaintOrder(FullscreenTransition.class, MenuButtons.class, Cursor.class, UI.class, Effect.class,TowerProjectile.class);
        
        addObject(loadingScreen, 512, 300);
        
        Greenfoot.setSpeed(50);
        
        acts = 0;
        isActed = false;
    }

    /**
     * Fast creation of SimulationWorld with preset values
     * for debugging purposes
     * 
     */
    public SimulationWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        this("SFodder", "SWarrior", "STank", "SRanger", "SHealer", "CFodder", "CWarrior", "CTank", "CRanger", "CDragon", new int[]{1000, 0, 0}, new int[]{1000, 0, 0}, true, true);
    }
    
    public void act()
    {
        zSort((ArrayList<Unit>)(getObjects(Unit.class)), this);
        acts++;
        if(loadingScreen.getIsDone() == true && isActed == false)
        {
            instantiateMusic();
            if(musicIndex != -1)
            {
                soundTrack.get(musicIndex).playLoop();
            }
            isActed = true;
        }
    }
    
    /**
     * A z-sort method which will sort Actors so that Actors that are
     * displayed "higher" on the screen (lower y values) will show up underneath
     * Actors that are drawn "lower" on the screen (higher y values), creating a
     * better perspective. 
     */
    public static void zSort (ArrayList<Unit> actorsToSort, World world){
        ArrayList<ActorContent> acList = new ArrayList<ActorContent>();
        // Create a list of ActorContent objects and populate it with all Actors sent to be sorted
        for (Unit a : actorsToSort){
            acList.add (new ActorContent (a, a.getAttacking() || a.getKnockedback() ? a.getX() : a.getNormalX(), a.getAttacking() || a.getKnockedback() ? a.getY() : a.getNormalY(), a.getFeet(), a.getSpeed(), a.getExactX()));
        }    
        // Sort the Actor, using the ActorContent comparitor (compares by y coordinate)
        Collections.sort(acList);
        // Replace the Actors from the ActorContent list into the World, inserting them one at a time
        // in the desired paint order (in this case lowest y value first, so objects further down the 
        // screen will appear in "front" of the ones above them).
        for (ActorContent a : acList){
            Unit actor  = a.getUnit();
            world.removeObject(actor);
            world.addObject(actor, a.getX(), a.getY());
        }
    }
    
    public void instantiateMusic()
    {
        musicIndex = song.getCurrentIndex();
        soundTrack = song.getPlaylist();
    }
    
    public void stopped()
    {
        if(musicIndex != -1)
        {
            soundTrack.get(musicIndex).stop();
        }
    }
    /**
     * Method for ending the simulation
     */
    public void simulationOver(boolean circle)
    {
        killAllUnitsOnTeam(Unit.class, !circle);
        removeAllClassObjects(Cursor.class);
        exitButton = new ToSimOverWorld(12,circle);
        addObject(exitButton, 512, 3000);
    }
    
    public void removeUI()
    {
        setBackground("Backgrounds/battlecatsbg.png");
        removeAllClassObjects(SpawnUnitButton.class);
        removeAllClassObjects(UpgradeButton.class);
        removeAllClassObjects(Text.class);
        removeAllClassObjects(PlayerUI.class);
    }
    
    public void killAllUnitsOnTeam(Class<Unit> team, boolean isCircle)
    {
        ArrayList<Unit> unit = (ArrayList)getObjects(team);
        for (Unit u : unit) {
            if(isCircle && u instanceof Circle)
            {
                u.createGhost();
            }
            else
            if(!isCircle && u instanceof Square)
            {
                u.createGhost();
            }
        }
    }
    
    public void removeAllClassObjects(Class<?> theClass)
    {
        ArrayList<?> obj = (ArrayList)getObjects(theClass);
        for (Object thing : obj) {
            removeObject((Actor) thing);
        }
    }
}

/**
 * Container to hold and Actor and an LOCAL position (so the data isn't lost when the Actor is temporarily
 * removed from the World).
 */
class ActorContent implements Comparable <ActorContent> 
{
    private Unit unit;
    private int xx, yy, feet;
    private double speed;
    private double exactX;
    public ActorContent(Unit unit, int xx, int yy, int feet, double speed, double exactX){
        this.unit = unit;
        this.xx = xx;
        this.yy = yy;
        this.feet = feet;
        this.speed = speed;
        this.exactX = exactX;
    }

    public void setLocation (int x, int y){
        xx = x;
        yy = y;
    }

    public int getX() {
        return xx;
    }
    
    public double getExactX() {
        return (xx+speed + (Math.signum(xx+speed) * 0.5));
    }

    public int getY() {
        return yy;
    }
    
    public int getFeet() {
        return feet;
    }
    
    public double getSpeed() {
        return speed;
    }

    public Unit getUnit(){
        return unit;
    }

    public String toString () {
        return "Unit: " + unit + " at " + xx + ", " + yy;
    }

    public int compareTo (ActorContent a){
        return (int)this.getFeet() - (int)a.getFeet();
    }
}
    
