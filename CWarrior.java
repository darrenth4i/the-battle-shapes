import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Warrior here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CWarrior extends Circle
{
    /**
     * Act - do whatever the Warrior wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public CWarrior()
    {
        super();
        loadAnimationFrames("images/Units/CWarrior/StageOne");
        speed = 2;
        atk = 3;
        health = 4;
        attackXOffset = -35;
        attackYOffset = -31;
    }
}
