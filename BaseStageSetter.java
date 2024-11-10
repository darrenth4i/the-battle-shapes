import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BaseStageSetter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BaseStageSetter extends MenuButtons
{
    private int stage = 0;
    private PreSimTower origin;
    public BaseStageSetter(int type, PreSimTower origin)
    {
        super(type);
        this.origin = origin;
    }
    /**
     * Act - do whatever the BaseStageSetter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        buttonClick();
    }
    
    public void buttonFunction()
    {
        stage++;
        if(stage > 2)
        {
            stage = 0;
        }
        origin.changeLevel(stage);
        setImage("images/UIElements/MenuButtons/"+ (6 + stage) +".png");
    }
}
