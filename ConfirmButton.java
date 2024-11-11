import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ConfirmButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConfirmButton extends MenuButtons
{
    private GreenfootSound[] errorSounds;
    private int errorSoundIndex;
    
    private int wiggle = 0;
    public ConfirmButton(int type)
    {
        super(type);
        errorSounds = new GreenfootSound[10];
        for (int i = 0; i < clickSounds.length; i++)
        {
            errorSounds[i] = new GreenfootSound ("click.wav");
            errorSounds[i].setVolume (70);
        }
    }
    /**
     * Act - do whatever the ConfirmButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        if(wiggle > 0 && wiggle < 60)
        {
            wiggle();
        }
        else if(wiggle > 0)
        {
            wiggle = 0;
            setLocation(512, getY());
        }
    }
    
    public void buttonFunction()
    {
         getWorldOfType(SelectionWorld.class).confirm();
    }
    
    public void wiggle()
    {
        setLocation(getX() + (int)20 * Math.cos(wiggle), getY());
        wiggle++;
    }
}
