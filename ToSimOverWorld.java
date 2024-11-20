import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ToSimOverWorld here.
 * 
 * @author Andy Li
 * @version (a version number or a date)
 */
public class ToSimOverWorld extends MenuButtons
{
    private double velocity;
    private int blackBoxTransparency = 0;
    private BlackBox simOverOverlay1 = new BlackBox(blackBoxTransparency, 1024, 300);
    private BlackBox simOverOverlay2 = new BlackBox(blackBoxTransparency, 1024, 300);
    private boolean circleWinner;
    private boolean stopped;
    private int timer = 0;
    
    public ToSimOverWorld(int type, boolean circleWinner)
    {
        super(type);
        stopped = false;
        this.circleWinner = circleWinner;
        velocity = 25;
        setImage("UIElements/simOver.png");
    }
    
    public void addedToWorld(World world)
    {
        getWorld().addObject(simOverOverlay1, 512, 75);
        getWorld().addObject(simOverOverlay2, 512, 700-75);
    }
    
    /**
     * Act - do whatever the ToSimOverWorld wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        timer++;
        if(timer == 300)
        {
            FullscreenTransition trans = new FullscreenTransition(new SimOverWorld(circleWinner));
            getWorld().addObject(trans, 512, 1200);
        }
        if(buttonType == 12)
        {
            playButtonAnimation();
            if(blackBoxTransparency == 0)
            {
                blackBoxTransparency++;
            }
            else if (blackBoxTransparency < 200)
            {
                simOverOverlay1.changeTransparency(blackBoxTransparency);
                simOverOverlay2.changeTransparency(blackBoxTransparency);
                blackBoxTransparency += 5;
            }
            else if (blackBoxTransparency < 210)
            {
                getWorldOfType(SimulationWorld.class).removeUI();
                blackBoxTransparency += 10000;
                getWorld().addObject(simOverOverlay1, 512, 75);
                getWorld().addObject(simOverOverlay2, 512, 700-75);
                if(!circleWinner){
                    getWorld().addObject(new Notification(true, 512, "This is not over...", true), 1300, 565);
                }
                else{
                    getWorld().addObject(new Notification(false, 512, "I'll be back...", true), -200, 565);
                }
            }
        }
    }
    
    public void buttonFunction()
    {
    }
    
    
    public void playButtonAnimation()
    {
        if(getY() > 100)
        {
            setLocation(getX(), getY() - velocity);
            if(getY() < 900)
            {
                velocity -= 0.32;
            }
        }
        else if(getY() != 100)
        {
            setLocation(getX(), 100);
            stopped = true;
        }
    }
}
