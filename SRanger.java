import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class SRanger here.
 * 
 * @author Justin Ye 
 * @version 10
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
    
    /** 
     * This constructor creates the SRanger, depending on what stage it is, it will take atk, health,
     * atkCooldown and access new frames to animate.
     */
    public SRanger(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            attackXOffset = 0;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/SRanger/StageOne");    
            setAtkSoundEffect(1,70);
            atkCooldown = 75;
            health = 5;
            knockbacks = 6;
            speed = 2;
            atk = 5;
            attackFrame = 10;
            break;
            
            case 2:
            attackXOffset = 0;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/SRanger/StageTwo");    
            setAtkSoundEffect(2,65);
            atkCooldown = 75;
            health = 8;
            knockbacks = 6;
            speed = 2;
            atk = 12;
            attackFrame = 3;
            break;
            
            case 3:
            attackXOffset = 10;
            attackYOffset = 0;
            moveXOffset = 0;
            moveYOffset = 0;
            loadAnimationFrames("images/Units/SRanger/StageThree");
            setAtkSoundEffect(3,80);
            attackFrame = 3;
            atkCooldown = 75;
            knockbacks = 7;
            speed = 2;
            atk = 20;
            health = 10;
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
     * Basically just like the Railgun attack(), but it takes only one target that is closest and in 
     * range, it attacks circles and its circle tower when it gets in range.
     */
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
                atkSoundEffect.play();
            }
            if(target != null && target.getNormalX() > getNormalX() + 200)
            {
                target.hurt(atk);
                atkSoundEffect.play();
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
