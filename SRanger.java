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
            attackXOffset = -12;
            attackYOffset = -9;
            loadAnimationFrames("images/Units/SRanger/StageOne");    
            
            atkCooldown = 100;
            health = 10;
            knockbacks = 6;
            speed = 2;
            atk = 6;
            attackFrame = 2;
        }        
    }
    
    public void addedToWorld(World world)
    {
        super.addedToWorld(world);
        range = 400;
    }
    
    protected void attack()
    {
        List<Circle> potentialTargets = getObjectsInRange(range, Circle.class);
    
        if (potentialTargets.size() > 0) {
            Circle target = potentialTargets.get(0);
            
            // Find the closest target approaching from the right (highest NormalX value).
            for (int i = 0; i < potentialTargets.size(); i++) {
                if (potentialTargets.get(i).getNormalX() > target.getNormalX()) {
                    target = potentialTargets.get(i);
                }
            }
            //flip the values
            if (target.getNormalX() < getNormalX() + 200) {
                target = null;
            }
            
            if (target != null) {
                target.hurt(atk);
            }
        }
    }
}
