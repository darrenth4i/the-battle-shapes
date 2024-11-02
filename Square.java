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
    
    public Square(int stage)
    {
        super(stage);
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
        Circle target = getObjectsInRange(range, Circle.class).size() != 0 ? getObjectsInRange(range, Circle.class).get(0) : null;
        if(target != null)
        {
            System.out.println("Shit");
            target.hurt(atk);
        }
        else
        
        {
            System.out.println("Smiss");
        }
    }
    
    protected void knockback()
    {
        setLocation(getX()-10, getY()+(3*(knockbackTimer-5)));
        setRotation(-20);
    }
    
    protected boolean checkFront()
    {
        //if it is empty, the front is clear
        return getOneObjectAtOffset(getImage().getWidth(), 0, Circle.class) == null;
    }
}
