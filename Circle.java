import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Circle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Circle extends Units
{
    /**
     * Act - do whatever the Circle wants to do. This method is called whenever
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
            move(-speed);
        }
        else
        {
            isAttacking = true;
        }
    }
    
    protected void knockback()
    {
        setLocation(getX()+50, getY());
        //make smoother later
        knockbackHealth.remove(0);
    }
    
    /**
     * Does damage to a target
     */
    protected void attack()
    {
        Square target = getObjectsInRange(getImage().getWidth()+30, Square.class).size() != 0 ? getObjectsInRange(getImage().getWidth()+30, Square.class).get(0) : null;
        if(target != null)
        {
            target.hurt(atk);
        }
    }
    
    protected boolean checkFront()
    {
        //if it is empty, the front is clear
        return getOneObjectAtOffset(-getImage().getWidth(), 0, Square.class) == null;
    }
}
