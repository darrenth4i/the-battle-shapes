import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class SHealer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SHealer extends Square
{
    /**
     * Act - do whatever the SHealer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public SHealer(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            attackXOffset = 0;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/SHealer/StageOne");
            setAtkSoundEffect(1,80);
            atkCooldown = 180;
            knockbacks = 2;
            speed = 1;
            atk = 15;
            health = 12;
            attackFrame = 5;
            break;
            
            case 2:
            attackXOffset = 0;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/SHealer/StageTwo");
            setAtkSoundEffect(2,80);
            attackFrame = 6;
            atkCooldown = 150;
            knockbacks = 2;
            speed = 1;
            atk = 20;
            health = 24;
            break;
            
            case 3:
            attackXOffset = 0;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/SHealer/StageThree");
            setAtkSoundEffect(3,80);
            attackFrame = 6;
            atkCooldown = 120;
            knockbacks = 3;
            speed = 1;
            atk = 40;
            health = 40;
            break;
        }
    }
    
    //Andy's code
    public void addedToWorld(World world)
    {
        if(justAddedToWorld)
        {
            super.addedToWorld(world);
            range = 300;
            standingRange = range - range/10;
        }
    }
    
    protected void attack()
    {
        List<Square> potentialTargets = getObjectsInRange(range, Square.class);
        if(potentialTargets.size() > 0)
        {
            Square target = potentialTargets.get(0);
            for(int i = 0; i < potentialTargets.size(); i++)
            {
                if(potentialTargets.get(i).getHealthDividedByMax() < target.getHealthDividedByMax())
                {
                    target = potentialTargets.get(i);
                }
            }
            if(target != null)
            {
                //System.out.println("S hit");
                target.heal(atk);
                atkSoundEffect.play();
            }
            else
            {
                //System.out.println("Smiss");
            }
        }
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "SHealer";
    }
}
