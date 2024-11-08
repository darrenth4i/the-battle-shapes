import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectionWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectionWorld extends World
{

    /**
     * Constructor for objects of class SelectionWorld.
     * 
     */
    public SelectionWorld()
    {    
        // Create a new world with 1024x700 cells with a cell size of 1x1 pixels.
        super(1024, 700, 1, false); 
        setBackground("images/Backgrounds/selection.png");
        addObject(new MenuButtons(1), 512, 600);
        addObject(new FullscreenTransition(), 512, 300);
    }
}
