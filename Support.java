import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Support here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Support extends Tower
{
    public Support(boolean circle, int level, int maxHP)
    {
        super(circle, 2, level, maxHP);
    }
    /**
     * Act - do whatever the Support wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public void shoot()
    {
        if(getLowestHealthSameShape() != null)
        {
            getWorld().addObject(new SupportProjectile(circle,getLowestHealthSameShape(),1, 1), getX(), getY());
        }
    }
}
