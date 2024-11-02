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
    
    public Circle(int stage)
    {
        super(stage);
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
        Square target = getObjectsInRange(range, Square.class).size() != 0 ? getObjectsInRange(range, Square.class).get(0) : null;
        if(target != null)
        {
            System.out.println("Chit");
            target.hurt(atk);
        }
        else
        {
            System.out.println("Cmiss");
        }
    }
    
    protected boolean checkFront()
    {
        //if it is empty, the front is clear
        return getOneObjectAtOffset(-getImage().getWidth(), 0, Square.class) == null;
    }
}
