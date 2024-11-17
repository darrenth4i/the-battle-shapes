import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ReturnButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ReturnButton extends MenuButtons
{
    
    public ReturnButton(int type)
    {
        super(type);
    }
    
    /**
     * Act - do whatever the ReturnButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public void buttonFunction()
    {
        //SimOverWorld to title world
        FullscreenTransition trans = new FullscreenTransition(new TitleWorld());
        getWorld().addObject(trans, 512, 1200);
    }
}
