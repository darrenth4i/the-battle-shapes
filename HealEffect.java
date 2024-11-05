import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealEffect extends Effect
{
    private int healParticleNumber = 10;
    private int actNum = 0;
    
    public HealEffect()
    {
        setImage("images/Effects/heal/HealEffect.png");
    }
    /**
     * Act - do whatever the HealEffect wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(healParticleNumber < 0)
        {
            getWorld().removeObject(this);
        }
        if(actNum < 10)
        {
            actNum++;
            return;
        }
        else
        {
            actNum = 0;
        }
        getWorld().addObject(new HealParticle(), getX() + 50 - healParticleNumber*(100/10), getY());
        healParticleNumber--;
    }
}
