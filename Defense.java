import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Defence here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Defense extends Tower
{
    public Defense(boolean circle, int towerRange, int fireInterval)
    {
        super(circle, towerRange, fireInterval);
    }
    
    /**
     * Act - do whatever the Defence wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void shoot()
    {
        getWorld().addObject(new DefenseProjectile(circle,getFurthestSameShape(),1, 1), getX(), getY());
    }
}
