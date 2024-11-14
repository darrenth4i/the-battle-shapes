import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CTank extends Circle
{
    /**
     * Act - do whatever the Tank wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public CTank(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            attackXOffset = 0;
            attackYOffset = -20;
            loadAnimationFrames("images/Units/CTank/StageOne");    
            
            atkCooldown = 120;
            knockbacks = 1;
            speed = 2;
            atk = 2;
            health = 50;
            break;
            
            case 2:
            totalYOffset = -25;
            attackXOffset = -35;
            attackYOffset = -22;
            moveXOffset = 0;
            moveYOffset = 0;
            loadAnimationFrames("images/Units/CTank/StageTwo");
            
            attackFrame = 8;
            atkCooldown = 120;
            knockbacks = 1;
            speed = 2;
            atk = 4;
            health = 75;
            break;
            
            case 3:
            totalYOffset = -45;
            attackXOffset = -34;
            attackYOffset = -46;
            moveXOffset = 0;
            moveYOffset = 0;
            loadAnimationFrames("images/Units/CTank/StageThree");
            
            attackFrame = 8;
            atkCooldown = 120;
            knockbacks = 1;
            speed = 2;
            atk = 6;
            health = 100;
            break;
        }
    }
    
    public void addedToWorld(World world)
    {
        if(justAddedToWorld)
        {
            super.addedToWorld(world);
            standingRange += 5;
        }
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "CTank";
    }
}
