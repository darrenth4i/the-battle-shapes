import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fodder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CFodder extends Circle
{
    /**
     * Act - do whatever the Fodder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public CFodder(int stage)
    {
        super(stage);
        
        loadAnimationFrames("images/Units/CFodder/StageOne");
        
        knockbacks = 2;
        speed = 2;
        atk = 4;
        health = 12;
        attackFrame = 5;
        
        attackXOffset = -50;
        attackYOffset = 0;
    }
}
