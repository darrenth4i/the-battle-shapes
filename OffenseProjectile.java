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
    public OffenseProjectile(boolean circle, Unit target, int speed, int damage)
    {
        super(circle, target, speed);
        this.damage = damage;
        
    }
    public void act()
    {
        super.act();
    }
    public void effect()
    {
        target.hurt(damage);
    }
    
}
