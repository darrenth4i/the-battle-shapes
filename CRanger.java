import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Ranger here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CRanger extends Circle
{
    /**
     * Act - do whatever the Ranger wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public CRanger(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            attackXOffset = -27;
            attackYOffset = -5;
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
    
    public void addedToWorld(World world)
    {
        if(justAddedToWorld)
        {
            super.addedToWorld(world);
            range = 400;
            standingRange = range - 50;
        }
    }
    
    /**
     * Does damage to a target from a distance within a certain range.
     */
    protected void attack()
    {
        List<Square> potentialTargets = getObjectsInRange(range, Square.class);
        Tower tower = stage == 3 ? (Tower)getOneObjectAtOffset(-range+30, 0,Tower.class) : (Tower)getOneObjectAtOffset(-range+10, 0,Tower.class);

        if(potentialTargets.size() > 0||tower != null)
        {
            Square target = potentialTargets.size() > 0 ? potentialTargets.get(0) : null;
            for(int i = 0; i < potentialTargets.size(); i++)
            {
                if(potentialTargets.get(i).getNormalX() < target.getNormalX())
                {
                    target = potentialTargets.get(i);
                }
            }
            if(tower != null && (target == null || (target.getNormalX() > getNormalX() - 200 && tower.getX() > target.getNormalX())))
            {
                target = null;
                
                tower.hurt(atk);
                if(stage == 3)
                {
                    getWorld().addObject(new RangerExplosion(), tower.getX(), tower.getY()+20);
                }
            }
            if(target != null && target.getNormalX() < getNormalX() - 200)
            {
                target.hurt(atk);
                if(stage == 3)
                {
                    getWorld().addObject(new RangerExplosion(), target.getNormalX(), target.getNormalY());
                }
            }
        }
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "CRanger";
    }
}
