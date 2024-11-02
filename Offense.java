import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Offense here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Offense extends Tower
{
    public Offense(boolean circle, int towerRange, int fireInterval)
    {
        super(circle, towerRange, fireInterval);
    }
    /**
     * Act - do whatever the Offense wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    public void shoot()
    {
        getWorld().addObject(new OffenseProjectile(circle,getNearestOppositeShape(),1, 1), getX(), getY());
    }
}
