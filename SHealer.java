import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SHealer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SHealer extends Square
{
    /**
     * Act - do whatever the SHealer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public SHealer(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            attackXOffset = 0;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/SHealer/StageOne");
            
            knockbacks = 2;
            speed = 2;
            atk = 4;
            health = 12;
            attackFrame = 5;
        }
    }
}
