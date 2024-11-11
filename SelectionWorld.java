import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class SelectionWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectionWorld extends World
{
    private String circleUnit1, circleUnit2, circleUnit3, circleUnit4, circleUnit5;
    private UnitSelector cU1, cU2, cU3, cU4, cU5;
    private String squareUnit1, squareUnit2, squareUnit3, squareUnit4, squareUnit5;
    private UnitSelector sU1, sU2, sU3, sU4, sU5;
    
    private PreSimTower sTower = new PreSimTower("Towers/Square/Offense.png", false, 500, 100, 0, 312);
    private PreSimTower cTower = new PreSimTower("Towers/Circle/Offense 1.png", true, 500, 100, 0, 712);
    
    private int sTowerHealth, sTowerLevel, sTowerType;
    private int cTowerHealth, cTowerLevel, cTowerType;
    
    private ConfirmButton confirmButton = new ConfirmButton(2);
    
    private StartButton startButton;
    
    private SongSelection song = new SongSelection("SongSelection/noSong.png");
    /**
     * Constructor for objects of class SelectionWorld.
     * 
     */
    public SelectionWorld()
    {    
        // Create a new world with 1024x700 cells with a cell size of 1x1 pixels.
        super(1024, 700, 1, false); 
        setBackground("images/Backgrounds/selection.png");
        
        cU1 = new UnitSelector(3);
        cU2 = new UnitSelector(3);
        cU3= new UnitSelector(3);
        cU4 = new UnitSelector(3);
        cU5 = new UnitSelector(3);
        
        sU1 = new UnitSelector(3);
        sU2 = new UnitSelector(3);
        sU3= new UnitSelector(3);
        sU4 = new UnitSelector(3);
        sU5 = new UnitSelector(3);

        
        addObject(cU1, 700, 200);
        addObject(cU2, 700, 270);
        addObject(cU3, 700, 340);
        addObject(cU4, 700, 410);
        addObject(cU5, 700, 480);
        
        addObject(sU1, 324, 200);
        addObject(sU2, 324, 270);
        addObject(sU3, 324, 340);
        addObject(sU4, 324, 410);
        addObject(sU5, 324, 480);
        
    
        addObject(song, 512, 0);
        
        addObject(confirmButton, 512, 640);
        addObject(new FullscreenTransition(), 512, 300);
    }
    
    public void confirm()
    {
        boolean eligible = false;
        circleUnit1 = cU1.getSelectedUnit();
        circleUnit2 = cU2.getSelectedUnit();
        circleUnit3 = cU3.getSelectedUnit();
        circleUnit4 = cU4.getSelectedUnit();
        circleUnit5 = cU5.getSelectedUnit();
        
        squareUnit1 = sU1.getSelectedUnit();
        squareUnit2 = sU2.getSelectedUnit();
        squareUnit3 = sU3.getSelectedUnit();
        squareUnit4 = sU4.getSelectedUnit();
        squareUnit5 = sU5.getSelectedUnit();
        
        if(circleUnit1 != null && circleUnit2 != null && circleUnit3 != null && circleUnit4 != null && circleUnit5 != null)
        {
            if(squareUnit1 != null && squareUnit2 != null && squareUnit3 != null && squareUnit4 != null && squareUnit5 != null)
            {
                ArrayList<String> circleTeam = new ArrayList();
                circleTeam.add(circleUnit1);
                circleTeam.add(circleUnit2);
                circleTeam.add(circleUnit3);
                circleTeam.add(circleUnit4);
                circleTeam.add(circleUnit5);
                
                ArrayList<String> squareTeam = new ArrayList();
                squareTeam.add(squareUnit1);
                squareTeam.add(squareUnit2);
                squareTeam.add(squareUnit3);
                squareTeam.add(squareUnit4);
                squareTeam.add(squareUnit5);
                for(int i = 0; i < circleTeam.size() - 1; i++)
                {
                    for(int j = 0; j < circleTeam.size() - 1; j++)
                    {
                        if(i > j && squareTeam.get(i).equals(squareTeam.get(j)))
                        {
                            confirmButton.wiggle();
                            return;
                        }
                    }
                }
                for(int i = 0; i < squareTeam.size() - 1; i++)
                {
                    for(int j = 0; j < squareTeam.size() - 1; j++)
                    {
                        if(i > j && squareTeam.get(i).equals(squareTeam.get(j)))
                        {
                            confirmButton.wiggle();
                            return;
                        }
                    }
                }
                eligible = true;
            }
        }
        if(eligible)
        {
            cU1.moveOffScreen();
            cU2.moveOffScreen();
            cU3.moveOffScreen();
            cU4.moveOffScreen();
            cU5.moveOffScreen();
            sU1.moveOffScreen();
            sU2.moveOffScreen();
            sU3.moveOffScreen();
            sU4.moveOffScreen();
            sU5.moveOffScreen();
            removeObject(confirmButton);
            
            addObject(startButton = new StartButton(1,  squareUnit1, squareUnit2, squareUnit3, squareUnit4, squareUnit5, circleUnit1, circleUnit2, circleUnit3, circleUnit4, circleUnit5), 512, 640);
            addObject(sTower, -200, 300);
            addObject(cTower, 1224, 300);
            setTower(sTower);
            setTower(cTower);
        }
    }
    
    public void setTower(PreSimTower tower)
    {
        startButton.setTower(tower);
    }
    
    public void stopAllSongs()
    {
        song.stopAllSongs();
    }
}
