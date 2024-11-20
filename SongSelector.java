import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SongSelector here.
 * 
 * @author Andy Li
 * @version (a version number or a date)
 */
public class SongSelector extends MenuButtons
{
    //Song select variables
    private SongSelection origin;
    
    public SongSelector(int type, SongSelection origin)
    {
        super(type);
        this.origin = origin;
    }
    /**
     * Act - do whatever the SongSelector wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    public void buttonFunction()
    {
        switch(buttonType)
        {
            case 4:
                //next song
                origin.nextSong();
                break;
            case 5:
                //previous song
                origin.previousSong();
                break;
        }
    }
}
