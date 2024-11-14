import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BloodSplat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BloodSplat extends Effect
{
    /**
     * Act - do whatever the BloodSplat wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int count;
    private int index;
    private GreenfootImage[] splat;
    public BloodSplat()
    {
        splat = new GreenfootImage[7];
        for(int i=0;i<7;i++)
        {
            splat[i] = new GreenfootImage("images/Effects/BloodSplats/bloodsplat" + i +".png");
            splat[i].scale(95,95);
        }
    }
    public void act()
    {
        // Add your action code here.
        if(count%8 == 0)
        {
            index++;
        }
        if(index<7)
        {
            setImage(splat[index]);
        }
        if(index > 6)
        {
            getWorld().removeObject(this);
        }
        count++;
    }
}
