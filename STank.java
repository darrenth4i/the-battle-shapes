import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class STank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class STank extends Square
{
    /**
     * Act - do whatever the STank wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public STank(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            totalYOffset = -20;
            attackXOffset = 25;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/STank/StageOne");
            
            knockbacks = 3;
            speed = 2;
            atk = 3;
            health = 30;
            attackFrame = 5;
            break;
            
            case 2:
            totalYOffset = -60;
            attackXOffset = 30;
            attackYOffset = 5;
            moveYOffset = -20;
            loadAnimationFrames("images/Units/STank/StageTwo");
            
            attackFrame = 8;
            atkCooldown = 120;
            knockbacks = 1;
            speed = 2;
            atk = 4;
            health = 50;
            break;
            
            case 3:
            totalYOffset = -60;
            attackXOffset = 20;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/STank/StageThree");
            
            attackFrame = 11;
            atkCooldown = 120;
            knockbacks = 1;
            speed = 2;
            atk = 6;
            health = 70;
            break;
        }
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "STank";
    }
}
