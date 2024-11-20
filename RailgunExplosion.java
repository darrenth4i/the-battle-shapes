import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RailgunExplosion here.
 * 
 * @author Justin Ye
 * @version (a version number or a date)
 */

public class RailgunExplosion extends Effect
{
    /**
     * Act - do whatever the RailgunExplosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
