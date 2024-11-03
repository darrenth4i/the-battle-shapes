import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class SpawnUnitButton extends UI
{
    private SimpleTimer timer = new SimpleTimer();

    private String unit;
    private boolean circle, spawned, onCooldown;
    private Tower spawn;
    private int unitCost, unitStage, unitcooldown, cooldownTimes;

    private BlackBox blackbox;
    private BlackBox hoverBox;
    private CooldownBar cooldownBar;
    
    //Boolean to determine if the cursor code "clicks" button 
    private boolean clicked;

    public SpawnUnitButton(String u, int stage, int cost, int cooldown) {
        unit = u;
        unitCost = cost;
        unitStage = stage;
        //cooldown is in milliseconds
        unitcooldown = cooldown;
        if (u.equals("CFodder") || u.equals("CTank") || u.equals("CRanger") || u.equals("CHealer") || u.equals("CWarrior")) {
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

            getWorld().addObject(new Text("$" + unitCost, 20), getX() - getImage().getWidth()/2 + 21, getY() + getImage().getHeight()/2 - 13);

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

        blackbox = new BlackBox(120);
        getWorld().addObject(blackbox, getX(), getY());
        cooldownBar = new CooldownBar(unitcooldown, unitcooldown, this, 78, 15, 0, Color.CYAN, Color.BLACK, false, Color.BLACK, 3);
        getWorld().addObject(cooldownBar, getX(), getY() + 16);

        timer.mark();
    }

    public void spawnUnit() {
        if (unit == "CFodder") {getWorld().addObject(new CFodder(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "CTank") {getWorld().addObject(new CTank(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "CRanger") {getWorld().addObject(new CRanger(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "CHealer") {getWorld().addObject(new CHealer(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "CWarrior") {getWorld().addObject(new CWarrior(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "SFodder") {getWorld().addObject(new SFodder(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "STank") {getWorld().addObject(new STank(unitStage), spawn.getX(), spawn.getY() + 40 + Greenfoot.getRandomNumber(30));
        } else if (unit == "SRanger") {getWorld().addObject(new SRanger(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "SHealer") {getWorld().addObject(new SHealer(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "SWarrior") {getWorld().addObject(new SWarrior(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
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
                hoverBox = new BlackBox(20);
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
