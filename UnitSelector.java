import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UnitSelector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UnitSelector extends MenuButtons
{
    //Unit select variables
    private boolean menuOpened;
    private VisualSpawnUnit sU1, sU2, sU3, sU4, sU5;
    private VisualSpawnUnit cU1, cU2, cU3, cU4, cU5;
    private String selectedUnit;
    private Text selectedCost;
    private boolean confirmed = false;
    private boolean isFinal;
    
    public UnitSelector(int type, boolean isFinal)
    {
        super(type);
        this.isFinal = isFinal;
        width = 90;
        height = 60;
    }
    
    public UnitSelector(int type)
    {
        this(type, false);
    }
    
    /**
     * Act - do whatever the UnitSelector wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        if(Greenfoot.getMouseInfo() != null && Greenfoot.mouseClicked(null))
        {
            if((getX() < 512 && Greenfoot.getMouseInfo().getX() > 512) || (getX() > 512 && Greenfoot.getMouseInfo().getX() < 512))
            {
                closeMenu();
            }
        }
        if(confirmed)
        {
            moveOffScreen();
            return;
        }
    }
    
    public void buttonFunction()
    {
        //Selection Buttons
        if(getX() < 512 && !menuOpened)
        {
            //square
            menuOpened = true;
            if(!isFinal)
            {
                sU1 = new VisualSpawnUnit("SFodder", 50, this);
                sU2 = new VisualSpawnUnit("STank", 100, this);
                sU3 = new VisualSpawnUnit("SWarrior", 200, this);
                sU4 = new VisualSpawnUnit("SRanger", 300, this);
                sU5 = new VisualSpawnUnit("SHealer", 300, this);
                getWorld().addObject(sU1, getX()-200, getY());
                getWorld().addObject(sU2, getX()-100, getY());
                getWorld().addObject(sU3, getX(), getY());
                getWorld().addObject(sU4, getX()+100, getY());
                getWorld().addObject(sU5, getX()+200, getY());
            }
            else
            {
                cU1 = new VisualSpawnUnit("SReaper", 600, this);
                cU2 = new VisualSpawnUnit("SRailgun", 2500, this);
                cU3 = new VisualSpawnUnit("STesseract", 300, this);
                getWorld().addObject(cU1, getX()-100, getY() + 70);
                getWorld().addObject(cU2, getX(), getY() + 70);
                getWorld().addObject(cU3, getX()+100, getY() + 70);
            }
        }
        else if(getX() > 512 && !menuOpened)
        {
            //circle
            menuOpened = true;
            if(!isFinal)
            {
                cU1 = new VisualSpawnUnit("CFodder", 50, this);
                cU2 = new VisualSpawnUnit("CTank", 100, this);
                cU3 = new VisualSpawnUnit("CWarrior", 200, this);
                cU4 = new VisualSpawnUnit("CRanger", 300, this);
                cU5 = new VisualSpawnUnit("CHealer", 300, this);
                getWorld().addObject(cU1, getX()-200, getY());
                getWorld().addObject(cU2, getX()-100, getY());
                getWorld().addObject(cU3, getX(), getY());
                getWorld().addObject(cU4, getX()+100, getY());
                getWorld().addObject(cU5, getX()+200, getY());
            }
            else
            {
                cU1 = new VisualSpawnUnit("CDragon", 2000, this);
                cU2 = new VisualSpawnUnit("CBomb", 150, this);
                cU3 = new VisualSpawnUnit("CCyclone", 300, this);
                getWorld().addObject(cU1, getX()-100, getY() + 70);
                getWorld().addObject(cU2, getX(), getY() + 70);
                getWorld().addObject(cU3, getX()+100, getY() + 70);
            }
        }
        else if(menuOpened)
        {
            closeMenu();
        }
    }
    
    public void selectUnit(String unit, int unitCost)
    {
        if(buttonType == 3)
        {
            getWorld().removeObject(selectedCost);
            selectedUnit = unit;
            String filePath = !isFinal ? "/UnitButtons/" + selectedUnit + "_1.png" : "/UnitButtons/" + selectedUnit + ".png";
            setImage(filePath);
            getImage().scale(width, height);
            selectedCost = new Text("$" + unitCost, 18);
            getWorld().addObject(selectedCost, getX() - getImage().getWidth()/2 + 24, getY() + getImage().getHeight()/2 - 15);
        }
    }
    
    public boolean getFinal()
    {
        return isFinal;
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
    
    public void moveOffScreen()
    {
        getWorld().removeObject(selectedCost);
        closeMenu();
        confirmed = true;
        if(getX() < 512)
        {
            setLocation(getX() - 10, getY());
            if(getX() < -500)
            {
                getWorld().removeObject(this);
                return;
            }
        }
        else
        {
            setLocation(getX() + 10, getY());
            if(getX() > 2000)
            {
                getWorld().removeObject(this);
            }
        }
    }
}
