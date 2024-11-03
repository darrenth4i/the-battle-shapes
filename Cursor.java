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
    
    //DEBUG PURPOSES ONLY, follow mouse movement
    private MouseInfo mouse;
    //variables to determine if left clicked
    private boolean clicked;
    private MyWorld world;
    
    private ArrayList<SpawnUnitButton> buttons;
    private SpawnUnitButton targetSpawnUnitButton;
    private int speed;
    
    //timer to make cursor animation more noticeable
    private int cursorTimer;
    
    public Cursor(){
        cursorIdle = new GreenfootImage("images/cursor.png");
        //Make held frame smaller, visual indicator of clicked
        cursorHeld = new GreenfootImage("images/cursorHeld.png");  
        
        speed = 3;
        cursorTimer = 0;
        
        world = (MyWorld)this.getWorld();
        clicked = false;
        setImage(cursorIdle);
    }
    
    /**
     * Act - do whatever the Cursor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //if mouse is held, set clicked to true, if let go, set to false
        if(Greenfoot.mousePressed(world)){
            clicked = true;
        }
        if(Greenfoot.mouseClicked(world)){
            clicked = false;
        }
        
        followCursor();
        click(clicked);
        //Move cursor to closest button
        cursorMovement(targetClosestSpawnUnitButton());
        //"Click" button if it exists, cursor is on it, and isnt on cooldown approx every 4s
        if(Greenfoot.getRandomNumber(240) == 0 && targetClosestSpawnUnitButton() != null && !targetClosestSpawnUnitButton().getOnCooldown() && isTouching(SpawnUnitButton.class)){
            targetClosestSpawnUnitButton().setClicked(true);
            click(true);
        }
    }
    
    /**
     * DEBUG Method to set cursor movement of mouse via click
     */
    public void followCursor(){
        //DEBUG, remove mouse when done
        mouse = Greenfoot.getMouseInfo();
        if(mouse != null && Greenfoot.mouseClicked(null)){
            setLocation(mouse.getX(), mouse.getY());
        }    
    }
    
    /**
     * Method to follow cursor movement of mouse 
     */
    public void cursorMovement(Actor button){
        int distanceX = button.getX() - this.getX();
        int distanceY = button.getY() - this.getY();
        
        //Normalize the direction vector = get direction/angle, but change magnitude to 1 so speed isn't increased/decrease
        //Math.atan2(y, x) returns the angle of the line formed between Cursor and SpawnUnitButton with respect to 
        //the x-axis starting from Q1, and greenfoot also calculates angles starting from Q1 so it works
        //ex.) if angle = 160 deg -> Q2, angle = 280 deg -> Q4 (follows quadrant of cartesian plane)
        double angle = Math.atan2(distanceY, distanceX);
        
        //multiply speed x and y components by angle so it moves smoothly, cos = x component, sin = y component
        double adjustedSpeedX = speed * Math.cos(angle);
        double adjustedSpeedY = speed * Math.sin(angle);
        
        //Set a hori/vert distance magnitude limit so it doesnt jitter back and forth
        if(Math.abs(distanceX) >= 5 || Math.abs(distanceY) >= 5){
            setLocation(getX() + adjustedSpeedX, getY() + adjustedSpeedY);
        }
    }
    
    /**
     * Private method, called by act(), that constantly checks for closer targets
     * 
     * Created by Mr. Cohen, reused/modified for Cursor to glide towards closest SpawnUnitButton
     */
    private SpawnUnitButton targetClosestSpawnUnitButton ()
    {
        double closestTargetDistance = 0;
        double distanceToActor;
        // Get a list of all SpawnUnitButtons in the World, cast it to ArrayList
        // for easy management

        buttons = (ArrayList<SpawnUnitButton>)getObjectsInRange(40, SpawnUnitButton.class);
        if (buttons.size() == 0){
            buttons = (ArrayList<SpawnUnitButton>)getObjectsInRange(150, SpawnUnitButton.class);
        } 
        if (buttons.size() == 0){
            buttons = (ArrayList<SpawnUnitButton>)getObjectsInRange(800, SpawnUnitButton.class);
        } 

        if (buttons.size() > 0)
        {
            // set the first one as my target
            targetSpawnUnitButton = buttons.get(0);
            // Use method to get distance to target. This will be used
            // to check if any other targets are closer
            closestTargetDistance = getDistance(this, targetSpawnUnitButton);

            // Loop through the objects in the ArrayList to find the closest target
            for (SpawnUnitButton o : buttons)
            {
                // Cast for use in generic method
                //Actor a = (Actor) o;
                // Measure distance from me
                distanceToActor = getDistance(this, o);
                // If I find a SpawnUnitButton closer than my current target, I will change
                // targets
                if (distanceToActor < closestTargetDistance)
                {
                    targetSpawnUnitButton = o;
                }
            }
            return targetSpawnUnitButton;
        }
        return null;
    }    
    
    /**
     * Method to return the closest distance between two Actors, a and b
     */
    public double getDistance(Actor a, Actor b){
        return Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
    }
    
    /**
     * Method to make image smaller to show it has been clicked
     */
    public void click(boolean c){
        cursorTimer++;
        if(c){
            setImage(cursorHeld);
        }
        //Only set back to idle if 0.2s passed so its more visible
        else if(cursorTimer >= 12){
            setImage(cursorIdle);
        }
    }

}
