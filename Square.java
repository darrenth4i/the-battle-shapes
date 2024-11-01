import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    
    public Square()
    {
        //Sets image size
        imageScale = 1;
        getImage().scale((int)(getImage().getWidth()*imageScale),(int)(getImage().getHeight()*imageScale));
    }
    
    protected void walk()
    {
        if(checkFront()&& !isAttacking)
        {
            move(speed);
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
        Circle target = getObjectsInRange(getImage().getWidth()+30, Circle.class).size() != 0 ? getObjectsInRange(getImage().getWidth()+30, Circle.class).get(0) : null;
        if(target != null)
        {
            target.hurt(atk);
        }
    }
    
    protected void knockback()
    {
        setLocation(getX()-30, getY());
        //make smoother later
        knockbackHealth.remove(knockbackHealth.size()-1);
    }
    
    protected boolean checkFront()
    {
        //if it is empty, the front is clear
        return getOneObjectAtOffset(getImage().getWidth(), 0, Circle.class) == null;
    }
}
