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
    private GreenfootImage squareImage = new GreenfootImage("images/TowerProjectile/SquareMeteor.png");
    private GreenfootImage circleImage = new GreenfootImage("images/TowerProjectile/CircleMeteor.png");
    private static GreenfootSound meteorSound = new GreenfootSound("sounds/Effects/meteorcrash.wav");
    public Meteor(boolean circle, Unit target, int speed)
    {
        super(circle, target, 10);
        this.damage = 100;
        meteorSound.setVolume(80);
        //Changes image depending on team
        if(!circle)
        {
            squareImage.scale(65, 120);
            setImage(squareImage);
        }
        else
        {
            circleImage.scale(65, 120);
            setImage(circleImage);
        }
    }
    public void act()
    {
        super.act();
    }
    public void effect()
    {
        meteorSound.play(); // plays noise when impacting target
        target.hurt(damage);
    }
}
