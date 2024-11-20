import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Warrior here.
 * 
 * @author Andy Li
 * @version (a version number or a date)
 */
public class CWarrior extends Circle
{
    /**
     * Act - do whatever the Warrior wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public CWarrior(int stage)
    {
        super(stage);
        attackFrame = 11;
        attackXOffset = -35;
        attackYOffset = -31;
        totalYOffset = -25;
        switch(stage)
        {
            case 1:
            loadAnimationFrames("images/Units/CWarrior/StageOne");
            atkCooldown = 30;
            knockbacks = 2;
            speed = 3;
            atk = 8;
            health = 15;
            
            setAtkSoundEffect(1, 80);
            break;
            
            case 2:
            loadAnimationFrames("images/Units/CWarrior/StageTwo");
            atkCooldown = 30;
            knockbacks = 2;
            speed = 3;
            atk = 15;
            health = 20;
            
            setAtkSoundEffect(1, 80);
            break;
            
            case 3:
            attackXOffset = -35;
            attackYOffset = -15;
            loadAnimationFrames("images/Units/CWarrior/StageThree");
            atkCooldown = 30;
            knockbacks = 2;
            speed = 3;
            atk = 22;
            health = 25;
            
            setAtkSoundEffect(1, 85);
            break;
        }
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "CWarrior";
    }
}
