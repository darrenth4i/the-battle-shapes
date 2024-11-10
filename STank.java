import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class STank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class STank extends Square
{
    /**
     * Act - do whatever the STank wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public STank(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            totalYOffset = -60;
            attackXOffset = 55;
            attackYOffset = 0;
            loadAnimationFrames("images/Units/STank/StageOne");
            
            knockbacks = 3;
            speed = 2;
            atk = 3;
            health = 90;
            attackFrame = 5;
        }
    }
}
