import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class VisualSpawnUnit extends MenuButtons
{
    private SimpleTimer timer = new SimpleTimer();

    private String unit;
    private boolean circle;
    private int unitCost;
    private UnitSelector origin;
    private boolean visible;
    private Text displayCost;
    
    private int targetX, targetY;
    //Constructor for Buttons without "last" boolean = true

    public VisualSpawnUnit(String u, int cost, UnitSelector origin) {
        unit = u;
        unitCost = cost;
        this.origin = origin;
        
        if (u.substring(0, 1).equals("C")) {
            circle = true;
        } else {
            circle = false;
        }

        setImage("images/UIElements/MenuButtons/3.png");
        width = 90;
        height = 60;
        getImage().scale(width,height);
    }
    
    public void addedToWorld(World world)
    {
        displayCost = new Text("$" + unitCost, 18);
        targetX = getX();
        targetY = origin.getY();
        setLocation(origin.getX(), origin.getY());
    }
    
    public void act()
    {
        if(getX() == targetX && getY() == targetY)
        {
            String filePath = "/UnitButtons/" + unit + "_" + 1 + ".png";
            setImage(filePath);
            getImage().scale(width,height);
            getWorld().addObject(displayCost, getX() - getImage().getWidth()/2 + 24, getY() + getImage().getHeight()/2 - 15);
        }
        else
        {
            moveToPosition(targetX, targetY, 10);
        }
        buttonClick();
        if(!origin.getMenu())
        {
            getWorld().removeObject(displayCost);
            getWorld().removeObject(this);
        }
    }
    
    public void buttonFunction()
    {
        origin.selectUnit(unit, unitCost);
        origin.closeMenu();
    }
    
    public void moveToPosition(int x, int y, int speed)
    {
        double angle = Math.atan2(y - getY(), x - getX());
        
        int dx = (int) (Math.cos(angle) * speed);
        int dy = (int) (Math.sin(angle) * speed);

        setLocation(getX() + dx, getY() + dy);
        
        if(getX() < x + 10 && getX() > x - 10 && getY() < y + 10 && getY() > y - 10)
        {
            setLocation(x, y);
        }
    }
    
}
