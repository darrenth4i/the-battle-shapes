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
    protected int health;
    protected int atk;
    protected int speed;
    protected boolean frontIsClear;
    protected boolean isCircle;
    
    public void act()
    {
        // Add your action code here.
    }
    
    public void walk()
    {
        if(frontIsClear)
        {
            move(speed);
        }
    }
}
