import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ConfirmButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConfirmButton extends MenuButtons
{   
    private int wiggle = 0;
    public ConfirmButton(int type)
    {
        super(type);
    }
    /**
     * Act - do whatever the ConfirmButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(wiggle > 0 && wiggle < 30)
        {
            wiggle();
        }
        else if(wiggle > 0)
        {
            wiggle = 0;
            setLocation(512, getY());
        }
        super.act();
    }
    
    public void buttonFunction()
    {
        wiggle = 1;
        setLocation(512, getY());
        getWorldOfType(SelectionWorld.class).confirm();
    }
    
    public void wiggle()
    {
        setLocation(getX() + (int)20 * Math.cos(wiggle), getY());
        wiggle++;
    }
    
    
}
