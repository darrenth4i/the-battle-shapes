import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fodder here.
 * 
 * @author Andy Li
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
            atkCooldown = 90;
            knockbacks = 2;
            speed = 2;
            atk = 4;
            health = 8;
            attackFrame = 5;
            
            setAtkSoundEffect(1, 100);
            break;
            
            case 2:
            attackXOffset = -15;
            attackYOffset = 5;
            moveXOffset = 0;
            moveYOffset = 20;
            loadAnimationFrames("images/Units/CFodder/StageTwo");
            
            attackFrame = 8;
            atkCooldown = 75;
            knockbacks = 2;
            speed = 4;
            atk = 6;
            health = 15;
            
            setAtkSoundEffect(1, 80);
            break;
            
            case 3:
            totalYOffset = -35;
            attackXOffset = -53;
            attackYOffset = 28;
            moveXOffset = 0;
            moveYOffset = 20;
            loadAnimationFrames("images/Units/CFodder/StageThree");
            
            attackFrame = 8;
            atkCooldown = 60;
            knockbacks = 3;
            speed = 4;
            atk = 9;
            health = 20;
            
            setAtkSoundEffect(1, 80);
            break;
        }
    }
    
    public void addedToWorld(World world)
    {
        super.addedToWorld(world);
        feetYPos = getY() + idleAnim.get(0).getHeight()/2 - moveYOffset;
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "CFodder";
    }
}
