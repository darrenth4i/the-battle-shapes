import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Shockwave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shockwave extends OffensiveProjectileExplosion
{
    private boolean circle;
    private int level;
    private Unit target;
    private int index;
    private int damage;
    
    private GreenfootSound explode = new GreenfootSound("Shockwave.wav");
    
    public Shockwave(boolean circle, int level, int damage)
    {
        this.circle = circle;
        this.level = level;
        this.damage = damage;
        index = 0;
    }
    /**
     * Act - do whatever the Shockwave wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        //Creates more shockwaves and sounds until the level of the shockwave reaches 0
        if(level < 0)
        {
            getWorld().removeObject(this);
            return;
        }
        if(index == 0)
        {
            effect();
            explode.setVolume(70);
            explode.play();
        }
        else if(index == 3)
        {
            getWorld().addObject(new Shockwave(circle, level-1, damage), circle ? getX() - getImage().getWidth() : getX() + getImage().getWidth(), getY());
        }
        index++;
    }
    
    public void effect()
    {
        ArrayList<Unit> targetList;
        if(circle)
        {
            targetList = (ArrayList)getIntersectingObjects(Square.class);
        }
        else
        {
            targetList = (ArrayList)getIntersectingObjects(Circle.class);
        }
        System.out.println(targetList);
        for(Unit u : targetList)
        {
            u.hurt(level);
        }
        //target.hurt(damage);
    }
}
