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
            attackYOffset = -30;
            loadAnimationFrames("images/Units/SFodder/StageOne");
            
            atkCooldown = 60;
            knockbacks = 2;
            speed = 2;
            atk = 4;
            health = 12;
            attackFrame = 5;
            
            break;
            
            case 2:
            attackXOffset = 40;
            attackYOffset = -30;
            loadAnimationFrames("images/Units/SFodder/StageTwo");
               
            atkCooldown = 60;
            knockbacks = 2;
            speed = 4;
            atk = 8;
            health = 24;
            attackFrame = 5;
            break;
            
            case 3:
            attackXOffset = 0;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/SFodder/StageThree");
            
            attackFrame = 5;
            atkCooldown = 30;
            knockbacks = 3;
            speed = 4;
            atk = 10;
            health = 30;
            break;
        }
    }
}
