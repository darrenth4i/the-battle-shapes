import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OffenseProjectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OffenseProjectile extends TowerProjectile
{
    private int damage;
    private boolean contacted;
    public OffenseProjectile(boolean circle, Unit target, int speed, int damage)
    {
        super(circle, target, speed);
        this.damage = damage;
        setImage("images/TowerProjectile/Offense.png");
        getImage().setTransparency(0);
    }
    public void act()
    {
        if (target != null && target.getWorld() != null) 
        {
            // The target is still in the world
            moveTowardsTarget();
            if(contacted)
            {
                effect();
                getWorld().addObject(new OffensiveProjectileExplosion(), getX(), getY());
                getWorld().removeObject(this);
            }
        }
        else
        {
            getWorld().removeObject(this);
        }
        if(getImage().getTransparency() < 250)
        {
            getImage().setTransparency(getImage().getTransparency() + 10);
        }
    }
    public void effect()
    {
        for(int i = 0; i < (circle ? getIntersectingObjects(Square.class).size() : getIntersectingObjects(Circle.class).size()); i++)
        {
            //area of effect damage
            if(circle)
            {
                Square target = getIntersectingObjects(Square.class).get(i);
            }
            else 
            {
                Circle target = getIntersectingObjects(Circle.class).get(i);
            }
            if(target.getNormalX() < getX() + 20 && target.getNormalX() > getX() - 20)
            {
                target.hurt(damage);
            }
        }
        //target.hurt(damage);
    }
    
    protected void moveTowardsTarget() 
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
    }
}
