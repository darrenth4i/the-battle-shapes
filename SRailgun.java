import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class SRailgun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SRailgun extends Square
{
    private boolean isHalfStep = true;
    /**
     * Act - do whatever the SRailgun wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public SRailgun()
    {
        super();
        imageScale = 0.55;
        attackXOffset = 40;
        attackYOffset = 0;
        loadAnimationFrames("images/Units/SRailgun");    
        setAtkSoundEffect(0,80);
        atkCooldown = 400;
        health = 20;
        knockbacks = 6;
        speed = 2;
        atk = 100;
        attackFrame = 6;
    }
    
    public void addedToWorld(World world)
    {
        if(justAddedToWorld)
        {
            super.addedToWorld(world);
            range = 400;
            standingRange = 300;
        }
    }
    
    protected void walk()
    {
        if(checkFront()&& !isAttacking)
        {
            //move(-speed);
            if(isHalfStep)
            {
                standingXPos = getX() + (int) speed;
            }
            isHalfStep = !isHalfStep;
        }
        else
        {
            isAttacking = true;
        }
    }
    
    protected void attack()
    { 
        List<Circle> potentialTargets = getObjectsInRange(range, Circle.class);
        List<Tower> towerTarget = getObjectsInRange(range,Tower.class);
        Tower tower = towerTarget.size() > 0 ? towerTarget.get(0) : null;
        if(potentialTargets.size() > 0 || towerTarget.size() > 0)
        {
            for(int i = 0; i < potentialTargets.size(); i++)
            {
                if(potentialTargets.get(i) != null)
                {
                    potentialTargets.get(i).shieldBreak();
                    potentialTargets.get(i).hurt(atk);
                    atkSoundEffect.play();
                    getWorld().addObject(new RailgunExplosion(), potentialTargets.get(i).getNormalX(), potentialTargets.get(i).getNormalY());
                }
            }
            if(tower != null && tower.getCircle())
            {
                tower = towerTarget.get(0);
                tower.hurt(atk);
                atkSoundEffect.play();
                getWorld().addObject(new RailgunExplosion(), tower.getX(), 400);
            }
        }
    }
    
    protected String getName(){
        return "SRailgun";
    }
}
