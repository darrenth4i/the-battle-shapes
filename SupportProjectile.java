import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SupportProjectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SupportProjectile extends TowerProjectile
{
    private int heal;
    public SupportProjectile(boolean circle, Unit target, int speed, int heal)
    {
        super(circle, target, speed);
        this.heal = heal;
        
    }
    public void act()
    {
        super.act();
    }
    public void effect()
    {
        target.heal(heal);
    }
}
