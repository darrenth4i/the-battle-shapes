import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RailgunExplosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class RailgunExplosion extends Effect
{
    /**
     * Act - do whatever the RailgunExplosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //taken from https://orangemushroom.net/2018/06/16/kmst-ver-1-2-069-maplestory-black-mage-begins/wild-grenade-effect-explosion/
    public RailgunExplosion()
    {
        super();
        imageScale = 0.5;
        loadAnimationFrames("railgunExplosion");
        animationIndex = 0;
    }
    
    public void act()
    {
        if(animationTimer.millisElapsed() < 50){
            return;
        }
        if(animationIndex == animation.size()-1)
        {
            getWorld().removeObject(this);
        }
        animationIndex = animate(animation, animationIndex);
        animationTimer.mark();
    }
}
