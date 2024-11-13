import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Cursor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cursor extends SuperSmoothMover
{
    //images for idle and held cursor frames
    private GreenfootImage cursorIdle;
    private GreenfootImage cursorHeld;

    //variables to determine if left clicked
    private boolean clicked;
 
    private int speed;
    
    //timer to make cursor animation more noticeable
    private int cursorTimer;
    
    //coordinates for cursor to move to
    private Coordinate currentDestination;
    
    //index to access spawnButtons list 
    //IMPORTANT: for square team, index is (0-4) LEFT to RIGHT
    //           for circle team, index is (0-4) RIGHT to LEFT
    private int destinationIndex;
    
    //variable to determine if cursor is circle team
    private boolean circle;
    //variable to run code once
    private boolean spawned;
    //arraylist for all spawnButtons in world
    private ArrayList<SpawnUnitButton> spawnButtons;
    //shape/team specific arraylist
    private ArrayList<SpawnUnitButton> spawnButtonTeams;
    //string representation of spawnButtonTeams;
    private ArrayList<String> buttonNames;
    
    //arraylist to hold all units alive of one specific team
    private ArrayList<Unit> units;
    
    //DEBUG remove wallets related code if never used later
    private ArrayList<Wallet> wallets;
    private Wallet myWallet;
    
    //list of all UpgradeButton
    private ArrayList<UpgradeButton> upgradeButtons;
    //team specific upgrade button
    private UpgradeButton myWalletUpgradeButton;
    private UpgradeButton myTowerUpgradeButton;
    
    //list of all UpgradeButton
    private ArrayList<Tower> towers;
    //team specific upgrade button
    private Tower myTower;
    private Tower enemyTower;
    
    //Only run winning() method after increasingly larger tower hp differences
    private double[] hpDiff;
    //index to iterate through array
    private int hpDiffIndex;
    
    //determine if it is the start of the game or not;
    private boolean start;
    //determine if cursor stopped on a button it wants to press
    private boolean stopped;
    //cooldown timer to prevent bestMove() from running too often
    private SimpleTimer cooldown = new SimpleTimer();
    
    //determine if boolean is smart or randomized
    private boolean random;

    public Cursor(boolean cir, boolean ran){
        cursorIdle = new GreenfootImage("images/cursor.png");
        //Make held frame smaller, visual indicator of clicked
        cursorHeld = new GreenfootImage("images/cursorHeld.png");  
        
        speed = 3;
        cursorTimer = 0;
        destinationIndex = 0;
        
        clicked = false;
        setImage(cursorIdle);
        
        circle = cir;
        spawned = false;
        
        spawnButtonTeams = new ArrayList<SpawnUnitButton>();
        buttonNames = new ArrayList<String>();
        towers = new ArrayList<Tower>();
        upgradeButtons = new ArrayList<UpgradeButton>();
        
        enableStaticRotation();
        
        hpDiff = new double[]{0.1, 0.2, 0.25, 0.3, 0.35, 0.4};
        hpDiffIndex = 0;
        
        units = new ArrayList<Unit>();
        start = true;
        stopped = false;
        
        random = ran;
    }
    
    /**
     * Act - do whatever the Cursor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //run only once when object is spawned
        if(!spawned){
            //look for spawnButtons
            spawnButtons = (ArrayList<SpawnUnitButton>)getWorld().getObjects(SpawnUnitButton.class);
            wallets = (ArrayList<Wallet>)getWorld().getObjects(Wallet.class);
            upgradeButtons = (ArrayList<UpgradeButton>)getWorld().getObjects(UpgradeButton.class);
            towers = (ArrayList<Tower>)getWorld().getObjects(Tower.class);
            //if all 3 arraylists not spawned yet, try again
            if(spawnButtons == null || wallets == null || upgradeButtons == null || towers == null){
                return;
            }
            
            //only want one team spawnButtons
            for(SpawnUnitButton b : spawnButtons){
                //add to team specific array list if cursor/button is circle/square
                if((circle && b.getCircle()) || (!circle && !b.getCircle())){
                    spawnButtonTeams.add(b);
                }
            }
            //add name of each button for enemy AI easy access 
            for(SpawnUnitButton b : spawnButtonTeams){
                buttonNames.add(b.getUnit());
            }
            
            //DEBUG remove if useless, get wallet for own team
            for(Wallet w : wallets){
                if((circle && w.getCircle()) || (!circle && !w.getCircle())){
                    myWallet = w;
                }
            }
            
            for(UpgradeButton u : upgradeButtons){
                if((circle && u.getCircle()) || (!circle && !u.getCircle())){
                    if(u.getType().equals("wallet")){
                        myWalletUpgradeButton = u;
                    }
                    else{
                        myTowerUpgradeButton = u;
                    }
                }
            }
            
            for(Tower t : towers){
                if((circle && t.getCircle()) || (!circle && !t.getCircle())){
                    myTower = t;
                }
                else{
                    enemyTower = t;
                }
            }
            
            cooldown.mark();

            spawned = true;
        }
        
        //find best move every 0.5 seconds
        if(cooldown.millisElapsed() >= 500 && !stopped){
            destinationIndex = bestMove();  
            cooldown.mark();
        }
        
        if(!stopped){
            //if using smart enemy setting
            if(!random){
                //If my tower hp is greater than enemy tower hp and no currentDestination
                if(currentDestination == null && winning()){
                    //75% chance to upgrade wallet
                    int randomNum = Greenfoot.getRandomNumber(4);
                    if(randomNum > 0 && myWalletUpgradeButton.getLevel() < 2){
                        currentDestination = myWalletUpgradeButton.getCoordinate();
                    }
                    //25% to upgrade tower ability
                    else if(randomNum == 0 && myWalletUpgradeButton.getLevel() < 2){    
                        currentDestination = myTowerUpgradeButton.getCoordinate();
                    }
                }
                // Check if there is another destination for me if I don't have one
                if (currentDestination == null){
                    currentDestination = getNextDestination (destinationIndex);
                }
            }
            //random enemy setting
            else{
                if(currentDestination == null){
                    randomMove();
                }
            }
    
            //move to button coords
            followCursor(currentDestination);
        }
        //check to reset mouse held animation
        click(false);  
        
        //"Click" button if cursor is stopped on it, and isnt on cooldown 
        if(stopped){
            //upgrade button that cursor touches
            UpgradeButton touchingButton = (UpgradeButton)getOneIntersectingObject(UpgradeButton.class);
            
            //purchase/click the corresponding button if affordable and not on cooldown
            if(touchingButton != null && myWallet.getAmount() >= touchingButton.getCost()){
                touchingButton.setClicked(true);
                stopped = false;
            }
            else if(touchingButton == null && !spawnButtonTeams.get(destinationIndex).getOnCooldown()){
                spawnButtonTeams.get(destinationIndex).setClicked(true);
                stopped = false;
            }
                
            click(true);
        }
        
    }
    
    /**
     * Method to get the coordinates to a specific button's destination in spawnButtonTeams
     */
    private Coordinate getNextDestination (int index) {
        return spawnButtonTeams.get(index).getCoordinate();
    }
    
    /**
     * Method to move the cursor image smoothly
     */
    public void followCursor(Coordinate target){
        double distanceToDestination = getDistance (new Coordinate(getX(), getY()), target);
        int distanceX = target.getX() - this.getX();
        int distanceY = target.getY() - this.getY();
        
        //Normalize the direction vector = get direction/angle, but change magnitude to 1 so speed isn't increased/decrease
        //Math.atan2(y, x) returns the angle of the line formed between Cursor and SpawnUnitButton with respect to 
        //the x-axis starting from Q1, and greenfoot also calculates angles starting from Q1 so it works
        //ex.) if angle = 160 deg -> Q2, angle = 280 deg -> Q4 (follows quadrant of cartesian plane)
        double angle = Math.atan2(distanceY, distanceX);
        
        //multiply speed x and y components by angle so it moves smoothly, cos = x component, sin = y component
        double adjustedSpeedX = speed * Math.cos(angle);
        double adjustedSpeedY = speed * Math.sin(angle);
        
        if (distanceToDestination < speed){
            setLocation (currentDestination.getX(), currentDestination.getY());
            currentDestination = null;
            stopped = true;
        }
        //only move if unit has been bought
        else if(!stopped){
            setLocation(getX() + adjustedSpeedX, getY() + adjustedSpeedY);
        }  
    }
    
    public void randomMove(){
        int rng = Greenfoot.getRandomNumber(30);
        if(rng == 0){
            currentDestination = myWalletUpgradeButton.getCoordinate();
        }
        else if(rng == 1){
            currentDestination = myTowerUpgradeButton.getCoordinate();
        }
        else{
            currentDestination = getNextDestination(Greenfoot.getRandomNumber(spawnButtonTeams.size() - 1));
        }
    }
    
    /**
     * Method that returns an int for destinationsIndex
     * based on specific factors happening in the simulation
     * 
     * Enemy AI
     */
    public int bestMove(){
        //if no team units exists yet
        if(checkUnits(true).equals("none")){
            //if nothing worth upgrading yet
            if(worthUpgradingUnit().equals("none")){
                //best move fodder if tank doesnt exist 
                if((findIndex("Tank") == -1)){
                    return findIndex("Fodder");  
                }
                //default best choice if no units 
                return findIndex("Tank");
            }
            else{
                //else go for the unit close to upgrade
                return findIndex(worthUpgradingUnit());
            }
        }
        //if units exist
        else{
            //if we mostly have warriors
            if(checkUnits(true).equals("Warrior")){
                //75% healer, 25% ranger
                if(Greenfoot.getRandomNumber(4) > 0){
                    return findIndex("Healer");
                }
                else{
                    return findIndex("Ranger");
                }
            }
            else{
                //if mostly enemy healers/rangers
                if(checkUnits(false).equals("Healer") || checkUnits(false).equals("Ranger")){
                    //go ranger 66% of time, else go warrior if unavailable
                    if(findIndex("Ranger") != -1 && Greenfoot.getRandomNumber(3) > 0){
                        return findIndex("Ranger");
                    }
                    else{
                        return findIndex("Warrior");
                    }
                }
                //if enemies have mostly warrior/tanks
                else if(checkUnits(false).equals("Warrior") || checkUnits(false).equals("Tank")){
                    //66% chance to go ranger
                    if(Greenfoot.getRandomNumber(3) > 1){
                        if(findIndex("Ranger") != -1){
                            return findIndex("Ranger");
                        }
                    }
                    else if(Greenfoot.getRandomNumber(3) > 1){
                        if(findIndex("Healer") != -1){
                            return findIndex("Healer");
                        }
                    }
                    else if(Greenfoot.getRandomNumber(3) > 0){
                        if(findIndex("Fodder") != -1){
                            return findIndex("Fodder");
                        }
                    }
                    //small chance of tank
                    return findIndex("Tank");
                }
                //else go warrior or tank if unavailable
                else{
                    if(findIndex("Warrior") != -1){
                        return findIndex("Warrior");
                    }
                    else{
                        return findIndex("Tank");
                    }
                }
            }
        }
    }
    
    /**
     * Find the index where part of a 
     * unit's name is located
     * 
     * part of Enemy AI, but used elsewhere too
     */
    public int findIndex(String u){
        String unit = "";
        int index = 0;
        //add proper prefix to unit name
        if(circle){
            unit = "C" + u;
        }
        else{
            unit = "S" + u;
        }
        //look for the index the unit is located in
        for(String s : buttonNames){
            if(s.contains(unit)){
                return index;
            }
            index++;
        }
        return -1;
    }
    
    /**
     * Method that returns true if current tower has significantly more Hp
     * than enemy tower. Else false.
     */
    public boolean winning(){
        //prevent index out of bounds error
        if(hpDiffIndex >= hpDiff.length){
            return false;
        }
        if((myTower.getHealthPercentage() - enemyTower.getHealthPercentage()) >= hpDiff[hpDiffIndex]){
            hpDiffIndex++;
            return true;
        }
        return false;
    }
    
    /**
     * Method that returns the name of the most common unit
     * on either side based on specified team
     * 
     * @param self - Search for own team? true/false
     * 
     * part of Enemy AI
     */
    public String checkUnits(boolean self){
        String mostCommonUnit = "";
        //count the number of each subclass
        int[] numUnits = new int[]{0, 0, 0, 0, 0};
        //names of each subclass
        String[] names = new String[]{"Fodder", "Warrior", "Tank", "Ranger", "Healer"};
        
        //check circle if self is circle or check enemy if self is square
        if((self && circle) || (!self && !circle)){
            //Convert to wildcard type arraylist before to Unit
            units = (ArrayList<Unit>)((ArrayList<?>)getWorld().getObjects(Circle.class));
        }
        //check square if self is square or check enemy if self is circle
        else{
            units = (ArrayList<Unit>)((ArrayList<?>)getWorld().getObjects(Square.class));
        }
        
        if(units.size() == 0){
            return "none";
        }
        
        //check which subclass each unit alive is part of 
        for(Unit u : units){
            if(u.getName().contains(names[0])){
                numUnits[0] += 1;
            }
            else if(u.getName().contains(names[1])){
                numUnits[1] += 1;
            }
            else if(u.getName().contains(names[2])){
                numUnits[2] += 1;
            }
            else if(u.getName().contains(names[3])){
                numUnits[3] += 1;
            }
            else if(u.getName().contains(names[4])){
                numUnits[4] += 1;
            }
        }
        int most = numUnits[0];
        int mostIndex = 0;
        
        //find most common subclass
        for(int i = 0; i<numUnits.length; i++){
            if(numUnits[i] > most){
                most = numUnits[i];
                mostIndex = i;
            }
        }
        
        //if a unit exists, start of game is over
        start = false;
        return names[mostIndex];
    }
    
    /**
     * Method that returns the Unit's name to bestMove() to
     * determine if the cursor should buy a specific unit close
     * to being upgraded
     * 
     * part of Enemy AI
     */
    public String worthUpgradingUnit(){
        //check three times for the closest button to being upgraded
        //return only if it is a specified percentage or closer 
        for(SpawnUnitButton b : spawnButtonTeams){
            if(b.getPercentUpgrade() >= 90){
                return b.getUnit().substring(1, b.getUnit().length() - 1);
            }
        }
        for(SpawnUnitButton b : spawnButtonTeams){
            if(b.getPercentUpgrade() >= 80){
                return b.getUnit().substring(1, b.getUnit().length() - 1);
            }
        }
        for(SpawnUnitButton b : spawnButtonTeams){
            if(b.getPercentUpgrade() >= 75){
                return b.getUnit().substring(1, b.getUnit().length() - 1);
            }
        }
        //else return none
        return "none";
    }
    
    /**
     * Method to return the closest distance between two Actors, a and b
     */
    public double getDistance(Actor a, Actor b){
        return Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
    }
    
     /**
     * Static method that gets the distance between the x,y coordinates of two Actors
     * using Pythagorean Theorum.
     *
     * @param a     First Actor
     * @param b     Second Actor
     * @return distance The distance from the center of a to the center of b.
     */
    public static double getDistance (Coordinate a, Coordinate b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return distance;
    }
    
    /**
     * Method to make image smaller to show it has been clicked
     */
    public void click(boolean c){
        //if currently held, count up timer
        if(this.getImage() == cursorHeld){
            cursorTimer++;
        }
        if(c){
            setImage(cursorHeld);
        }
        //Only set back to idle if 0.3s passed so its more visible
        else if(cursorTimer >= 18){
            setImage(cursorIdle);
            cursorTimer = 0;
        }
    }

    //Return if cursor is part of circle team
    public boolean getCircle(){
        return circle;
    }
    
    /**
     * Method to replace a SpawnUnitButton in spawnButtonTeams 
     * when it gets upgraded so the list has the right button
     * at all times
     */
    public void replaceButtonsTeam(int index, SpawnUnitButton b) {
        currentDestination = null;
        spawnButtonTeams.set(index, b);
    }
}
