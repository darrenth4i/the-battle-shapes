import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Graphic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Graphic extends UI
{
    protected GreenfootImage image;
    
    public Graphic(String path)
    {
        image = new GreenfootImage("images/" + path);
        setImage(image);
    }
    
    public Graphic()
    {
        image = new GreenfootImage("images/LiterallyNothing.png");
        setImage(image);
    }
    
    /**
     * Act - do whatever the Graphic wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
