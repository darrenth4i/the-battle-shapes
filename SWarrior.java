import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SWarrior here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SWarrior extends Square
{
    /**
     * Act - do whatever the SWarrior wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public SWarrior()
    {
        super();
        loadAnimationFrames("images/Units/SWarrior/StageOne");
        
        atkCooldown = 30;
        knockbacks = 2;
        speed = 3;
        atk = 8;
        health = 33;
        
        attackFrame = 11;
        attackXOffset = 15;
        attackYOffset = 0;
    }
}
