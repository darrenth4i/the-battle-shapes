import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends MenuButtons
{
    private double velocity;
    private int blackBoxTransparency = 0;
    private BlackBox mainMenuBox = new BlackBox(blackBoxTransparency, 1024, 100);
    private String sUnit1, sUnit2, sUnit3, sUnit4, sUnit5, cUnit1, cUnit2, cUnit3, cUnit4, cUnit5;
    private boolean cIsSmart, sIsSmart;
    
    public StartButton(int type)
    {
        super(type);
        velocity = 17;
    }
    
    public StartButton(int type, String sUnit1, String sUnit2, String sUnit3, String sUnit4, String sUnit5, String cUnit1, String cUnit2, String cUnit3, String cUnit4, String cUnit5, boolean squareIsSmart, boolean circleIsSmart)
    {
        this(type);
        cIsSmart = circleIsSmart;
        sIsSmart = squareIsSmart;
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
    
    /**
     * Act - do whatever the StartButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        if(buttonType == 0)
        {
            playButtonAnimation();
            if(getY() == 450)
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
        }
    }
    
    public void buttonFunction()
    {
        switch(buttonType)
        {
            case 0:
                //title world to selection world
                FullscreenTransition trans = new FullscreenTransition(new SelectionWorld());
                getWorld().addObject(trans, 512, 1200);
                ((TitleWorld)getWorld()).stopMusic();
                break;
            case 1:
                //selection world to main world
                int[] sTowerVariables = {sTowerHealth, sTowerType, sTowerLevel};
                int[] cTowerVariables = {cTowerHealth, cTowerType, cTowerLevel};
                trans = new FullscreenTransition(new SimulationWorld(sUnit1, sUnit2, sUnit3, sUnit4, sUnit5, cUnit1, cUnit2, cUnit3, cUnit4, cUnit5, sTowerVariables, cTowerVariables, cIsSmart, sIsSmart));
                getWorldOfType(SelectionWorld.class).stopAllSongs();
                getWorld().addObject(trans, 512, 1200);
                break;
        }
    }
    
    public void playButtonAnimation()
    {
        if(getY() > 450)
        {
            setLocation(getX(), getY() - velocity);
            if(getY() < 900)
            {
                velocity -= 0.32;
            }
        }
        else if(getY() != 450)
        {
            setLocation(getX(), 450);
        }
    }
    
    public void setTower(PreSimTower tower)
    {
        if(!tower.getCircle())
        {
            sTowerHealth = tower.getHP();
            sTowerType = tower.getType();
            sTowerLevel = tower.getLevel();
        }
        else
        {
            cTowerHealth = tower.getHP();
            cTowerType = tower.getType();
            cTowerLevel = tower.getLevel();
        }
    }
}
