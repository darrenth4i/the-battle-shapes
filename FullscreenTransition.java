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
    private World newWorld;
    public FullscreenTransition()
    {
        setImage("images/Effects/transition.png");
    }
    public FullscreenTransition(World newWorld)
    {
        this.newWorld = newWorld;
        isExiting = true;
        setImage("images/Effects/transition.png");
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
                Greenfoot.setWorld(newWorld);
            }
        }
        else
        {
            if(getY() < 2000)
            {
                setLocation(getX(), getY()+20);
            }
            else
            {
                getWorld().removeObject(this);
            }
        }
    }
}
