import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PreSimTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PreSimTower extends Graphic
{
    protected int maxHealth = 1000;
    //Denotes team
    protected boolean circle;
    protected int towerRange;
    protected int fireInterval; //Higher rate means slower speed
    private int targetX;
    private int type = 0;
    private int level = 0;
    
    private BaseStageSetter stageSetter = new BaseStageSetter(6, this);
    
    private GreenfootImage towerImage;
    public PreSimTower(String path, boolean circle, int towerRange, int fireInterval, int type, int targetX)
    {
        super(path);
        this.circle = circle;
        this.towerRange = towerRange;
        this.fireInterval = fireInterval;
        this.targetX = targetX;
        this.type = type;
        
        //Sets tower image depending on side and type
        updateImage();
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
            getWorld().addObject(stageSetter, getX(), getY() + 200);
            getWorld().addObject(new Slider(2000, 1000, this, 300, 20, -170), getX(), getY() + 300);
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
    
    public void changeLevel(int level)
    {
        this.level = level;
        updateImage();
        getWorldOfType(SelectionWorld.class).setTower(this);
    }
    
    public void changeType()
    {
        type ++;
        if(type > 2)
        {
            type = 0;
        }
        getWorldOfType(SelectionWorld.class).setTower(this);
        updateImage();
    }
    
    public void changeHP(int newHP)
    {
        if(newHP > 0)
        {
            maxHealth = newHP;
            getWorldOfType(SelectionWorld.class).setTower(this);
        }
    }
    
    public int getHP()
    {
        return maxHealth;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public int getType()
    {
         return type;
    }
    
    public boolean getCircle()
    {
        return circle;
    }
    
    public void updateImage()
    {
        if(circle)
        {
            if(type == 0){
                switch(level)
                {
                    case 0:
                    towerImage = new GreenfootImage("Towers/Circle/Defense 1.png");
                    break;
                    case 1:
                    towerImage = new GreenfootImage("Towers/Circle/Defense 2.png");
                    break;
                    case 2:
                    towerImage = new GreenfootImage("Towers/Circle/Defense 3.png");
                    break;
                }
            }
            else if(type == 1){
                switch(level)
                {
                    case 0:
                    towerImage = new GreenfootImage("Towers/Circle/Offense 1.png");
                    break;
                    case 1:
                    towerImage = new GreenfootImage("Towers/Circle/Offense 2.png");
                    break;
                    case 2:
                    towerImage = new GreenfootImage("Towers/Circle/Offense 3.png");
                    break;
                }
            }
            else{
                switch(level)
                {
                    case 0:
                    towerImage = new GreenfootImage("Towers/Circle/Support 1.png");
                    break;
                    case 1:
                    towerImage = new GreenfootImage("Towers/Circle/Support 2.png");
                    break;
                    case 2:
                    towerImage = new GreenfootImage("Towers/Circle/Support 3.png");
                    break;
                }
            }
            setImage(towerImage);
        }
        else
        {
            if(type == 0){
                switch(level)
                {
                    case 0:
                    towerImage = new GreenfootImage("Towers/Square/Defense 1.png");
                    break;
                    case 1:
                    towerImage = new GreenfootImage("Towers/Square/Defense 2.png");
                    break;
                    case 2:
                    towerImage = new GreenfootImage("Towers/Square/Defense 3.png");
                    break;
                }
            }
            else if(type == 1){
                switch(level)
                {
                    case 0:
                    towerImage = new GreenfootImage("Towers/Square/Offense 1.png");
                    break;
                    case 1:
                    towerImage = new GreenfootImage("Towers/Square/Offense 2.png");
                    break;
                    case 2:
                    towerImage = new GreenfootImage("Towers/Square/Offense 3.png");
                    break;
                }
            }
            else{
                switch(level)
                {
                    case 0:
                    towerImage = new GreenfootImage("Towers/Square/Support 1.png");
                    break;
                    case 1:
                    towerImage = new GreenfootImage("Towers/Square/Support 2.png");
                    break;
                    case 2:
                    towerImage = new GreenfootImage("Towers/Square/Support 3.png");
                    break;
                }
            }
            setImage(towerImage);
        }
    }
}
