import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class SpawnUnitButton extends UI
{
    private SimpleTimer timer = new SimpleTimer();

    private String unit;
    private Wallet wallet;
    private boolean circle, spawned, onCooldown, canUpgrade;
    private Tower spawn;
    private int unitCost, unitStage, unitCooldown, cooldownTimes;

    //Used for upgrade of the buttons to spawn upgraded units
    private int spent;
    final private int firstUpgrade;
    final private int secondUpgrade;

    private BlackBox blackbox;
    private BlackBox hoverBox;
    private ProgressBar cooldownBar;
    private ProgressBar upgradeBar;
    private Text lvlText;

    //Boolean to determine if the cursor code "clicks" button 
    private boolean clicked;

    //boolean to check if the last button has been created
    private boolean last;

    //Constructor for Buttons without "last" boolean = true
    public SpawnUnitButton(String u, int stage, int cost, int cooldown) {
        this(u, stage, cost, cooldown, true, false);
    }
<<<<<<< Updated upstream
    
    public SpawnUnitButton(String u, int stage, int cost, int cooldown, boolean canUpgrade) {
        this(u, stage, cost, cooldown, canUpgrade, false);
    }
    
    public SpawnUnitButton(String u, int stage, int cost, int cooldown, boolean canUpgrade, boolean lastButton) {
        unit = u;
        unitCost = cost;
        unitStage = stage;
        this.canUpgrade = canUpgrade;
        
=======

    public SpawnUnitButton(String u, int stage, int cost, int cooldown, boolean lastButton) {
        unit = u;
        unitCost = cost;
        unitStage = stage;
        spent = 0;

        firstUpgrade = unitCost * 5;
        secondUpgrade = unitCost * 15;
>>>>>>> Stashed changes
        //cooldown is in milliseconds
        unitCooldown = cooldown;
        if (u.substring(0, 1).equals("C")) {
            circle = true;
        } else {
            circle = false;
        }

        String filePath = "/UnitButtons/" + unit + "_" + unitStage + ".png";
        setImage(filePath);
        getImage().scale(90,60);

        cooldownTimes = 0;
        onCooldown = false;
        clicked = false;
        spawned = true;
        last = lastButton;
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
        String[] unitString = new String[]
            {
                "SFodder", "SWarrior", "STank", "SRanger", "SHealer", 
                "CFodder", "CWarrior", "CTank", "CRanger", "CHealer"
            };

        //create every object so greenfoot can cache img animations
        for(int x = 1; x<4; x++){
            unitStage = x;
            for(int i = 0; i<unitString.length; i++){
                unit = unitString[i];
                spawnUnit();
            }
            unitStage++;
        }

        //get all units created and remove them
        ArrayList<Unit> units = (ArrayList<Unit>)getWorld().getObjects(Unit.class);
        for(Unit shapes : units){
            getWorld().removeObject(shapes);
        }
<<<<<<< Updated upstream
        
        
        
=======

>>>>>>> Stashed changes
        //Back to original value 
        unit = tempUnit;
        unitStage = tempStage;
    }

    public void act() {
        if (spawned) {
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

            getWorld().addObject(new Text("$" + unitCost, 18), getX() - getImage().getWidth()/2 + 24, getY() + getImage().getHeight()/2 - 15);

            if (unitStage == 1) {
                upgradeBar = new ProgressBar(firstUpgrade, 1, this, 78, 15, 0, Color.YELLOW, Color.WHITE, false, Color.BLACK, 3);
                getWorld().addObject(upgradeBar, getX(), getY() + 50);
                lvlText = new Text("LVL 1", 13);
                getWorld().addObject(lvlText, getX(), getY() + 52);
            } else if (unitStage == 2) {
                upgradeBar = new ProgressBar(secondUpgrade - firstUpgrade, 1, this, 78, 15, 0, Color.YELLOW, Color.WHITE, false, Color.BLACK, 3);
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

            spawned = false;
        }
        if (onCooldown && cooldownTimes >= 50) {
            offCooldown();
        }
        if (onCooldown && timer.millisElapsed() > (unitCooldown/50)) {
            timer.mark();
            cooldownTimes++;
            cooldownBar.update(unitCooldown - (unitCooldown/50 * cooldownTimes));
        }
        darkenOnHover();
        if(canUpgrade)
        {
            if(unitStage == 1 && spent>=firstUpgrade)
            {
                upgrade();
            }
            if(unitStage == 2 && spent>=secondUpgrade)
            {
                upgrade();
            }
        }
        if (upgradeBar.getWorld() != null && unitStage != 3) {
            upgradeBar.update(spent);
        }
    }

    public void offCooldown() {
        onCooldown = false;

        getWorld().removeObject(blackbox);
        getWorld().removeObject(cooldownBar);
    }

    public void cooldown() {
        onCooldown = true;
        cooldownTimes = 0;

        blackbox = new BlackBox(120, this);
        getWorld().addObject(blackbox, getX(), getY());
        cooldownBar = new ProgressBar(unitCooldown, unitCooldown, this, 78, 15, 0, Color.CYAN, Color.BLACK, false, Color.BLACK, 3);
        getWorld().addObject(cooldownBar, getX(), getY() + 16);

        timer.mark();
    }

    /**
     * Method to spawn a preloaded unit based on its unitIndex 
     */
    public void spawnUnit() {
        //Tanks will offset less since they're taller
        int yOffset = unit.substring(1, unit.length() - 1).equals("Tank") ? 40 : 70; 
        yOffset += Greenfoot.getRandomNumber(30);

        if(!circle){
            if (unit == "SFodder") {
                getWorld().addObject(new SFodder(unitStage), spawn.getX(), spawn.getY() + yOffset);
            } 
            else if (unit == "STank") {
                getWorld().addObject(new STank(unitStage), spawn.getX(), spawn.getY() + yOffset);
            } 
            else if (unit == "SRanger") {
                getWorld().addObject(new SRanger(unitStage), spawn.getX(), spawn.getY() + yOffset);
            } 
            else if (unit == "SHealer") {
                getWorld().addObject(new SHealer(unitStage), spawn.getX(), spawn.getY() + yOffset);
            } 
            else if (unit == "SWarrior") {
                getWorld().addObject(new SWarrior(unitStage), spawn.getX(), spawn.getY() + yOffset);
            }
            return;
        }
        if (unit == "CFodder") {
            getWorld().addObject(new CFodder(unitStage), spawn.getX(), spawn.getY() + yOffset);
        } 
        else if (unit == "CTank") {
            getWorld().addObject(new CTank(unitStage), spawn.getX(), spawn.getY() + yOffset);
        } 
        else if (unit == "CRanger") {
            getWorld().addObject(new CRanger(unitStage), spawn.getX(), spawn.getY() + yOffset);
        } 
        else if (unit == "CHealer") {
            getWorld().addObject(new CHealer(unitStage), spawn.getX(), spawn.getY() + yOffset);
        } 
        else if (unit == "CWarrior") {
            getWorld().addObject(new CWarrior(unitStage), spawn.getX(), spawn.getY() + yOffset);
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
            //if button clicked
            //DEBUG remove Greenfoot.mouseClicked(null)
            if ((Greenfoot.mouseClicked(null) || clicked) && !onCooldown && wallet.getAmount() > unitCost) {
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

    public void upgrade()
    {
        getWorld().addObject(new SpawnUnitButton(unit, unitStage+1, unitCost, unitCooldown), getX(), getY());
        getWorld().removeObject(this);
    }

    public int getSpent() {return spent;}
}
