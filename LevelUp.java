import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelUp here.
 * 
 * @author Brennan Lyn
 * @version (a version number or a date)
 */
public class LevelUp extends Effect
{
    private GreenfootImage image = new GreenfootImage("images/Effects/LevelUp/levelup.png");
    private int count;
    public LevelUp()
    {
        image.scale(110, 90);
        setImage(image);
    }
    /**
     * Act - do whatever the LevelUp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(count>135)
        {
            getWorld().removeObject(this);
        }
        count++;
    }
}
