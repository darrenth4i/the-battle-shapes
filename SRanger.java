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
            attackYOffset = -9;
            loadAnimationFrames("images/Units/SRanger/StageOne");    
            
            atkCooldown = 100;
            health = 10;
            knockbacks = 6;
            speed = 2;
            atk = 6;
            attackFrame = 0;
        }        
    }
    
    public void addedToWorld(World world)
    {
        super.addedToWorld(world);
        range = 400;
        standingRange = range - 50;
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
}
