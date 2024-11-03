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
        List<Circle> potentialTargets = getObjectsInRange(range, Circle.class);
        if(potentialTargets.size() > 0)
        {
            Circle target = potentialTargets.get(0);
            for(int i = 0; i < potentialTargets.size(); i++)
            {
                if(potentialTargets.get(i).getNormalX() < target.getNormalX())
                {
                    target = potentialTargets.get(i);
                }
            }
            if(target != null)
            {
                System.out.println("S hit");
                target.hurt(atk);
            }
            else
            {
                System.out.println("Smiss");
            }
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
        return getObjectsInRange(range, Circle.class).size() == 0 && (getOneObjectAtOffset(range, 0, Tower.class) == null);
    }
}
