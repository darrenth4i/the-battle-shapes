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
    
    protected boolean checkFront()
    {
        //if it is empty, the front is clear
        return getOneObjectAtOffset(-getImage().getWidth(), 0, Square.class) == null;
    }
}
