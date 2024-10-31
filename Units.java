import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Units here.
 * 
 * @author (Justin Ye and Andy Li) 
 * @version (a version number or a date)
 */
public abstract class Units extends SuperSmoothMover
{
    /**
     * Act - do whatever the Units wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected int speed;
    protected int atk;
    protected int health;
    protected boolean frontIsClear;
    
    public void act()
    {
        
    }
    
    public void walk(int direction)
    {
        if(frontIsClear)
        {
            move(direction*speed);
        }
    }
}
