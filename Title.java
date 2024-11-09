import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Title here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Title extends Graphic
{
    private double velocity = 27;
    private int passesPointNum = 0;
    
    public Title(String path)
    {
        super(path);
    }
    /**
     * Act - do whatever the Title wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(getY() > 200 && passesPointNum == 0)
        {
            setLocation(getX(), getY() - velocity);
            velocity -= 0.45;
        }
        else switch(passesPointNum)
        {
            case 0:
                //initially passes y point 200
                passesPointNum++;
                break;
            case 1:
                //while above and moving back down
                setLocation(getX(), getY() - velocity);
                velocity -= 0.45;
                if(getY() > 250)
                {
                    passesPointNum++;
                }
                break;
            case 2:
                //while below
                setLocation(getX(), getY() - velocity);
                velocity += 0.55;
                if(getY() < 200)
                {
                    passesPointNum++;
                }
                break;
            case 3:
                setLocation(getX(), 200);
                break;
        }
    }
    
    /**
     * Set the location using exact coordinates.
     *
     * @param x the new x location
     * @param y the new y location
     */
    public void setLocation(double x, double y)
    {
        super.setLocation((int) (x + (Math.signum(x) * 0.5)), (int) (y + (Math.signum(y) * 0.5)));
    }
}
