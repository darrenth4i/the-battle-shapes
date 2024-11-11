import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DefenseProjectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DefenseProjectile extends TowerProjectile
{
    private int mitigation; //Times the shield will mitigate damage
    public DefenseProjectile(boolean circle, Unit target, int speed, int mitigation)
    {
        super(circle, target, speed);
        this.mitigation = mitigation;
        setImage("images/TowerProjectile/Defense.png");
    }
    public void act()
    {
        super.act();
    }
    public void effect()
    {
        target.shield(mitigation);
    }
}
