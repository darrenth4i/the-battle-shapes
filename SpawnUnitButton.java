import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class SpawnUnitButton extends UI
{
    private SimpleTimer timer = new SimpleTimer();

    private String unit;
    private boolean circle, spawned, onCooldown;
    private Tower spawn;
    private int unitCost, unitStage, unitCooldown, cooldownTimes;

    private BlackBox blackbox;
    private CooldownBar cooldownbar;

    public SpawnUnitButton(String u, int stage, int cost, int cooldown) {
        unit = u;
        unitCost = cost;
        unitStage = stage;
        //cooldown is in milliseconds
        unitCooldown = cooldown;
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
        if (Greenfoot.mouseClicked(this)) {
            Cooldown();
            SpawnUnit();
        }
        if (onCooldown && cooldownTimes >= 50) {
            OffCooldown();
        }
        if (onCooldown && timer.millisElapsed() > (unitCooldown/50)) {
            timer.mark();
            cooldownTimes++;
            cooldownbar.update(unitCooldown - (unitCooldown/50 * cooldownTimes));
        }
    }

    public void OffCooldown() {
        onCooldown = false;

        getWorld().removeObject(blackbox);
        getWorld().removeObject(cooldownbar);
    }

    public void Cooldown() {
        onCooldown = true;
        cooldownTimes = 0;

        blackbox = new BlackBox(120);
        getWorld().addObject(blackbox, getX(), getY());
        cooldownbar = new CooldownBar(unitCooldown, unitCooldown, this, 78, 15, 0, Color.CYAN, Color.BLACK, false, Color.BLACK, 3);
        getWorld().addObject(cooldownbar, getX(), getY() + 16);

        timer.mark();
    }

    public void SpawnUnit() {
        if (unit == "CFodder") {getWorld().addObject(new CFodder(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "CTank") {getWorld().addObject(new CTank(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "CRanger") {getWorld().addObject(new CRanger(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "CHealer") {getWorld().addObject(new CHealer(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "CWarrior") {getWorld().addObject(new CWarrior(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "SFodder") {getWorld().addObject(new SFodder(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "STank") {getWorld().addObject(new STank(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "SRanger") {getWorld().addObject(new SRanger(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "SHealer") {getWorld().addObject(new SHealer(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        } else if (unit == "SWarrior") {getWorld().addObject(new SWarrior(unitStage), spawn.getX(), spawn.getY() + 70 + Greenfoot.getRandomNumber(30));
        }
    }
}
