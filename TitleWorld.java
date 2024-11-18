import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleWorld extends World
{
    private GreenfootSound mainTheme = new GreenfootSound("sounds/mainTheme.mp3");
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
        addObject(new FullscreenTransition(), 512, 300);
    }
    /**
     * Constructor for objects of class TitleWorld.
     * 
     */
    public TitleWorld(boolean thisIsOnlyHereToMakeSimulationRestartPlayMusic)
    {    
        this();
        started();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Title title = new Title("TheBattleShapes.png");
        addObject(title,512,1000);
        MenuButtons play = new StartButton(0);
        addObject(play,512,3000);
    }
    
    public void started()
    {
        mainTheme.setVolume(60);
        mainTheme.playLoop();
    }
    
    public void stopped()
    {
        mainTheme.pause();
    }
    
    public void stopMusic()
    {
        mainTheme.stop();
    }
}
