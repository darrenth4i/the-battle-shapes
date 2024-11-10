import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Slider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slider extends SuperStatBar
{
    private GreenfootSound[] clickSounds;
    private int clickSoundIndex;
    private PreSimTower towerOwner;
    
    public Slider (int maxVal, int currVal, Actor owner, int width, int height, int offset)
    {
        super (maxVal, currVal, owner, width, height, offset, Color.GREEN, Color.RED);
        if(target instanceof PreSimTower)
        {
            towerOwner = (PreSimTower)target;
        }
        clickSounds = new GreenfootSound[10];
        for (int i = 0; i < clickSounds.length; i++){
            clickSounds[i] = new GreenfootSound ("click.wav");
            clickSounds[i].setVolume (70);
        }
    }
    /**
     * Act - do whatever the Slider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        mouseSet();
    }
    
    public void mouseSet()
    {
        if(Greenfoot.getMouseInfo() != null && (Greenfoot.mouseDragged(this) || Greenfoot.mousePressed(this)) && (Greenfoot.getMouseInfo().getX() > getX()-width/2 && Greenfoot.getMouseInfo().getX() < getX()+width/2 && Greenfoot.getMouseInfo().getY() > getY()-height/2 && Greenfoot.getMouseInfo().getY() < getY()+height/2))
        {
            update(10 * (int)Math.ceil( ( (double)maxVals[0] / ( ( (double)(getX()+width/2) - (double)(getX()-width/2) ) ) * ( (double)Greenfoot.getMouseInfo().getX() - (double)(getX()-width/2) ) ) / 10 ) );
            clickSounds[clickSoundIndex].play();
            clickSoundIndex++;
            if (clickSoundIndex >= clickSounds.length)
            {
                clickSoundIndex = 0;
            }
            if(towerOwner != null)
            {
                towerOwner.changeHP(currVals[0]);
            }
        }
    }
}
