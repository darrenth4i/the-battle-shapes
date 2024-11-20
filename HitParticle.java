import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HitParticle here.
 * 
 * @author Andy Li 
 * @version (a version number or a date)
 */
public class HitParticle extends Effect
{
    
    
    public HitParticle()
    {
        super();
        imageScale = 1.5;
        loadAnimationFrames("hit");
        animationIndex = 0;
    }
    
    /**
     * Act - do whatever the HitParticle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act()
    {
        if(animationTimer.millisElapsed() < 30){
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
