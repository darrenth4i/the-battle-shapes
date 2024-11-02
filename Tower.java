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
    //Denotes team
    protected boolean circle;
    protected int towerRange;
    protected int fireInterval; //Higher rate means slower speed
    
    //Tower images - placeholders
    protected GreenfootImage circleTower;
    protected GreenfootImage squareTower;
    
    
    
    //Helper variables
    private double distance, nearestDistance, furthestDistance, lowestHealth;
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
    
    
}
