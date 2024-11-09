import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PreSimTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PreSimTower extends Graphic
{
    //Tower vaiables "traits"
    protected int maxHealth = 1000, health = maxHealth;
    protected SuperStatBar healthBar = new SuperStatBar(maxHealth, health, this, 80, 10, 0, Color.GREEN, Color.GRAY);
    //Denotes team
    protected boolean circle;
    protected int towerRange;
    protected int fireInterval; //Higher rate means slower speed
    private int targetX;
    private int type;
    
    protected GreenfootImage towerImage;
    public PreSimTower(String path, boolean circle, int towerRange, int fireInterval, int type, int targetX)
    {
        super(path);
        this.circle = circle;
        this.towerRange = towerRange;
        this.fireInterval = fireInterval;
        this.targetX = targetX;
        this.type = type;
        
        //Sets tower image depending on side and type
        changeType();
    }
    
    public void addedToWorld(World world)
    {
        if(circle)
        {
            getWorld().addObject(new BaseSwitcher(4, this), getX() + 100, getY());
        }
        else
        {
            getWorld().addObject(new BaseSwitcher(5, this), getX() - 100, getY());
        }
    }
    
    public void moveToPosition(int x, int y, int speed)
    {
        double angle = Math.atan2(y - getY(), x - getX());
        
        int dx = (int) (Math.cos(angle) * speed);
        int dy = (int) (Math.sin(angle) * speed);

        setLocation(getX() + dx, getY() + dy);
        
        if(getX() < x + 20 && getX() > x - 20 && getY() < y + 20 && getY() > y - 20)
        {
            setLocation(x, y);
            if(circle)
            {
                getWorld().addObject(new BaseSwitcher(4, this), getX() + 100, getY());
            }
            else
            {
                getWorld().addObject(new BaseSwitcher(5, this), getX() - 100, getY());
            }
        }
    }
    
    
    
    
    /**
     * Act - do whatever the PreSimTower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(getX() != targetX)
        {
            moveToPosition(targetX, getY(), 10);
        }
    }
    
    public void changeType()
    {
        type ++;
        if(type > 2)
        {
            type = 0;
        }
        if(circle)
        {
            if(type == 0){
                towerImage = new GreenfootImage("Towers/Circle/Defense.png");
            }
            else if(type == 1){
                towerImage = new GreenfootImage("Towers/Circle/Offense.png");
            }
            else{
                towerImage = new GreenfootImage("Towers/Circle/Support.png");
            }
            setImage(towerImage);
        }
        else
        {
            if(type == 0){
                towerImage = new GreenfootImage("Towers/Square/Defense.png");
            }
            else if(type == 1){
                towerImage = new GreenfootImage("Towers/Square/Offense.png");
            }
            else{
                towerImage = new GreenfootImage("Towers/Square/Support.png");
            }
            setImage(towerImage);
        }
    }
}
