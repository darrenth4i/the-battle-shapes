import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RangerExplosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RangerExplosion extends Effect
{
    public RangerExplosion()
    {
        super();
        imageScale = 3;
        loadAnimationFrames("rangerExplosion");
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
        //fade(animation.size()-1 - animationIndex, 1);
        animationIndex = animate(animation, animationIndex);
        animationTimer.mark();
    }
}
