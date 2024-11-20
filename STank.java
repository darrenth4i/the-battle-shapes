import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class STank here.
 * 
 * @author Justin Ye 
 * @version 10
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
    
    /** 
     * This constructor creates the STank, depending on what stage it is, it will take atk, health,
     * atkCooldown and access new frames to animate.
     */
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
            setAtkSoundEffect(1,90);
            knockbacks = 1;
            speed = 2;
            atk = 2;
            health = 30;
            attackFrame = 5;
            break;
            
            case 2:
            totalYOffset = -60;
            attackXOffset = 30;
            attackYOffset = 5;
            moveYOffset = -20;
            loadAnimationFrames("images/Units/STank/StageTwo");
            setAtkSoundEffect(2,80);
            attackFrame = 8;
            atkCooldown = 120;
            knockbacks = 1;
            speed = 2;
            atk = 3;
            health = 50;
            break;
            
            case 3:
            totalYOffset = -50;
            attackXOffset = 30;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/STank/StageThree");
            setAtkSoundEffect(3,65);
            attackFrame = 4;
            atkCooldown = 120;
            knockbacks = 1;
            speed = 2;
            atk = 4;
            health = 70;
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
        return "STank";
    }
}
