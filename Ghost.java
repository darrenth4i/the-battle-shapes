import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ghost here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ghost extends Effect
{
    public Ghost(boolean isCircle)
    {
        super();
        imageScale = 0.75;
        if(isCircle)
        {
            setImage("images/Effects/ghosts/CircleGhost.png");
        }
        else
        {
            setImage("images/Effects/ghosts/SquareGhost.png");
        }
        getImage().scale((int)(getImage().getWidth()*imageScale),(int)(getImage().getHeight()*imageScale));
    }
    /**
     * Act - do whatever the Ghost wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getX() + (int)(Math.sin(getY()) * 5), getY() - 10);
        if (isAtEdge())
        {
            getWorld().removeObject(this); 
        }
    }
}
