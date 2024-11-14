import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Square here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Square extends Unit
{
    /**
     * Act - do whatever the Square wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public Square(int stage)
    {
        super(stage);
        //Sets image size
        imageScale = 1;
        getImage().scale((int)(getImage().getWidth()*imageScale),(int)(getImage().getHeight()*imageScale));
    }
    
    public Square()
    {
        super();
    }
    
    protected void walk()
    {
        if(checkFront()&& !isAttacking)
        {
            //move(speed);
            standingXPos = getX() + (int) speed;
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
        List<Circle> potentialTargets = getObjectsInRange(range, Circle.class);
        List<Tower> towerTarget = getObjectsInRange(2 * range,Tower.class);
        Tower tower = towerTarget.size() > 0 ? towerTarget.get(0) : null;
        if(potentialTargets.size() > 0||towerTarget.size() > 0)
        {
            Circle target = potentialTargets.size() > 0 ? potentialTargets.get(0) : null;
            for(int i = 0; i < potentialTargets.size(); i++)
            {
                if(potentialTargets.get(i).getNormalX() < target.getNormalX())
                {
                    target = potentialTargets.get(i);
                }
            }
            if(tower != null && tower.getCircle() && (target == null || tower.getX() < target.getNormalX()))
            {
                tower = towerTarget.get(0);
                target = null;
            }
            if(target != null)
            {
                target.hurt(atk);
            }
            else if(tower != null)
            {
                tower.hurt(atk);
            }
        }
    }
    
    protected void knockback()
    {
        setLocation(getX()-10, getY()+(3*(knockbackTimer-5)));
        standingXPos = getX() - 10;
        setRotation(-20);
    }
    
    protected boolean checkFront()
    {
        //if it is empty, the front is clear
        return getObjectsInRange(standingRange, Circle.class).size() == 0 && (getOneObjectAtOffset(standingRange, 0, Tower.class) == null || !((Tower)getOneObjectAtOffset(standingRange, 0, Tower.class)).getCircle());
    }
    
    protected void createGhost()
    {
        getWorld().addObject(new Ghost(false),getNormalX(), getNormalY());
    }
}
