import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class baseSwitcher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BaseSwitcher extends MenuButtons
{
    private PreSimTower origin;
    public BaseSwitcher(int type, PreSimTower origin)
    {
        super(type);
        this.origin = origin;
    }
    /**
     * Act - do whatever the baseSwitcher wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        buttonClick();
    }
    
    public void buttonFunction()
    {
        origin.changeType();
    }
}
