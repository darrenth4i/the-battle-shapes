import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class VisualSpawnUnit extends Menu
{
    private SimpleTimer timer = new SimpleTimer();

    private String unit;
    private Wallet wallet;
    private boolean circle, spawned, onCooldown, canUpgrade;
    private Tower spawn;
    private int unitCost, unitStage;

    //Used for upgrade of the buttons to spawn upgraded units
    private int spent;
    final private int firstUpgrade;
    final private int secondUpgrade;
    
    private boolean visible;

    //Boolean to determine if the cursor code "clicks" button 
    private boolean clicked;

    //boolean to check if the last button has been created
    private boolean last;

    //Constructor for Buttons without "last" boolean = true

    public VisualSpawnUnit(String u, int stage, int cost) {
        unit = u;
        unitCost = cost;
        unitStage = stage;
        this.canUpgrade = canUpgrade;

        firstUpgrade = unitCost * 5;
        secondUpgrade = unitCost * 15;
        
        if (u.substring(0, 1).equals("C")) {
            circle = true;
        } else {
            circle = false;
        }

        String filePath = "/UnitButtons/" + unit + "_" + unitStage + ".png";
        if(visible)
        {
            setImage(filePath);
            getImage().scale(90,60);
        }
    }

    
}
