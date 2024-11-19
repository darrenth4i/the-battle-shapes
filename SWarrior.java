import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SWarrior here.
 * 
 * @author Justin Ye 
 * @version 10
 */
public class SWarrior extends Square
{
    /**
     * Act - do whatever the SWarrior wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    /** 
     * This constructor creates the SWarrior, depending on what stage it is, it will take atk, health,
     * atkCooldown and access new frames to animate.
     */
    public SWarrior(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            attackXOffset = 60;
            attackYOffset = -30;
            loadAnimationFrames("images/Units/SWarrior/StageOne");
            setAtkSoundEffect(1,70);
            atkCooldown = 30;
            knockbacks = 2;
            speed = 3;
            atk = 8;
            health = 30;
            attackFrame = 11;
            break;
            
            case 2:
            attackXOffset = 65;
            attackYOffset = -30;
            loadAnimationFrames("images/Units/SWarrior/StageTwo");
            setAtkSoundEffect(2,80);
            atkCooldown = 30;
            attackFrame = 14;
            knockbacks = 2;
            speed = 3;
            atk = 12;
            health = 40;
            break;
            
            case 3:
            attackXOffset = 78;
            attackYOffset = -28;
            loadAnimationFrames("images/Units/SWarrior/StageThree");
            setAtkSoundEffect(3,70);
            atkCooldown = 40;
            attackFrame = 6;
            knockbacks = 2;
            speed = 3;
            atk = 16;
            health = 50;
            break;
        }
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "SWarrior";
    }
}
