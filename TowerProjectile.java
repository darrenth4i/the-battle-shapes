import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TowerProjectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class TowerProjectile extends Actor
{
    protected boolean circle;
    protected Unit target;
    protected int speed;
    public TowerProjectile(boolean circle, Unit target, int speed)
    {
        this.circle = circle;
        this.target = target;
        this.speed = speed;
    }
    /**
     * Projectile moves towards target at set speed
     * When the projectile reaches its target it calls the effect method
     * performing the overrided subclasses' method for effect
     */
    public void act()
    {
        moveTowards(target, speed);
        if(intersects(target))
        {
            effect();
        }
    }
    
    /**
     * Abstract method overrided by all subclasses to perform
     * their class specific class
     */
    public abstract void effect();
    
    /**
     * A method used to move towards an actor
     * Made by ChatGPT
     */
    public void moveTowards(Actor targetObject, int speed) {
        // Get the target's coordinates
        if(targetObject.getWorld()==null)
        {
            return;
        }
        int targetX = targetObject.getX();
        int targetY = targetObject.getY();
    
        // Get the moving object's current coordinates
        int currentX = getX();
        int currentY = getY();
    
        // Calculate the difference (direction vector)
        int deltaX = targetX - currentX;
        int deltaY = targetY - currentY;
    
        // Calculate the distance between the two objects
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    
        // If the distance is not zero, move towards the target
        if (distance != 0) {
            // Calculate the normalized direction (unit vector)
            double directionX = deltaX / distance;
            double directionY = deltaY / distance;
    
            // Move in the direction of the target by 'speed' units
            int newX = (int) (currentX + directionX * speed);
            int newY = (int) (currentY + directionY * speed);
    
            // Set the new position of the moving object
            setLocation(newX, newY);
        }
    }
}
