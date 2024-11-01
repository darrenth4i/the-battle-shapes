import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SWarrior here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SWarrior extends Square
{
    /**
     * Act - do whatever the SWarrior wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public SWarrior()
    {
        super();
        atkCooldown = 30;
        knockbacks = 2;
        speed = 3;
        atk = 8;
        health = 12;
    }
}
