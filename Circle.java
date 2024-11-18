import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * The superclass for every circle unit
 * 
 * @author Andy Li
 * @version (a version number or a date)
 */
public abstract class Circle extends Unit
{
    /**
     * Act - do whatever the Circle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public Circle(int stage)
    {
        super(stage);
        //Sets image size
        imageScale = 0.35;
        getImage().scale((int)(getImage().getWidth()*imageScale),(int)(getImage().getHeight()*imageScale));
    }
    public Circle()
    {
        super();
    }
    
    /**
     * Walks forward if nothing is obstructing movement
     */
    protected void walk()
    {
        if(checkFront()&& !isAttacking)
        {
            //move(-speed);
            standingXPos = getX() - (int) speed;
        }
        else
        {
            isAttacking = true;
        }
    }
    
    protected void knockback()
    {
        setLocation(getX()+10, getY()+(3*(knockbackTimer-5)));
        standingXPos = getX() + 10;
        setRotation(20);
    }
    
    /**
     * Does damage to a target
     */
    protected void attack()
    {
        List<Square> potentialTargets = getObjectsInRange(range, Square.class);
        List<Tower> towerTarget = getObjectsInRange(2 * range,Tower.class);
        Tower tower = towerTarget.size() > 0 ? towerTarget.get(0) : null;
        if(potentialTargets.size() > 0||towerTarget.size() > 0)
        {
            Square target = potentialTargets.size() > 0 ? potentialTargets.get(0) : null;
            for(int i = 0; i < potentialTargets.size(); i++)
            {
                if(potentialTargets.get(i).getNormalX() > target.getNormalX())
                {
                    target = potentialTargets.get(i);
                }
            }
            if(tower != null && !tower.getCircle() && (target == null || tower.getX() > target.getNormalX()))
            {
                tower = towerTarget.get(0);
                target = null;
            }
            if(target != null)
            {
                target.hurt(atk);
                playAtkSoundEffect();
            }
            else if(tower != null)
            {
                tower.hurt(atk);
                playAtkSoundEffect();
            }
        }
    }
    
    protected boolean checkFront()
    {
        //if it is empty, the front is clear
        return getObjectsInRange(standingRange, Square.class).size() == 0 && (getOneObjectAtOffset(-standingRange, 0, Tower.class) == null || ((Tower)getOneObjectAtOffset(-standingRange, 0, Tower.class)).getCircle());
    }
    
    protected void createGhost()
    {
        health = -1000;
        dieSoundEffect.play();
        getWorld().addObject(new Ghost(true),getNormalX(), getNormalY());
    }
}
