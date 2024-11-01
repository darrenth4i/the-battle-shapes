import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fodder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CFodder extends Circle
{
    /**
     * Act - do whatever the Fodder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public CFodder()
    {
        speed = 2;
        atk = 1;
        health = 4;
        attackOffset = -50;
    }
}
