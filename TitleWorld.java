import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleWorld extends World
{

    /**
     * Constructor for objects of class TitleWorld.
     * 
     */
    public TitleWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 700, 1, false); 
        prepare();
        setBackground("images/Backgrounds/title.png");
        setPaintOrder(Effect.class, Title.class, MenuButtons.class, BlackBox.class);
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Title title = new Title("TheBattleShapes.png");
        addObject(title,512,1000);
        MenuButtons play = new MenuButtons(0);
        addObject(play,512,1500);
    }
}
