import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Meteor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Meteor extends TowerProjectile
{
    private int damage;
    private GreenfootImage image = new GreenfootImage("images/TowerProjectile/MeteorProjectile.png");
    public Meteor(boolean circle, Unit target, int speed)
    {
        super(circle, target, speed);
        this.damage = 100;
        
        image.scale(65, 65);
        setImage(image);
        if(!circle)
        {
            image.mirrorHorizontally();
        }
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
