import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Healer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CHealer extends Circle
{
    /**
     * Act - do whatever the Healer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public CHealer(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            attackXOffset = -12;
            attackYOffset = -9;
            loadAnimationFrames("images/Units/CRanger/StageOne");    
            
            attackFrame = 11;
            atkCooldown = 60;
            knockbacks = 6;
            speed = 2;
            atk = 6;
            health = 12;
            break;
            
            case 2:
            attackXOffset = -25;
            attackYOffset = -35;
            loadAnimationFrames("images/Units/CRanger/StageTwo");    
            
            attackFrame = 6;
            atkCooldown = 45;
            knockbacks = 6;
            speed = 2;
            atk = 8;
            health = 16;
            break;
            
            case 3:
            totalYOffset = -30;
            attackXOffset = -65;
            attackYOffset = 21;
            moveXOffset = 0;
            moveYOffset = 0;
            loadAnimationFrames("images/Units/CRanger/StageThree");
            
            attackFrame = 33;
            atkCooldown = 30;
            knockbacks = 7;
            speed = 2;
            atk = 10;
            health = 20;
            break;
        }
    }
    
    /**
     * Heals an ally
     */
    protected void attack()
    {
        List<Circle> potentialTargets = getObjectsInRange(range, Circle.class);
        if(potentialTargets.size() > 0)
        {
            Circle target = potentialTargets.get(0);
            for(int i = 0; i < potentialTargets.size(); i++)
            {
                if(potentialTargets.get(i).getNormalX() > target.getNormalX())
                {
                    target = potentialTargets.get(i);
                }
            }
            if(target != null)
            {
                //System.out.println("Chit");
                target.heal(atk);
            }
            else
            {
                //System.out.println("Cmiss");
            }
        }
    }
}
