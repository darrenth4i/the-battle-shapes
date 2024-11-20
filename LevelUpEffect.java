import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelUp here.
 * 
 * @author Brennan Lyn
 * @version (a version number or a date)
 */
public class LevelUpEffect extends Effect
{
    protected int count;
    private int index;
    private GreenfootImage[] effect;
    private boolean circle;
    
    public LevelUpEffect(boolean circle)
    {
        this.circle = circle;
        effect = new GreenfootImage[4];
        for(int i=0;i<4;i++)
        {
            effect[i] = new GreenfootImage("images/Effects/LevelUp/flame" + i +".png");
            effect[i].scale(275,185);
        }
        effectSound = new GreenfootSound("sounds/Effects/level-up.wav");
    }
    public void addedToWorld(World world)
    {   
        //creates a LevelUp object depending on team when added to world, otherwise for square, it is placed offscreen
        if(circle)
        {
            getWorld().addObject(new LevelUp(), getX(),getY()-60);
        } else
        {
            getWorld().addObject(new LevelUp(), getX(),getY()+80);
        }
        
    }
    public void act()
    {
        if(count%2 == 0)
        {
            index++;
            if(index>3)
            {
                index=0;
            }
            setImage(effect[index]);
        }
        if(count>140)
        {
            getWorld().removeObject(this);
            return;
        }
        count++;
        effectSound.play();
    }
}
