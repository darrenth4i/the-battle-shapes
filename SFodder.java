import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SFodder here.
 * 
 * @author Justin Ye 
 * @version 10
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
    
    /** 
     * This constructor creates the SFodders, depending on what stage it is, it will take atk, health,
     * atkCooldown and access new frames to animate.
     */
    public SFodder(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            attackXOffset = 40;
            attackYOffset = -30;
            loadAnimationFrames("images/Units/SFodder/StageOne");
            setAtkSoundEffect(1,80);
            atkCooldown = 50;
            knockbacks = 2;
            speed = 2;
            atk = 4;
            health = 8;
            attackFrame = 5;
            break;
            
            case 2:
            attackXOffset = 40;
            attackYOffset = -30;
            loadAnimationFrames("images/Units/SFodder/StageTwo");
            setAtkSoundEffect(2,80);
            atkCooldown = 50;
            knockbacks = 2;
            speed = 4;
            atk = 8;
            health = 15;
            attackFrame = 5;
            break;
            
            case 3:
            attackXOffset = 40;
            attackYOffset = -30;
            loadAnimationFrames("images/Units/SFodder/StageThree");
            setAtkSoundEffect(3,80);
            attackFrame = 5;
            atkCooldown = 30;
            knockbacks = 3;
            speed = 4;
            atk = 12;
            health = 25;
            break;
        }
    }

    public void addedToWorld(World world)
    {
        if(justAddedToWorld)
        {
            super.addedToWorld(world);
            range += 20;
            standingRange += 20;
        }
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "SFodder";
    }
}
