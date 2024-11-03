import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cursor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cursor extends SuperSmoothMover
{
    //images for idle and held cursor frames
    private GreenfootImage cursorIdle;
    private GreenfootImage cursorHeld;
    
    //DEBUG PURPOSES ONLY, follow mouse movement
    private MouseInfo mouse;
    //variables to determine if left clicked
    private boolean clicked;
    private MyWorld world;
    
    public Cursor(){
        cursorIdle = new GreenfootImage("images/cursor.png");
        //Make held frame smaller, visual indicator of clicked
        cursorHeld = new GreenfootImage("images/cursorHeld.png");  
        
        world = (MyWorld)this.getWorld();
        clicked = false;
        setImage(cursorIdle);
    }
    
    /**
     * Act - do whatever the Cursor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //if mouse is held, set clicked to true, if let go, set to false
        if(Greenfoot.mousePressed(world)){
            clicked = true;
        }
        if(Greenfoot.mouseClicked(world)){
            clicked = false;
        }
        
        //DEBUG, remove mouse when done
        mouse = Greenfoot.getMouseInfo();
        followCursor(clicked, mouse);
    }
    
    /**
     * Method to follow cursor movement of mouse 
     */
    public void followCursor(boolean held, MouseInfo m){
        if(m != null){
            setLocation(m.getX(), m.getY());
            //Make image smaller to show it has been clicked
            if(held){
                setImage(cursorHeld);
            }
            else{
                setImage(cursorIdle);
            }
        }    
        
    }
}
