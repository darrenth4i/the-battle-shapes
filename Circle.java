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
        List<Square> potentialTargets = getObjectsInRange(range, Square.class);
        if(potentialTargets.size() > 0)
        {
            Square target = potentialTargets.get(0);
            for(int i = 0; i < potentialTargets.size(); i++)
            {
                if(potentialTargets.get(i).getNormalX() > target.getNormalX())
                {
                    target = potentialTargets.get(i);
                }
            }
            if(target != null)
            {
                //System.out.println("Chit");
                target.hurt(atk);
            }
            else
            {
                //System.out.println("Cmiss");
            }
        }
    }
    
    protected boolean checkFront()
    {
        //if it is empty, the front is clear
        return getObjectsInRange(range, Square.class).size() == 0 && (getOneObjectAtOffset(-range, 0, Tower.class) == null || ((Tower)getOneObjectAtOffset(-range, 0, Tower.class)).getCircle());
    }
    
    protected void createGhost()
    {
        getWorld().addObject(new Ghost(true),getNormalX(), getNormalY());
    }
}
