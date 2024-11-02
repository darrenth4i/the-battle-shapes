import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Tower here.
 * 
 * @author Brennan Lyn 
 * @version (a version number or a date)
 */

public abstract class Tower extends Actor
{
    //Tower vaiables "traits"
    protected int maxHealth = 1000, health = maxHealth;
    //Denotes team
    protected boolean circle;
    protected int towerRange;
    protected int fireInterval; //Higher rate means slower speed
    
    //Tower images - placeholders
    protected GreenfootImage towerImage;
    
    
    
    //Helper variables
    private double distance, nearestDistance, furthestDistance, lowestHealth;
    protected int count;
    public Tower(boolean circle, int towerRange, int fireInterval, int type)
    {
        this.circle = circle;
        this.towerRange = towerRange;
        this.fireInterval = fireInterval;
        
        //Sets tower image depending on side and type
        if(circle)
        {
            if(type == 0){
                towerImage = new GreenfootImage("Towers/Circle/Defense.png");
            }
            else if(type == 1){
                towerImage = new GreenfootImage("Towers/Circle/Offense.png");
            }
            else{
                towerImage = new GreenfootImage("Towers/Circle/Support.png");
            }
            setImage(towerImage);
        }
        else
        {
            if(type == 0){
                towerImage = new GreenfootImage("Towers/Square/Defense.png");
            }
            else if(type == 1){
                towerImage = new GreenfootImage("Towers/Square/Offense.png");
            }
            else{
                towerImage = new GreenfootImage("Towers/Square/Support.png");
            }
            setImage(towerImage);
        }
    }
    /**
     * Act - do whatever the Tower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        count++;
        if(count%fireInterval == 0)
        {
            shoot();
        }
        if(health<0)
        {
            endSimulation();
        }
    }
    
    /** {NOT IMPLEMENTED YET}
     * The method that is run to end the simulation, resulting 
     * in either square's or circle's victory
     */
    protected void endSimulation()
    {
        if(circle)
        {
            
        }
        else
        {
            
        }
    }
    
    /**
     * Gets nearest instance of Unit class of opposite team
     * Used by Offense tower to target the frontmost unit to shoot at
     */
    protected Unit getNearestOppositeShape()
    {
        if(circle)
        {
            ArrayList<Square> nearSquares = (ArrayList<Square>)getObjectsInRange(towerRange, Square.class);
            Square nearestSquare = null;
            nearestDistance = -1;
            for(Square s : nearSquares)
            {
                distance = getDistance(s);
                if (distance < nearestDistance || nearestDistance == -1)
                {
                    nearestSquare = s;
                    nearestDistance = distance;
                }
            }
            return nearestSquare;
        }
        else
        {
            ArrayList<Circle> nearCircles = (ArrayList<Circle>)getObjectsInRange(towerRange, Circle.class);
            Circle nearestCircle = null;
            nearestDistance = -1;
            for(Circle c : nearCircles)
            {
                distance = getDistance(c);
                if (distance < nearestDistance || nearestDistance == -1)
                {
                    nearestCircle = c;
                    nearestDistance = distance;
                }
            }
            return nearestCircle;
        }
    }
    
    /**
     * Gets furthest instance of Unit class of the same shape
     * Used by Defense tower to target the frontmost unit of that "team"
     */
    protected Unit getFurthestSameShape()
    {
        if(!circle)
        {
            ArrayList<Square> nearSquares = (ArrayList<Square>)getObjectsInRange(towerRange, Square.class);
            Square furthestSquare = null;
            furthestDistance = -1;
            for(Square s : nearSquares)
            {
                distance = getDistance(s);
                if (distance > furthestDistance)
                {
                    furthestSquare = s;
                    furthestDistance = distance;
                }
            }
            return furthestSquare;
        }
        else
        {
            ArrayList<Circle> nearCircles = (ArrayList<Circle>)getObjectsInRange(towerRange, Circle.class);
            Circle furthestCircle = null;
            furthestDistance = -1;
            for(Circle c : nearCircles)
            {
                distance = getDistance(c);
                if (distance > furthestDistance)
                {
                    furthestCircle = c;
                    furthestDistance = distance;
                }
            }
            return furthestCircle;
        }
    }
    
    /**
     * Gets lowest health instance of Unit class of the same shape
     * Used by Support tower to target the lowest health unit of that "team"
     */
    protected Unit getLowestHealthSameShape()
    {
        if(!circle)
        {
            ArrayList<Square> nearSquares = (ArrayList<Square>)getObjectsInRange(towerRange, Square.class);
            Square lowHealthSquare = null;
            lowestHealth = 1.0;
            for(Square s : nearSquares)
            {
                if (s.getHealthDividedByMax()<=lowestHealth)
                {
                    lowHealthSquare = s;
                    lowestHealth = s.getHealthDividedByMax();
                }
            }
            return lowHealthSquare;
        }
        else
        {
            ArrayList<Circle> nearCircles = (ArrayList<Circle>)getObjectsInRange(towerRange, Circle.class);
            Circle furthestCircle = null;
            furthestDistance = -1;
            for(Circle c : nearCircles)
            {
                distance = getDistance(c);
                if (distance > furthestDistance)
                {
                    furthestCircle = c;
                    furthestDistance = distance;
                }
            }
            return furthestCircle;
        }
    }
    
    /**
     * Abstract method overrided by all subclasses
     * to shoot their class specific projectile
     */
    protected abstract void shoot();
    
    /**
     * Used to find distance from an actor
     */
    public double getDistance(Actor actor)
    {
        return Math.hypot(actor.getX() - getX(), actor.getY() - getY());
    }
    
    /**
     * Getter method to return boolean "circle"
     * which is true when the tower team is circle, false
     * if square
     */
    public boolean getCircle()
    {
        return circle;
    }
    
    public void hurt(int damage)
    {
        health-=damage;
    }
}
