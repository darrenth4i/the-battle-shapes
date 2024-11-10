import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class SRanger here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SRanger extends Square
{
    /**
     * Act - do whatever the SRanger wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public SRanger(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            attackXOffset = 0;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/SRanger/StageOne");    
            
            atkCooldown = 60;
            health = 10;
            knockbacks = 6;
            speed = 2;
            atk = 6;
            attackFrame = 0;
            break;
            
            case 2:
            attackXOffset = 0;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/SRanger/StageTwo");    
            
            atkCooldown = 45;
            health = 16;
            knockbacks = 6;
            speed = 2;
            atk = 12;
            attackFrame = 0;
            break;
            
            case 3:
            attackXOffset = 10;
            attackYOffset = 0;
            moveXOffset = 0;
            moveYOffset = 0;
            loadAnimationFrames("images/Units/SRanger/StageThree");
            
            attackFrame = 3;
            atkCooldown = 30;
            knockbacks = 7;
            speed = 2;
            atk = 10;
            health = 20;
            break;
        }        
    }
    
    public void addedToWorld(World world)
    {
        if(justAddedToWorld)
        {
            super.addedToWorld(world);
            range = 400;
            standingRange = range - 50;
        }
    }
    
    protected void attack()
    { 
        List<Circle> potentialTargets = getObjectsInRange(range, Circle.class);
        Tower tower = (Tower)getOneObjectAtOffset(range+10, 0,Tower.class);

        if(potentialTargets.size() > 0||tower != null)
        {
            Circle target = potentialTargets.size() > 0 ? potentialTargets.get(0) : null;
            for(int i = 0; i < potentialTargets.size(); i++)
            {
                if(potentialTargets.get(i).getNormalX() < target.getNormalX())
                {
                    target = potentialTargets.get(i);
                }
            }
            if(tower != null && (target == null || (target.getNormalX() < getNormalX() + 200 && tower.getX() < target.getNormalX())))
            {
                target = null;
                
                tower.hurt(atk);
            }
            if(target != null && target.getNormalX() > getNormalX() + 200)
            {
                target.hurt(atk);
            }
        }
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "SRanger";
    }
}
