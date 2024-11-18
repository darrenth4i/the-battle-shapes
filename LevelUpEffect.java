import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelUpEffect extends Effect
{
    private int count;
    private int index;
    private GreenfootImage[] effect;
    public LevelUpEffect()
    {
        effect = new GreenfootImage[4];
        for(int i=0;i<4;i++)
        {
            effect[i] = new GreenfootImage("images/Effects/LevelUp/flame" + i +".png");
            effect[i].scale(65,65);
        }
    }
    public void act()
    {
        // Add your action code here.
        if(count%7 == 0)
        {
            index++;
        }
        if(count>140)
        {
            getWorld().removeObject(this);
            return;
        }
        if(index<4)
        {
            setImage(effect[index]);
        }
        
        count++;
    }
}
