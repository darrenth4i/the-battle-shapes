import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Notification here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Notification extends PlayerUI
{
    private int direction; //+1 is down, -1 is up
    private int endX; //final x coord the notification goes to
    private boolean circle;
    private double velocity;
    private GreenfootImage image;
    
    public Notification(boolean cir, int x){
        circle = cir;
        direction = cir ? -1 : 1;
        endX = x;
        
        velocity = 19;
        
        //set image based on team
        image = cir ? new GreenfootImage("images/UIElements/cNotification.png") : new GreenfootImage("images/UIElements/sNotification.png");
        setImage(image);
    }
    
    /**
     * Act - do whatever the Notification wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        scrollToEnd();
    }
    
    /**
     * Method to move Notification to the endX position
     */
    public void scrollToEnd(){
        if((circle && getX() > endX) || (!circle && getX() < endX)){
            setLocation(getX() + (int)(direction * velocity), getY());
            velocity -= 0.3;
        }
        else if((circle && getX() < endX) || (!circle && getX() > endX)){
            setLocation(endX, getY());
        }
    }
}
