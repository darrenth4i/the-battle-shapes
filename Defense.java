import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Defence here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Defense extends Tower
{
    public Defense(boolean circle, int towerRange, int fireInterval, int level, int maxHP)
    {
        super(circle, towerRange, fireInterval, 0, level, maxHP);
    }
    
    /**
     * Act - do whatever the Defence wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public void shoot()
    {
        if(getFurthestSameShape() != null)
        {
            getWorld().addObject(new DefenseProjectile(circle,getFurthestSameShape(),1, 1), getX(), getY());
        }
    }
}
