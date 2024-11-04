import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SRanger here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SRanger extends Square
{
    /**
     * Act - do whatever the SRanger wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public SRanger(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            attackXOffset = -12;
            attackYOffset = -9;
            loadAnimationFrames("images/Units/SRanger/StageOne");    
            
            attackFrame = 11;
            atkCooldown = 60;
            knockbacks = 6;
            speed = 2;
            atk = 6;
            health = 12;
        }
    }
}
