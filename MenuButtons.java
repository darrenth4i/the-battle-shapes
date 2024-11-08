import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuButtons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuButtons extends Menu
{
    private int buttonType;
    private double velocity;
    private int width, height;
    private boolean buttonPressed;
    
    public MenuButtons(int type)
    {
        buttonType = type;
        //0 = Play button
        setImage("images/MenuButtons/" + buttonType + ".png");
        getImage().scale(getImage().getWidth()/2, getImage().getHeight()/2);
        width = getImage().getWidth();
        height = getImage().getHeight();
        switch(buttonType)
        {
            case 0:
                velocity = 32;
        }
    }

    /**
     * Act - do whatever the MenuButtons wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        switch(buttonType)
        {
            case 0:
            playButtonAnimation();
            if(getY() == 400)
            {
                if(blackBoxTransparency == 0)
                {
                    getWorld().addObject(mainMenuBox, getX(), getY());
                    blackBoxTransparency++;
                }
                else if (blackBoxTransparency < 150)
                {
                    mainMenuBox.changeTransparency(blackBoxTransparency);
                    blackBoxTransparency+= 10;
                }
            }
            break;
            case 1:
            break;
        }
        buttonClick();
        
    }
    
    public void buttonClick()
    {
        if(Greenfoot.mousePressed(this))
        {
            buttonPressed = true;
        }
        else if (Greenfoot.mouseClicked(this))
        {
            buttonPressed = false;
            buttonFunction();
        }
        else if(Greenfoot.getMouseInfo() != null && (Greenfoot.getMouseInfo().getX() < getX()-width/2 || Greenfoot.getMouseInfo().getX() > getX()+width/2 || Greenfoot.getMouseInfo().getY() < getY()-height/2 || Greenfoot.getMouseInfo().getY() > getY()+height/2))
        {
            buttonPressed = false;
        }
        if(buttonPressed)
        {
            getImage().scale(7*width/8, 7*height/8);
        }
        else
        {
            getImage().scale(width, height);
        }
    }
    
    public void buttonFunction()
    {
        switch(buttonType)
        {
            case 0:
                FullscreenTransition trans = new FullscreenTransition(new SelectionWorld());
                getWorld().addObject(trans, 512, 1200);
                break;
            case 1:
                trans = new FullscreenTransition(new MyWorld());
                getWorld().addObject(trans, 512, 1200);
                break;
        }
    }
    
    public void playButtonAnimation()
    {
        if(getY() > 400)
        {
            System.out.println(getY());
            setLocation(getX(), getY() - velocity);
            velocity -= 0.45;
        }
        else if(getY() != 400)
        {
            setLocation(getX(), 400);
            
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
