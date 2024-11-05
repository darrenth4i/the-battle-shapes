import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class SpawnUnitButton extends UI
{
    private SimpleTimer timer = new SimpleTimer();

    private String unit;
    private int unitIndex;
    private boolean circle, spawned, onCooldown;
    private Tower spawn;
    private int unitCost, unitStage, unitcooldown, cooldownTimes;

    private BlackBox blackbox;
    private BlackBox hoverBox;
    private CooldownBar cooldownBar;
    
    private Unit[] unitArray;

    //Boolean to determine if the cursor code "clicks" button 
    private boolean clicked;

    public SpawnUnitButton(String u, int uIndex, int stage, int cost, int cooldown) {
        unit = u;
        unitIndex = uIndex;
        unitCost = cost;
        unitStage = stage;
        //cooldown is in milliseconds
        unitcooldown = cooldown;
        if (u.substring(0, 1).equals("C")) {
            circle = true;
        } else {
            circle = false;
        }

        String filePath = "/UnitButtons/" + unit + "_" + unitStage + ".png";
        setImage(filePath);
        getImage().scale(90,60);
        
        //preload objects so they dont lag in the middle of a game
        unitArray = new Unit[]
        {
            new SFodder(unitStage), new SWarrior(unitStage), new STank(unitStage), new SRanger(unitStage), new SHealer(unitStage), 
            new CFodder(unitStage), new CWarrior(unitStage), new CTank(unitStage), new CRanger(unitStage), new CHealer(unitStage)
        };
        
        cooldownTimes = 0;
        onCooldown = false;
        clicked = false;
        spawned = true;
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

            getWorld().addObject(new Text("$" + unitCost, 18), getX() - getImage().getWidth()/2 + 24, getY() + getImage().getHeight()/2 - 15);
            
            spawned = false;
        }
        if (onCooldown && cooldownTimes >= 50) {
            offCooldown();
        }
        if (onCooldown && timer.millisElapsed() > (unitcooldown/50)) {
            timer.mark();
            cooldownTimes++;
            cooldownBar.update(unitcooldown - (unitcooldown/50 * cooldownTimes));
        }
        darkenOnHover();
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
        cooldownBar = new CooldownBar(unitcooldown, unitcooldown, this, 78, 15, 0, Color.CYAN, Color.BLACK, false, Color.BLACK, 3);
        getWorld().addObject(cooldownBar, getX(), getY() + 16);

        timer.mark();
    }
    
    /**
     * Method to spawn a preloaded unit based on its unitIndex 
     */
    public void spawnUnit() {
        //Tanks will offset less since they're taller
        int yOffset = unit.substring(1, unit.length() - 1).equals("Tank") ? 40 : 70; 
        getWorld().addObject(unitArray[unitIndex], spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
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
            if ((Greenfoot.mouseClicked(null) || clicked) && !onCooldown) {
                clicked = false;
                spawnUnit();
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
}
