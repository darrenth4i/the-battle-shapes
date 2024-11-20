import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Notification here.
 * 
 * @author Darren Thai 
 * @version (a version number or a date)
 */
public class Notification extends PlayerUI
{
    private int direction; //+1 is right, -1 is left
    private int finalX; //final x coord the notification goes to
    private boolean circle;
    private double velocity;
    private GreenfootImage image;
    private Text message;
    private boolean stopped;
    private int removeCounter;
    
    public Notification(boolean cir, int x, String text){
        this(cir, x, text, false);
    }
    
    public Notification(boolean cir, int x, String text, boolean end){
        circle = cir;
        direction = cir ? -1 : 1;
        finalX = x;
        
        if(end){
            velocity = 35;
            message = new Text(text, 32);
        }
        else{
            velocity = 19; 
            message = new Text(text, 18);
        }
        
        removeCounter = 0;
        
        stopped = false;
        
        //set image based on team and based on if it's the event notif or end screen notif
        if(end){
            image = cir ? new GreenfootImage("images/UIElements/cNotificationEnd.png") : new GreenfootImage("images/UIElements/sNotificationEnd.png");
        }
        else{
            image = cir ? new GreenfootImage("images/UIElements/cNotification.png") : new GreenfootImage("images/UIElements/sNotification.png");
        }
        setImage(image);
    }
    
    /**
     * Set message object to follow Notification after it has been added
     * to the world
     */
    public void addedToWorld(World w){
        SimulationWorld world = (SimulationWorld)w;
        world.addObject(message, getX(), getY());
    }
    
    /**
     * Act - do whatever the Notification wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        scrollToEnd();
        //message follows notification
        message.setLocation(getX(), getY());
        
        if(stopped){
            removeCounter++;
            //Reverse movements
            if(removeCounter >= 300){
                velocity += 0.6;
                setLocation(getX() + (int)(-1 * direction * velocity), getY());
            }
            //remove after it is offscreen
            if((circle && getX() > 1300) || (!circle && getX() < -200)){
                getWorld().removeObject(this);
            }
        }
        
    }
    
    /**
     * Method to move Notification to the finalX position
     */
    public void scrollToEnd(){
        if(!stopped){
            if((circle && getX() > finalX) || (!circle && getX() < finalX)){
                setLocation(getX() + (int)(direction * velocity), getY());
                velocity -= 0.3;
            }
            else if((circle && getX() < finalX) || (!circle && getX() > finalX)){
                setLocation(finalX, getY());
                velocity = 1;
                stopped = true;
            } 
        }
    }
}
