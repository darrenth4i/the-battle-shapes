import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AISelector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AISelector extends MenuButtons
{
    private int ai;
    private GreenfootImage random = new GreenfootImage("images/UIElements/MenuButtons/10.png");
    private GreenfootImage smart = new GreenfootImage("images/UIElements/MenuButtons/11.png");
    public AISelector(int type)
    {
        super(type);
        ai = type; //10 - random, 11 - smart
        width = 220;
        height = 60;
        getImage().scale(width,height);
    }
    public void addedToWorld(World world)
    {
        if(ai == 10)
        {
            ai = 11;
        }
        else
        {
            ai = 10;
        }
    }
    /**
     * Act - do whatever the AISelector wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        buttonClick();
    }
    
    public void buttonFunction()
    {
        if(ai == 10)
        {
            setImage(random);
            ai = 11;
        }
        else
        {
            setImage(smart);
            ai = 10;
        }
    }
    
    public boolean isSmart()
    {
        return getImage().equals(smart);
    }
}
