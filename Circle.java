import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Circle here.
 * 
 * @author (your name) 
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
    
    public Circle()
    {
        super();
        //Sets image size
        imageScale = 0.35;
        getImage().scale((int)(getImage().getWidth()*imageScale),(int)(getImage().getHeight()*imageScale));
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
        setLocation(getX()+10, getY()+(3*(knockbackTimer-5)));
        setRotation(20);
    }
    
    /**
     * Does damage to a target
     */
    protected void attack()
    {
        Square target = getObjectsInRange(getImage().getWidth()/2, Square.class).size() != 0 ? getObjectsInRange(getImage().getWidth()/2, Square.class).get(0) : null;
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
