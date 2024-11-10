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
    private SimulationWorld world;
 
    private int speed;
    
    //timer to make cursor animation more noticeable
    private int cursorTimer;
    //index to access buttons list or destinations list
    private int destinationIndex;
    
    //coordinates for cursor to move to
    private Coordinate currentDestination;
    
    //variable to determine if cursor is circle team
    private boolean circle;
    private boolean spawned;
    ArrayList<SpawnUnitButton> buttons;
    //shape specific arraylist
    ArrayList<SpawnUnitButton> buttonsTeam;
    
    public Cursor(boolean cir){
        cursorIdle = new GreenfootImage("images/cursor.png");
        //Make held frame smaller, visual indicator of clicked
        cursorHeld = new GreenfootImage("images/cursorHeld.png");  
        
        speed = 3;
        cursorTimer = 0;
        destinationIndex = 0;
        
        world = (SimulationWorld)this.getWorld();
        clicked = false;
        setImage(cursorIdle);
        
        circle = cir;
        spawned = false;
        
        buttonsTeam = new ArrayList<SpawnUnitButton>();
        enableStaticRotation();
    }
    
    /**
     * Act - do whatever the Cursor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!spawned){
            buttons = (ArrayList<SpawnUnitButton>)getWorld().getObjects(SpawnUnitButton.class);
            if(buttons == null){
                return;
            }
            for(SpawnUnitButton c : buttons){
                //add to team specific array list if cursor/button is circle/square
                if((circle && c.getCircle()) || (!circle && !c.getCircle())){
                    buttonsTeam.add(c);
                }
            }
            spawned = true;
        }
        
        //if mouse is held, set clicked to true, if let go, set to false
        if(Greenfoot.mousePressed(world)){
            clicked = true;
        }
        if(Greenfoot.mouseClicked(world)){
            clicked = false;
        }
        
        //reset destination index if too large
        if(destinationIndex >= buttonsTeam.size() - 1){
            destinationIndex = 0;
        }
        
        // Check if there is another destination for me if I don't have one
        if (currentDestination == null){
            currentDestination = getNextDestination (destinationIndex);
        }
        
        //move to button coords
        followCursor(currentDestination);
        //check to reset mouse held animation
        click(false);
        
        //"Click" button if it exists, cursor is on it, and isnt on cooldown 
        if(currentDestination == null && !buttonsTeam.get(destinationIndex).getOnCooldown() && isTouching(SpawnUnitButton.class)){
            buttonsTeam.get(destinationIndex).setClicked(true);
            click(true);
        }
        
        if(Greenfoot.isKeyDown("space")){
            destinationIndex++;
        }
    }
    
    private Coordinate getNextDestination (int index) {
        return buttonsTeam.get(index).getCoordinate();
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
        }
        else {
            setLocation(getX() + adjustedSpeedX, getY() + adjustedSpeedY);
        }  
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

    public boolean getCircle(){
        return circle;
    }
    
    public void replaceButtonsTeam(int index, SpawnUnitButton b) {
        currentDestination = null;
        buttonsTeam.set(index, b);
    }
}
