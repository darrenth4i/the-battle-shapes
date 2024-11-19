import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TowerProjectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class TowerProjectile extends SuperSmoothMover
{
    protected boolean circle;
    protected Unit target;
    protected int speed;
    protected GreenfootImage projectile;
    protected boolean contacted;
    protected GreenfootSound effectSound;
    public TowerProjectile(boolean circle, Unit target, int speed)
    {
        this.circle = circle;
        this.target = target;
        this.speed = speed;
        projectile = getImage();
        projectile.scale(10,10);
        enableStaticRotation ();
    }
    /**
     * Projectile moves towards target at set speed
     * When the projectile reaches its target it calls the effect method
     * performing the overrided subclasses' method for effect
     */
    public void act()
    {
        if ((target != null && target.getWorld() != null) && !target.getName().equals("CDragonHitbox")) 
        {
            // The target is still in the world
            moveTowardsTarget();
            if(intersects(target))
            {
                effect();
                getWorld().removeObject(this);
            }
        }
        else
        {
            getWorld().removeObject(this);
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
    protected void moveTowardsTarget() {
        int x1 = getX();
        int y1 = getY();
        int x2 = target.getNormalX();
        int y2 = target.getNormalY();

        // Calculate the angle towards the target
        double angle = Math.atan2(y2 - y1, x2 - x1);

        // Set rotation to face the target (optional)
        setRotation((int) Math.toDegrees(angle));

        // Move a certain number of units (speed) towards the target
        int dx = (int) (Math.cos(angle) * speed);
        int dy = (int) (Math.sin(angle) * speed);

        setLocation(x1 + dx, y1 + dy);
    }
    
    /*protected void moveTowardsTarget() 
    {
        int x1 = getX();
        int y1 = getY();
        int x2 = target.getNormalX();
        int y2 = target.getNormalY();

        // Calculate the angle towards the target
        double angle = Math.atan2(y2 - y1, x2 - x1);

        // Move a certain number of units (speed) towards the target
        int speed = 8;  // Adjust speed as needed
        int dx = (int) (Math.cos(angle) * speed);
        int dy;
        if(x1 > x2 - 40 && x1 < x2 +40)
        {
            dy = 15;
        }
        else
        {
            dy = -1;
        }

        setLocation(x1 + dx, y1 + dy);
        if(x1 > x2 - 40 && x1 < x2 +40 && y1 > y2)
        {
            contacted = true;
        }
    }*/
}
