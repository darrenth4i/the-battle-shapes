import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DragonExplosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DragonExplosion extends Effect
{
    public DragonExplosion()
    {
        super();
        imageScale = 3;
        loadAnimationFrames("dragonExplosion");
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
