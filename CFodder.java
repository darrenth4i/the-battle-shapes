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
        switch(stage)
        {
            case 1:
            attackXOffset = -45;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/CFodder/StageOne");    
            
            attackFrame = 5;
            atkCooldown = 60;
            knockbacks = 2;
            speed = 2;
            atk = 4;
            health = 12;
            attackFrame = 5;
            break;
            
            case 2:
            attackXOffset = -15;
            attackYOffset = 5;
            moveXOffset = 0;
            moveYOffset = 20;
            loadAnimationFrames("images/Units/CFodder/StageTwo");
            
            attackFrame = 8;
            atkCooldown = 60;
            knockbacks = 2;
            speed = 4;
            atk = 8;
            health = 24;
            break;
            
            case 3:
            totalYOffset = -35;
            attackXOffset = -53;
            attackYOffset = 28;
            moveXOffset = 0;
            moveYOffset = 20;
            loadAnimationFrames("images/Units/CFodder/StageThree");
            
            attackFrame = 8;
            atkCooldown = 30;
            knockbacks = 3;
            speed = 4;
            atk = 10;
            health = 30;
            break;
        }
    }
}
