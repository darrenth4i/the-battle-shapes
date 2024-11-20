import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Warrior here.
 * 
 * @author Andy Li
 * @version (a version number or a date)
 */
public class CDragon extends Circle
{   
    private boolean isHalfStep = true;
    private CDragonHitbox hitbox ;
    
    public CDragon()
    {
        super();
        imageScale = 1;
        attackFrame = 110;
        attackXOffset = -118;
        attackYOffset = -22;
        totalYOffset = -100;
        
        loadAnimationFrames("images/Units/CDragon");
        atkCooldown = 240;
        knockbacks = 3;
        speed = 1;
        atk = 70;
        health = 16;
    }
    
    public void addedToWorld(World world)
    {
        if(justAddedToWorld)
        {
            super.addedToWorld(world);
            hitbox = new CDragonHitbox(this);
            getWorld().addObject(hitbox, getX(), getFeet());
            range = 300;
            standingRange = 200;
        }
    }
    
    /**
     * Act - do whatever the Dragon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    /**
     * Walks forward if nothing is obstructing movement
     */
    protected void walk()
    {
        if(checkFront()&& !isAttacking)
        {
            //move(-speed);
            if(isHalfStep)
            {
                standingXPos = getX() - (int) speed;
            }
            isHalfStep = !isHalfStep;
        }
        else
        {
            isAttacking = true;
        }
    }
    
    /**
     * Does damage to a target
     */
    protected void attack()
    {
        List<Square> potentialTargets = getObjectsInRange(range, Square.class);
        List<Tower> towerTarget = getObjectsInRange(range,Tower.class);
        Tower tower = towerTarget.size() > 0 ? towerTarget.get(0) : null;
        if(potentialTargets.size() > 0||towerTarget.size() > 0)
        {
            for(int i = 0; i < potentialTargets.size(); i++)
            {
                if(potentialTargets.get(i) != null)
                {
                    potentialTargets.get(i).shieldBreak();
                    potentialTargets.get(i).hurt(atk);
                    getWorld().addObject(new DragonExplosion(), potentialTargets.get(i).getNormalX(), potentialTargets.get(i).getNormalY());
                }
            }
            if(tower != null && !tower.getCircle())
            {
                tower = towerTarget.get(0);
                tower.hurt(atk);
                getWorld().addObject(new DragonExplosion(), tower.getX(), 400);
            }
            playAtkSoundEffect();
        }
    }
    
    protected void createGhost()
    {
        getWorld().removeObject(hitbox);
        super.createGhost();
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "CDragon";
    }
}
