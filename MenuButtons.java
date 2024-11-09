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
    protected int width, height;
    private boolean buttonPressed;
    
    //Simulation World Preperation
    String sUnit1, sUnit2, sUnit3, sUnit4, sUnit5, cUnit1, cUnit2, cUnit3, cUnit4, cUnit5;
    
    //Unit select variables
    private boolean menuOpened;
    private VisualSpawnUnit sU1, sU2, sU3, sU4, sU5;
    private VisualSpawnUnit cU1, cU2, cU3, cU4, cU5;
    private String selectedUnit;
    private Text selectedCost;
    
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
                break;
            case 3:
                width = 90;
                height = 60;
                break;
        }
    }
    
    public MenuButtons(int type, String sUnit1, String sUnit2, String sUnit3, String sUnit4, String sUnit5, String cUnit1, String cUnit2, String cUnit3, String cUnit4, String cUnit5)
    {
        this(type);
        this.sUnit1 = sUnit1;
        this.sUnit2 = sUnit2;
        this.sUnit3 = sUnit3;
        this.sUnit4 = sUnit4;
        this.sUnit5 = sUnit5;
        this.cUnit1 = cUnit1;
        this.cUnit2 = cUnit2;
        this.cUnit3 = cUnit3;
        this.cUnit4 = cUnit4;
        this.cUnit5 = cUnit5;
    }
    
    public MenuButtons()
    {
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
                trans = new FullscreenTransition(new SimulationWorld(sUnit1, sUnit2, sUnit3, sUnit4, sUnit5, cUnit1, cUnit2, cUnit3, cUnit4, cUnit5));
                getWorld().addObject(trans, 512, 1200);
                break;
            case 2:
                getWorldOfType(SelectionWorld.class).confirm();
                break;
            case 3:
                if(getX() < 512 && !menuOpened)
                {
                    //square
                    menuOpened = true;
                    
                    sU1 = new VisualSpawnUnit("SFodder", 100, this);
                    sU2 = new VisualSpawnUnit("STank", 150, this);
                    sU3 = new VisualSpawnUnit("SWarrior", 300, this);
                    sU4 = new VisualSpawnUnit("SRanger", 400, this);
                    sU5 = new VisualSpawnUnit("SHealer", 400, this);
                    getWorld().addObject(sU1, getX()-200, getY() + 70);
                    getWorld().addObject(sU2, getX()-100, getY() + 70);
                    getWorld().addObject(sU3, getX(), getY() + 70);
                    getWorld().addObject(sU4, getX()+100, getY() + 70);
                    getWorld().addObject(sU5, getX()+200, getY() + 70);
                }
                else if(getX() > 512 && !menuOpened)
                {
                    //circle
                    menuOpened = true;
                    
                    cU1 = new VisualSpawnUnit("CFodder", 100, this);
                    cU2 = new VisualSpawnUnit("CTank", 150, this);
                    cU3 = new VisualSpawnUnit("CWarrior", 300, this);
                    cU4 = new VisualSpawnUnit("CRanger", 400, this);
                    cU5 = new VisualSpawnUnit("CHealer", 400, this);
                    getWorld().addObject(cU1, getX()-200, getY() + 70);
                    getWorld().addObject(cU2, getX()-100, getY() + 70);
                    getWorld().addObject(cU3, getX(), getY() + 70);
                    getWorld().addObject(cU4, getX()+100, getY() + 70);
                    getWorld().addObject(cU5, getX()+200, getY() + 70);
                }
                else if(menuOpened)
                {
                    closeMenu();
                }
                break;
        }
    }
    
    public void selectUnit(String unit, int unitCost)
    {
        if(buttonType == 3)
        {
            getWorld().removeObject(selectedCost);
            selectedUnit = unit;
            String filePath = "/UnitButtons/" + selectedUnit + "_1.png";
            setImage(filePath);
            getImage().scale(width, height);
            selectedCost = new Text("$" + unitCost, 18);
            getWorld().addObject(selectedCost, getX() - getImage().getWidth()/2 + 24, getY() + getImage().getHeight()/2 - 15);
        }
    }
    
    public void closeMenu()
    {
        menuOpened = false;
    }
    
    public boolean getMenu()
    {
        return menuOpened;
    }
    
    public String getSelectedUnit()
    {
        return selectedUnit;
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
