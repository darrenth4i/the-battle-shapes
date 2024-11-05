import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealParticle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealParticle extends HealEffect
{
    public HealParticle()
    {
        setImage("images/Effects/heal/HealParticle.png");
    }
    /**
     * Act - do whatever the HealParticle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getX() + (int)(Math.sin(getY()) * 5), getY() - 5);
        getImage().setTransparency(getImage().getTransparency()-5);
        if (getImage().getTransparency() == 0)
        {
            getWorld().removeObject(this); 
        }
    }
}
