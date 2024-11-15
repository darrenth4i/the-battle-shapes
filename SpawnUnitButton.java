import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class SpawnUnitButton extends PlayerUI
{
    private SimpleTimer timer = new SimpleTimer();
    
    private String unit;
    private Wallet wallet;
    private boolean circle, spawned, onCooldown, canUpgrade;
    private Tower spawn;
    private int unitCost, unitStage, unitCooldown, cooldownTimes;
    
    private int[] unitCostIndex = {50, 100, 200, 300, 300};
    
    private int[] specialCircleUnitCostIndex = {200, 600, 2000};

    //Used for upgrade of the buttons to spawn upgraded units
    private int spent;
    final private int firstUpgrade;
    final private int secondUpgrade;
    
    //other actors that all are for each spawn unit button
    private BlackBox blackbox;
    private BlackBox hoverBox;
    private ProgressBar cooldownBar;
    private ProgressBar upgradeBar;
    private Text lvlText;
    
    //Boolean to determine if the cursor code "clicks" button 
    private boolean clicked;

    //boolean to check if the last button has been created
    private boolean last;
    //to help with replacing buttons ArrayList in Cursor class
    private int unitIndex;
    //specific cursor to replace arraylist for
    Cursor targetCursor;

    //Constructor for Buttons without "last" boolean = true
    public SpawnUnitButton(String u, int uIndex, int stage, int cooldown) {
        this(u, uIndex, stage, cooldown, true, false);
    }

    public SpawnUnitButton(String u, int uIndex, int stage, int cooldown, boolean canUpgrade) {
        this(u, uIndex, stage, cooldown, canUpgrade, false);
    }

    public SpawnUnitButton(String u, int uIndex, int stage, int cooldown, boolean canUpgrade, boolean lastButton) {
        unit = u;
        unitIndex = uIndex;
        unitStage = stage;
        this.canUpgrade = canUpgrade;
        unitCooldown = cooldown;
        if (u.substring(0, 1).equals("C")) {
            circle = true;
        } else {
            circle = false;
        }
        
        //set costs if the unit is in the loadout
        if(u.contains("Fodder"))
        {
            unitCost = unitCostIndex[0] * stage;
            unitCooldown = 1000;
        }
        else if(u.contains("Tank"))
        {
            unitCost = unitCostIndex[1] * stage;
            unitCooldown = 1000;
        }
        else if(u.contains("Warrior"))
        {
            unitCost = unitCostIndex[2] * stage;
            unitCooldown = 2000;
        }
        else if(u.contains("Ranger"))
        {
            unitCost = unitCostIndex[3] * stage;
            unitCooldown = 4000;
        }
        else if(u.contains("Healer"))
        {
            unitCost = unitCostIndex[4] * stage;
            unitCooldown = 4000;
        }
        else if(u.contains("Dragon"))
        {
            unitCost = specialCircleUnitCostIndex[2];
            unitCooldown = 12000;
        }
        else if(u.contains("Bomb"))
        {
            unitCost = specialCircleUnitCostIndex[1];
            unitCooldown = 8000;
        }
        else if(u.contains("Cyclone"))
        {
            unitCost = specialCircleUnitCostIndex[0];
            unitCooldown = 4000;
        }
        
        //set thresholds for upgrades
        firstUpgrade = unitCost * 5;
        secondUpgrade = unitCost * 5;
        
        //create file path and find image
        String filePath = canUpgrade ? "/UnitButtons/" + unit + "_" + unitStage + ".png" : "/UnitButtons/" + unit + ".png";
        setImage(filePath);
        getImage().scale(90,60);

        cooldownTimes = 0;
        onCooldown = false;
        clicked = false;
        spawned = true;
        last = lastButton;
        
        targetCursor = null;
    }

    /**
     * Method to preload objects into world and then remove them so that
     * Greenfoot can cache images (prevent freezing)
     */
    public void preload(){
        //Temporarily store proper String unit while objects get preloaded
        String tempUnit = unit;
        int tempStage = unitStage;
        //name of all units
        String[] circleUnit = new String[]
        {
            "CFodder", "CWarrior", "CTank", "CRanger", "CHealer", "CDragon"
        };
        String[] squareUnit = new String[]
        {
            "SFodder", "SWarrior", "STank", "SRanger", "SHealer" , "SFodder"
        };

        //create every object so greenfoot can cache img animations
        for(int x = 1; x<4; x++){
            unitStage = x;
            for(int i = 0; i<circleUnit.length; i++){
                circle = true;
                unit = circleUnit[i];
                spawnUnit();
                circle = false;
                unit = squareUnit[i];
                spawnUnit();
            }
            unitStage++;
        }

        //get all units created and remove them
        ArrayList<Unit> units = (ArrayList<Unit>)getWorld().getObjects(Unit.class);
        for(Unit shapes : units){
            getWorld().removeObject(shapes);
        }
        
        //reset temp changed variables
        unit = tempUnit;
        unitStage = tempStage;
        circle = true;
    }

    public void act() {
        if (spawned) {
            //when first spawned, find the corresponding tower and wallet object to your team
            ArrayList<Tower> towers = (ArrayList<Tower>)getWorld().getObjects(Tower.class);
            if (towers.size() != 0) {
                if (towers.get(0).getCircle() == circle) {
                    spawn = towers.get(0);
                } else {
                    spawn = towers.get(1);
                }
            }
            ArrayList<Wallet> wallets = (ArrayList<Wallet>)getWorld().getObjects(Wallet.class);
            if (wallets.size() != 0) {
                if (wallets.get(0).getCircle() == circle) {
                    wallet = wallets.get(0);
                } else {
                    wallet = wallets.get(1);
                }
            }
            
            //add text for unit cost on the button
            getWorld().addObject(new Text("$" + unitCost, 18), getX() - getImage().getWidth()/2 + 24, getY() + getImage().getHeight()/2 - 15);
            
            //add upgrade bar depending on the current unit stage
            if (unitStage == 1) {
                upgradeBar = new ProgressBar(firstUpgrade, 1, this, 78, 15, 0, Color.YELLOW, Color.WHITE, false, Color.BLACK, 3);
                getWorld().addObject(upgradeBar, getX(), getY() + 50);
                lvlText = new Text("LVL 1", 13);
                getWorld().addObject(lvlText, getX(), getY() + 52);
            } else if (unitStage == 2) {
                upgradeBar = new ProgressBar(secondUpgrade, 1, this, 78, 15, 0, Color.YELLOW, Color.WHITE, false, Color.BLACK, 3);
                getWorld().addObject(upgradeBar, getX(), getY() + 50);
                lvlText = new Text("LVL 2", 13);
                getWorld().addObject(lvlText, getX(), getY() + 52);
            } else if (unitStage == 3) {
                upgradeBar = new ProgressBar(1, 1, this, 78, 15, 0, Color.YELLOW, Color.WHITE, false, Color.BLACK, 3);
                getWorld().addObject(upgradeBar, getX(), getY() + 50);
                lvlText = new Text("LVL MAX", 13);
                getWorld().addObject(lvlText, getX(), getY() + 52);
            }
            
            if(last){
                preload();   
            }
            
            //ensure this is only run once.
            spawned = false;
        }
        //if on cooldown and cooldown is over, run offcooldown()
        if (onCooldown && cooldownTimes >= 50) {
            offCooldown();
        }
        //create 50 intervals for the cooldown bar to update to simulate the bar decreasing 
        if (onCooldown && timer.millisElapsed() > (unitCooldown/50)) {
            timer.mark();
            cooldownTimes++;
            cooldownBar.update(unitCooldown - (unitCooldown/50 * cooldownTimes));
        }
        //darken button when cursor is hovering
        darkenOnHover();
        
        if(canUpgrade) {
            //if at stage 1 and you can upgrade, upgrade.
            if(unitStage == 1 && spent >= firstUpgrade)
            {
                upgrade();
            }
            //if at stage 2 and you can upgrade, upgrade.
            if(unitStage == 2 && spent >= secondUpgrade)
            {
                upgrade();
            }
        }
        //update upgrade bar
        if (upgradeBar.getWorld() != null && unitStage != 3) {
            upgradeBar.update(spent);
        }
    }

    public void offCooldown() {
        onCooldown = false;
        //remove cooldownbar and darken effect 
        getWorld().removeObject(blackbox);
        getWorld().removeObject(cooldownBar);
    }

    public void cooldown() {
        onCooldown = true;
        cooldownTimes = 0;
        //add cooldownbar and darken effect 
        blackbox = new BlackBox(120, this);
        getWorld().addObject(blackbox, getX(), getY());
        cooldownBar = new ProgressBar(unitCooldown, unitCooldown, this, 78, 15, 0, Color.CYAN, Color.BLACK, false, Color.BLACK, 3);
        getWorld().addObject(cooldownBar, getX(), getY() + 16);
        //timer for cooldown
        timer.mark();
    }

    /**
     * Method to spawn a preloaded unit based on its unitIndex 
     */
    public void spawnUnit() {
        //Tanks will offset less since they're taller
        int yOffset = unit.substring(1, unit.length() - 1).equals("Tank") ? 40 : 110; 
        yOffset += Greenfoot.getRandomNumber(20);
        int xOffset = 30;
        //spawn respective unit
        if(!circle){
            if (unit == "SFodder") {
                getWorld().addObject(new SFodder(unitStage), spawn.getX() + xOffset, spawn.getY() + yOffset);
            } 
            else if (unit == "STank") {
                getWorld().addObject(new STank(unitStage), spawn.getX() + xOffset, spawn.getY() + yOffset);
            } 
            else if (unit == "SRanger") {
                getWorld().addObject(new SRanger(unitStage), spawn.getX() + xOffset, spawn.getY() + yOffset);
            } 
            else if (unit == "SHealer") {
                getWorld().addObject(new SHealer(unitStage), spawn.getX() + xOffset, spawn.getY() + yOffset);
            } 
            else if (unit == "SWarrior") {
                getWorld().addObject(new SWarrior(unitStage), spawn.getX() + xOffset, spawn.getY() + yOffset);
            }
            return;
        }
        if (unit == "CFodder") {
            getWorld().addObject(new CFodder(unitStage), spawn.getX() - xOffset, spawn.getY() + yOffset);
        } 
        else if (unit == "CTank") {
            getWorld().addObject(new CTank(unitStage), spawn.getX() - xOffset, spawn.getY() + yOffset);
        } 
        else if (unit == "CRanger") {
            getWorld().addObject(new CRanger(unitStage), spawn.getX() - xOffset, spawn.getY() + yOffset);
        } 
        else if (unit == "CHealer") {
            getWorld().addObject(new CHealer(unitStage), spawn.getX() - xOffset, spawn.getY() + yOffset);
        } 
        else if (unit == "CWarrior") {
            getWorld().addObject(new CWarrior(unitStage), spawn.getX() - xOffset, spawn.getY() + yOffset);
        } 
        else if (unit == "CDragon") {
            getWorld().addObject(new CDragon(), spawn.getX() - xOffset, spawn.getY() + yOffset);
        } 
        else if (unit == "CBomb") {
            getWorld().addObject(new CBomb(), spawn.getX() - xOffset, spawn.getY() + yOffset);
        } 
        else if (unit == "CCyclone") {
            getWorld().addObject(new CCyclone(), spawn.getX() - xOffset, spawn.getY() + yOffset);
        } 
    }

    /**
     * Method to darken the button upon cursor hover
     */
    public void darkenOnHover() {
        //If button touches cursor
        if(isTouching(Cursor.class)){
            //if no blackbox exists
            if(!isTouching(BlackBox.class)){
                hoverBox = new BlackBox(20, this);
                getWorld().addObject(hoverBox, getX(), getY());    
            }
            //if button clicked and can afford
            if (clicked && !onCooldown && wallet.getAmount() > unitCost) {
                clicked = false;
                spawnUnit();
                wallet.spend(unitCost);
                spent+=unitCost;
                cooldown();
            }
        }
        //If button doesnt touch cursor and blackbox exists
        if(!isTouching(Cursor.class) && isTouching(BlackBox.class)){
            getWorld().removeObject(hoverBox);
        }
    }
    
    public void upgrade()
    {
        //create a new spawnunitbutton of the next stage of the unit
        SpawnUnitButton upgradedButton = new SpawnUnitButton(unit, unitIndex, unitStage+1, unitCooldown, true);
        
        getWorld().addObject(upgradedButton, getX(), getY());
        
        ArrayList<Cursor> cursors = (ArrayList<Cursor>)getWorld().getObjects(Cursor.class);
        for(Cursor c : cursors){
            if((circle && c.getCircle()) || (!circle && !c.getCircle())){
                targetCursor = c;
            }
        }
        targetCursor.replaceButtonsTeam(unitIndex, upgradedButton);
        //remove this lower stage spawnunitbutton
        getWorld().removeObject(this);
    }
    
    /**
     * Setter method to change clicked 
     */
    public void setClicked(boolean c){
        clicked = c;   
    }
    
    /**
     * Getter method to return onCooldown
     */
    public boolean getOnCooldown(){
        return onCooldown;
    }

    /**
     * Method to return the coordinates of the button
     */
    public Coordinate getCoordinate(){
        return new Coordinate(getX(), getY());
    }
    
    /**
     * Method to return if button is part of circle team
     */
    public boolean getCircle(){
        return circle;
    }
    
    /**
     * Method to return unit name
     */
    public String getUnit(){
        return unit;
    }
    
    /**
     * Method to return amount spent
     */
    public int getSpent() {
        return spent;
    }
    
    /**
     * Returns the percentage progress of how close
     * a button is to being upgraded
     */
    public int getPercentUpgrade(){
        if(unitStage == 1){
            return 100 * spent / firstUpgrade;
        }
        else if(unitStage == 2){
            return 100 * spent / secondUpgrade;
        }
        return -1;
    }
}
