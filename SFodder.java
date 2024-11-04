import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SFodder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SFodder extends Square
{
    /**
     * Act - do whatever the SFodder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public SFodder(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            attackXOffset = 40;
            attackYOffset = -10;
            loadAnimationFrames("images/Units/SFodder/StageOne");
               
            knockbacks = 2;
            speed = 2;
            atk = 4;
            health = 12;
            attackFrame = 5;
        }
    }
}
