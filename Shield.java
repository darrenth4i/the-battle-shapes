import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shield here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shield extends Effect
{
    private Unit origin;
    private int shieldPow = 0;
    private int spawnTimer = 0;
    private int animationTimer = 0;
    private int animationIndex = 0;
    private int transparency = 0;
    private boolean isCircle;
    
    public Shield(Unit origin)
    {
        this.origin = origin;
        setImage("images/Effects/shield/Shield.png");
        if(origin instanceof Square)
        {
            getImage().mirrorHorizontally();
            isCircle = false;
        }
        else
        {
            isCircle = true;
        }
        loadAnimationFrames("shield/breakAnim");
    }
    public void addedToWorld(World world)
    {
        shieldPow = origin.getShield();
        getImage().scale(40, origin.getNormalHeight());
        animation();
    }
    /**
     * Act - do whatever the Shield wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        animation();
        setLocation(isCircle ? origin.getNormalX() - 30 : origin.getNormalX() + 30, origin.getNormalY());
    }
    
    public void animation()
    {
        spawnTimer++;
        if(spawnTimer < 17)
        {
            transparency += 15;
        }
        else if(spawnTimer < 68)
        {
            transparency -= 5;
        }
        
        if(transparency > 255)
        {
            transparency = 255;
        }
        else if (transparency < 0)
        {
            transparency = 0;
        }
        
        getImage().setTransparency(transparency);
    }
    
    public void setTransparency(int trans)
    {
        transparency = trans;
        if(transparency > 255)
        {
            transparency = 255;
        }
        else if (transparency < 0)
        {
            transparency = 0;
        }        
    }
    
    public void setSpawnTimer(int timer)
    {
        spawnTimer = timer;
    }
}
