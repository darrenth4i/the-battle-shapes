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
    
    //coordinates for cursor to move to
    private Coordinate currentDestination;
    
    //index to access buttons list or destinations list
    //IMPORTANT: for square team, index is (0-4) LEFT to RIGHT
    //           for circle team, index is (0-4) RIGHT to LEFT
    private int destinationIndex;
    
    //variable to determine if cursor is circle team
    private boolean circle;
    //variable to run code once
    private boolean spawned;
    //arraylist for all buttons in world
    ArrayList<SpawnUnitButton> buttons;
    //shape/team specific arraylist
    ArrayList<SpawnUnitButton> buttonTeams;
    //string representation of buttonTeams;
    ArrayList<String> buttonNames;
    
    ArrayList<Unit> units;
    
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
        
        buttonTeams = new ArrayList<SpawnUnitButton>();
        buttonNames = new ArrayList<String>();
        enableStaticRotation();
        
        units = new ArrayList<Unit>();
    }
    
    /**
     * Act - do whatever the Cursor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //run only once when object is spawned
        if(!spawned){
            //look for buttons
            buttons = (ArrayList<SpawnUnitButton>)getWorld().getObjects(SpawnUnitButton.class);
            //if buttons not spawned yet, try again
            if(buttons == null){
                return;
            }
            //only want one team buttons
            for(SpawnUnitButton b : buttons){
                //add to team specific array list if cursor/button is circle/square
                if((circle && b.getCircle()) || (!circle && !b.getCircle())){
                    buttonTeams.add(b);
                }
            }
            //add name of each button for enemy AI easy access 
            for(SpawnUnitButton b : buttonTeams){
                buttonNames.add(b.getUnit());
            }
            spawned = true;
        }
        
        //reset destination index if too large DEBUG? maybe remove
        if(destinationIndex >= buttonTeams.size() - 1){
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
        if(currentDestination == null && !buttonTeams.get(destinationIndex).getOnCooldown() && isTouching(SpawnUnitButton.class)){
            buttonTeams.get(destinationIndex).setClicked(true);
            click(true);
        }
        
        //DEBUG remove
        if(Greenfoot.isKeyDown("space")){
            destinationIndex++;
        }
        if(Greenfoot.isKeyDown("w")){
            System.out.println(checkUnits(true));
        }
    }
    
    /**
     * Method to get the coordinates to a specific button's destination in buttonTeams
     */
    private Coordinate getNextDestination (int index) {
        return buttonTeams.get(index).getCoordinate();
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
     * Method that returns an int for destinationsIndex
     * based on specific factors happening in the simulation
     * 
     * Enemy AI
     */
    public int bestMove(){
        return 1;
    }
    
    /**
     * Method that returns the name of the most common unit
     * on either side based on specified team
     * 
     * @param self - Search for own team? true/false
     */
    public String checkUnits(boolean self){
        String mostCommonUnit = "";
        //count the number of each subclass
        int[] numUnits = new int[]{0, 0, 0, 0, 0};
        //names of each subclass
        String[] names = new String[]{"Fodder", "Warrior", "Tank", "Ranger", "Healer"};
        
        //check circle if self is circle or check enemy if self is square
        if((self && circle) || (!self && !circle)){
            //Convert to wildcard type arraylist before to Unit
            units = (ArrayList<Unit>)((ArrayList<?>)getWorld().getObjects(Circle.class));
        }
        //check square if self is square or check enemy if self is circle
        else{
            units = (ArrayList<Unit>)((ArrayList<?>)getWorld().getObjects(Square.class));
        }
        
        //check which subclass each unit alive is part of 
        for(Unit u : units){
            if(u.getName().contains(names[0])){
                numUnits[0] += 1;
            }
            else if(u.getName().contains(names[1])){
                numUnits[1] += 1;
            }
            else if(u.getName().contains(names[2])){
                numUnits[2] += 1;
            }
            else if(u.getName().contains(names[3])){
                numUnits[3] += 1;
            }
            else if(u.getName().contains(names[4])){
                numUnits[4] += 1;
            }
        }
        int most = numUnits[0];
        int mostIndex = 0;
        
        //find most common subclass
        for(int i = 0; i<numUnits.length; i++){
            if(numUnits[i] > most){
                most = numUnits[i];
                mostIndex = i;
            }
        }
        
        return names[mostIndex];
    }
    
    /**
     * Method that returns a boolean to bestMove() to
     * determine if the cursor should buy a specific unit close
     * to being upgraded
     * 
     * part of Enemy AI
     */
    public boolean worthUpgrading(){
        return false;
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

    //Return if cursor is part of circle team
    public boolean getCircle(){
        return circle;
    }
    
    /**
     * Method to replace a SpawnUnitButton in buttonTeams 
     * when it gets upgraded so the list has the right button
     * at all times
     */
    public void replaceButtonsTeam(int index, SpawnUnitButton b) {
        currentDestination = null;
        buttonTeams.set(index, b);
    }
}
