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
    private ArrayList<String> circleSelection = new ArrayList<String>(); // Create an ArrayList object
    private MenuButtons cU1, cU2, cU3, cU4, cU5;
    private String squareUnit1, squareUnit2, squareUnit3, squareUnit4, squareUnit5;
    private MenuButtons sU1, sU2, sU3, sU4, sU5;
    MenuButtons confirmButton = new MenuButtons(2);
    /**
     * Constructor for objects of class SelectionWorld.
     * 
     */
    public SelectionWorld()
    {    
        // Create a new world with 1024x700 cells with a cell size of 1x1 pixels.
        super(1024, 700, 1, false); 
        setBackground("images/Backgrounds/selection.png");
        
        cU1 = new MenuButtons(3);
        cU2 = new MenuButtons(3);
        cU3= new MenuButtons(3);
        cU4 = new MenuButtons(3);
        cU5 = new MenuButtons(3);
        
        sU1 = new MenuButtons(3);
        sU2 = new MenuButtons(3);
        sU3= new MenuButtons(3);
        sU4 = new MenuButtons(3);
        sU5 = new MenuButtons(3);

        
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
        
        addObject(confirmButton, 512, 640);
        addObject(new FullscreenTransition(), 512, 300);
    }
    
    public void confirm()
    {
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
                removeObject(confirmButton);
                addObject(new MenuButtons(1,  squareUnit1, squareUnit2, squareUnit3, squareUnit4, squareUnit5, circleUnit1, circleUnit2, circleUnit3, circleUnit4, circleUnit5), 512, 640);
            }
        }
    }
}
