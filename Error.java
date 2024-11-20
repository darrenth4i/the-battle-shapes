import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Error here.
 * 
 * @author Andy Li
 * @version (a version number or a date)
 */
public class Error extends Graphic
{
    private GreenfootSound errorSound;
    
    private int vanishTimer = 30;
    private int transparency = 255;
    public Error(String path)
    {
        super(path);
        errorSound = new GreenfootSound ("eror.wav");
        errorSound.setVolume(70);
        errorSound.play();
    }
    /**
     * Act - do whatever the Error wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        vanishTimer--;
        if(vanishTimer < 0)
        {
            setRotation(0);
            setLocation(getX(), getY() - 2);
            transparency-= 5;
            if(transparency < 0)
            {
                getWorld().removeObject(this);
                return;
            }
            getImage().setTransparency(transparency);
        }
        else
        {
            setRotation((int)((10/(6 - vanishTimer/6))*Math.cos(vanishTimer)));
        }
    }
}
