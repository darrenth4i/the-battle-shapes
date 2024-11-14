import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class STesseract here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class STesseract extends Square
{
    /**
     * Act - do whatever the SFodder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public STesseract()
    {
        super();
        attackXOffset = 40;
        attackYOffset = -30;
        loadAnimationFrames("images/Units/STesseract");
        
        atkCooldown = 60;
        knockbacks = 2;
        speed = 2;
        atk = 4;
        health = 12;
        attackFrame = 0;
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "SFodder";
    }
}
