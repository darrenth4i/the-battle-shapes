import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Square here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Square extends Units
{
    /**
     * Act - do whatever the Square wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    protected boolean checkFront()
    {
        //if it is empty, the front is clear
        return getOneObjectAtOffset(getImage().getWidth(), 0, Circle.class) == null;
    }
}
