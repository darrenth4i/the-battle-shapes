import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class SpawnUnitButton extends Actor
{
    private String unit;
    private boolean circle, spawned;
    private Tower spawn;
    private int unitCost;
    

    public SpawnUnitButton(String u, int cost, boolean c) {
        unit = u;
        circle = c;
        unitCost = cost;

        // ArrayList<Tower> towers = (ArrayList<Tower>)getWorld().getObjects(Tower.class);
        // if (towers.get(0).getCircle() == circle) {
            // spawn = towers.get(0);
        // } else {
            // spawn = towers.get(1);
        // }

        String filePath = "/UnitButtons/" + unit + ".png";
        setImage(filePath);
        getImage().scale(90,60);
        spawned = true;
    }
    
    public void act() {
        if (spawned) {
            getWorld().addObject(new Text("$" + unitCost, 20), getX() - getImage().getWidth()/2 + 23, getY() + getImage().getHeight()/2 - 13);
            spawned = false;
        }
    }

    public void SpawnUnit() {
        if (unit == "CFodder") {getWorld().addObject(new CFodder(), spawn.getX(), spawn.getY());
        } else if (unit == "CTank") {getWorld().addObject(new CTank(), spawn.getX(), spawn.getY());
        } else if (unit == "CRanger") {getWorld().addObject(new CRanger(), spawn.getX(), spawn.getY());
        } else if (unit == "CHealer") {getWorld().addObject(new CHealer(), spawn.getX(), spawn.getY());
        } else if (unit == "CWarrior") {getWorld().addObject(new CWarrior(), spawn.getX(), spawn.getY());
        } else if (unit == "SFodder") {getWorld().addObject(new SFodder(), spawn.getX(), spawn.getY());
        } else if (unit == "STank") {getWorld().addObject(new STank(), spawn.getX(), spawn.getY());
        } else if (unit == "SRanger") {getWorld().addObject(new SRanger(), spawn.getX(), spawn.getY());
        } else if (unit == "SHealer") {getWorld().addObject(new SHealer(), spawn.getX(), spawn.getY());
        } else if (unit == "SWarrior") {getWorld().addObject(new SWarrior(), spawn.getX(), spawn.getY());
        }
    }
}
