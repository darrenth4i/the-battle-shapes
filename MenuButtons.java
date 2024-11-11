import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuButtons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class MenuButtons extends Menu
{
    protected int buttonType;
    protected int width, height;
    protected boolean buttonPressed;
    
    //Simulation World Preperation
    protected String sUnit1, sUnit2, sUnit3, sUnit4, sUnit5, cUnit1, cUnit2, cUnit3, cUnit4, cUnit5;
    protected int sTowerHealth, sTowerLevel, sTowerType, cTowerHealth, cTowerLevel, cTowerType;
    
    protected GreenfootSound[] clickSounds;
    protected int clickSoundIndex;
    
    public MenuButtons(int type)
    {
        this();
        buttonType = type;
        //0 = Play button
        setImage("images/UIElements/MenuButtons/" + buttonType + ".png");
        getImage().scale(getImage().getWidth()/2, getImage().getHeight()/2);
        width = getImage().getWidth();
        height = getImage().getHeight();
    }
    
    public MenuButtons()
    {
        clickSounds = new GreenfootSound[10];
        for (int i = 0; i < clickSounds.length; i++){
            clickSounds[i] = new GreenfootSound ("click.wav");
            clickSounds[i].setVolume (70);
        }
    }
    
    /**
     * Act - do whatever the MenuButtons wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        buttonClick();  
    }
    
    public void buttonClick()
    {
        if(Greenfoot.mousePressed(this))
        {
            buttonPressed = true;
            clickSounds[clickSoundIndex].play();
            clickSoundIndex++;
            if (clickSoundIndex >= clickSounds.length)
            {
                clickSoundIndex = 0;
            }
        }
        else if (Greenfoot.mouseClicked(this))
        {
            buttonPressed = false;
            buttonFunction();
        }
        else if(Greenfoot.getMouseInfo() != null && (Greenfoot.getMouseInfo().getX() < getX()-width/2 || Greenfoot.getMouseInfo().getX() > getX()+width/2 || Greenfoot.getMouseInfo().getY() < getY()-height/2 || Greenfoot.getMouseInfo().getY() > getY()+height/2))
        {
            buttonPressed = false;
        }
        if(buttonPressed)
        {
            getImage().scale(7*width/8, 7*height/8);
        }
        else
        {
            getImage().scale(width, height);
        }
    }
    
    public abstract void buttonFunction();
    

    /**
     * Set the location using exact coordinates.
     *
     * @param x the new x location
     * @param y the new y location
     */
    public void setLocation(double x, double y)
    {
        super.setLocation((int) (x + (Math.signum(x) * 0.5)), (int) (y + (Math.signum(y) * 0.5)));
    }
}
