import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Tower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public abstract class Tower extends Actor
{
    //Tower vaiables "traits"
    //Denotes team
    protected boolean circle;
    protected int towerRange;
    protected int fireInterval; //Higher rate means slower speed
    
    //Tower images - placeholders
    protected GreenfootImage circleTower;
    protected GreenfootImage squareTower;
    
    
    
    //Helper variables
    private double distance, nearestDistance;
    protected int count;
    public Tower(boolean circle, int towerRange, int fireInterval)
    {
        this.circle = circle;
        this.towerRange = towerRange;
        this.fireInterval = fireInterval;
        
        //Sets tower image depending on side
        if(circle)
        {
            setImage(circleTower);
        }
        else
        {
            setImage(squareTower);
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
    }
    public Unit getNearestOppositeShape()
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
     * Abstract method overrided by all subclasses
     * to shoot their class specific projectile
     */
    public abstract void shoot();
    
    /**
     * Used to find distance from an actor
     */
    public double getDistance(Actor actor)
    {
        return Math.hypot(actor.getX() - getX(), actor.getY() - getY());
    }
    public boolean getCircle()
    {
        return circle;
    }
    
    
}
