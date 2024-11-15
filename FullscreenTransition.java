import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FullscreenTransition here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FullscreenTransition extends Effect
{
    private boolean isExiting = false;
    private boolean isDone;
    private boolean isFake;
    private World newWorld;
    public FullscreenTransition()
    {
        this(false);
    }
    public FullscreenTransition(World newWorld)
    {
        this.newWorld = newWorld;
        isExiting = true;
        isFake = false;
        setImage("images/Effects/transition.png");
    }
    public FullscreenTransition(boolean fakeTransistion)
    {
        setImage("images/Effects/transition.png");
        isDone = false;
        isExiting = fakeTransistion;
        isFake = fakeTransistion;
    }
    /**
     * Act - do whatever the FullscreenTransition wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(isExiting)
        {
            if(getY() > 300)
            {
                setLocation(getX(), getY()-20);
            }
            else
            {
                if(!isFake)
                {
                    Greenfoot.setWorld(newWorld);
                }
                else
                {
                    isExiting = false;
                }
            }
        }
        else
        {
            if(getY() < 1050)
            {
                setLocation(getX(), getY()+20);
            }
            else
            {
                isDone = true;
                getWorld().removeObject(this);
            }
        }
    }
    
    public boolean getIsDone()
    {
        return isDone;
    }
    
    public boolean getisExiting()
    {
        return isExiting;
    }
}
