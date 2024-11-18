import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CDragonHitbox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CDragonHitbox extends CDragon
{
    private CDragon origin;
    public CDragonHitbox(CDragon origin)
    {
        setImage("LiterallyNothing.png");
        this.origin = origin;
    }
    
    public void addedToWorld(World world)
    {

    }
    
    /**
     * Act - do whatever the CDragonHitbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(origin.getNormalX(), origin.getFeet());
        if(origin.getWorld() == null)
        {
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "CDragonHitbox";
    }
}
