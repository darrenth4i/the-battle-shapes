import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Tower here.
 * 
 * @author Brennan Lyn 
 * @version (a version number or a date)
 */

public abstract class Tower extends Actor
{
    //Tower vaiables "traits"
    protected int maxHealth = 1000, health = maxHealth;
    protected SuperStatBar healthBar;
    //Denotes team
    protected boolean circle;
    
    protected int towerRange;
    protected int fireInterval; //Higher rate means slower speed
    protected int type;
    protected int level;
    protected Wallet myWallet;
    protected int event;
    protected ArrayList<Unit> targets;

    //Animation index for death
    protected int deathAnim = -1001;
    
    //Tower images - placeholders
    protected GreenfootImage towerImage;

    //Helper variables
    private double distance, nearestDistance, furthestDistance, lowestHealth;
    protected int count, randomEventCount;
    private ArrayList<Wallet> wallets;
    private int xOffset;
    
    private GreenfootSound[] damage;
    private int damageIndex;
    
    public Tower(boolean circle, int type, int level, int maxHP)
    {
        damage = new GreenfootSound[10];
        for (int i = 0; i < damage.length; i++){
            damage[i] = new GreenfootSound ("baseDamage.wav");
            damage[i].setVolume (80);
        }
        
        this.circle = circle;
        this.type = type;
        this.level = level;
        this.maxHealth = maxHP;
        health = maxHealth;
        updateLevel(level);
        healthBar= new SuperStatBar(maxHealth, health, this, getImage().getWidth() , 30, 200, circle ? new Color(255, 124, 124) : new Color(130, 124, 225), Color.WHITE ,false, Color.BLACK, 5);
    }
    public void setWallet()
    {
        wallets = (ArrayList<Wallet>)getWorld().getObjects(Wallet.class);
        if(wallets == null){
            return;
        }
        for(Wallet w : wallets){
            if((circle && w.getCircle()) || (!circle && !w.getCircle())){
                myWallet = w;
            }
        }
    }
    public boolean healthBelow(double below)
    {
        if((double)health/maxHealth<below)
        {
            return true;
        }
        return false;
    }
    public void conscription()
    {
        createNotification("Attack the Enemies!!!", "Push them back!!!");
        for(int i=0;i<(5+Greenfoot.getRandomNumber(9));i++)
        {
            if(circle)
            {
                getWorld().addObject(new CFodder(1), getWorld().getWidth()-Greenfoot.getRandomNumber(40), getY()+110+Greenfoot.getRandomNumber(20));
            }
            else
            {
                getWorld().addObject(new SFodder(1), 0-Greenfoot.getRandomNumber(40), getY()+110+Greenfoot.getRandomNumber(20));
            }
        }
    }
    public void meteorStorm()
    {
        createNotification("Scorch them!", "Eat fire!");
        targets = getEnemies();
        if(circle){
            xOffset = 200;
        }else{
            xOffset = -200;
        }
        for(Unit u : targets)
        {
            //Spawn a meteor above the target offscreen with an xoffset to angle the meteor
            getWorld().addObject(new Meteor(circle, u, 4), u.getX()+xOffset, -70);
        }
    }
    public void bloodSplatter()
    {
        createNotification("NONE. SHALL. PASS.", "REVERSAL!");
        targets = getEnemies();    
        for(Unit u : targets)
        {
            u.getWorld().addObject(new BloodSplat(), u.getX(), u.getY());
            u.getWorld().removeObject(u);
        }
    }
    public void randomEvent()
    {
        event = Greenfoot.getRandomNumber(2);
        //System.out.println(event);
        switch(event)
        {
            case 0: //Doubles the rate at which money increases
                createNotification("Extra Money!", "Double my Money and Give it to ME!");
                setWallet();
                myWallet.setEventMultiplier(2);
                break;
            case 1:
                conscription();
                break;    
        }
    }
    
    /**
     * Method to create a notification to alert
     * people watching that an event is happening
     * 
     */
    public void createNotification(String cText, String sText){
        if(circle){
            getWorld().addObject(new Notification(true, 826, cText), 1300, 535);
        }
        else{
            getWorld().addObject(new Notification(false, 198, sText), -200, 167);
        }
    }
    
    public void alternateRandomEvent()
    {
        event = Greenfoot.getRandomNumber(2);
        switch(event)
        {
            case 0:
                meteorStorm();
                break;
            case 1:
                bloodSplatter();
                break;
        }
    }
    
    public void updateLevel(int level)
    {
        //Sets tower stats depending on level
        this.level = level;
        switch(level)
        {
            case 0:
                towerRange = 200;
                fireInterval = 150;
                if(type==0)
                {
                    fireInterval = 300;
                }
                break;
            case 1:
                towerRange = 350;
                fireInterval = 100;
                if(type==0)
                {
                    fireInterval = 200;
                }
                break;
            case 2:
                towerRange = 500;
                fireInterval = 50;
                if(type==0)
                {
                    fireInterval = 100;
                }
                break;
        }
        //Sets tower image depending on side and type
        updateImage();
    }
    
    public void addedToWorld(World world)
    {
        getWorld().addObject(healthBar, getX(), getY() - getImage().getHeight() - 10);
    }
    
    /**
     * Act - do whatever the Tower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        count++;
        if(count%fireInterval == 0)
        {
            shoot();
        }
        if(health<0)
        {
            endSimulation();
        }
        if(healthBelow(0.5))
        {
            if(randomEventCount<1)
            {
                randomEvent();
                randomEventCount++;
            }
        }
        if(healthBelow(0.25))
        {
            if(randomEventCount<2)
            {
                alternateRandomEvent();
                randomEventCount++;
            }
        }
    }
    
    /** {NOT IMPLEMENTED YET}
     * The method that is run to end the simulation, resulting 
     * in either square's or circle's victory
     */
    protected void endSimulation()
    {
        if(deathAnim == -1001)
        {
            getWorldOfType(SimulationWorld.class).simulationOver(!circle);
        }
        setLocation(getX() + (int)(Math.sin(deathAnim) * 5), getY());
        deathAnim ++;
        if(deathAnim > 1000)
        {
            deathAnim = -1000;
        }
        
    }
    
    /**
     * Gets nearest instance of Unit class of opposite team
     * Used by Offense tower to target the frontmost unit to shoot at
     */
    protected Unit getNearestOppositeShape()
    {
        if(circle)
        {
            ArrayList<Square> nearSquares = (ArrayList<Square>)getObjectsInRange(towerRange, Square.class);
            Square nearestSquare = null;
            nearestDistance = -1;
            for(Square s : nearSquares)
            {
                distance = getDistance(s);
                if (distance < nearestDistance || nearestDistance == -1)
                {
                    nearestSquare = s;
                    nearestDistance = distance;
                }
            }
            return nearestSquare;
        }
        else
        {
            ArrayList<Circle> nearCircles = (ArrayList<Circle>)getObjectsInRange(towerRange, Circle.class);
            Circle nearestCircle = null;
            nearestDistance = -1;
            for(Circle c : nearCircles)
            {
                distance = getDistance(c);
                if (distance < nearestDistance || nearestDistance == -1)
                {
                    nearestCircle = c;
                    nearestDistance = distance;
                }
            }
            return nearestCircle;
        }
    }
    
    /**
     * Gets furthest instance of Unit class of the same shape
     * Used by Defense tower to target the frontmost unit of that "team"
     */
    protected Unit getFurthestSameShape()
    {
        if(!circle)
        {
            ArrayList<Square> nearSquares = (ArrayList<Square>)getObjectsInRange(towerRange, Square.class);
            Square furthestSquare = null;
            furthestDistance = -1;
            for(Square s : nearSquares)
            {
                distance = getDistance(s);
                if (distance > furthestDistance)
                {
                    furthestSquare = s;
                    furthestDistance = distance;
                }
            }
            return furthestSquare;
        }
        else
        {
            ArrayList<Circle> nearCircles = (ArrayList<Circle>)getObjectsInRange(towerRange, Circle.class);
            Circle furthestCircle = null;
            furthestDistance = -1;
            for(Circle c : nearCircles)
            {
                distance = getDistance(c);
                if (distance > furthestDistance)
                {
                    furthestCircle = c;
                    furthestDistance = distance;
                }
            }
            return furthestCircle;
        }
    }
    
    /**
     * Gets lowest health instance of Unit class of the same shape
     * Used by Support tower to target the lowest health unit of that "team"
     */
    protected Unit getLowestHealthSameShape()
    {
        if(!circle)
        {
            ArrayList<Square> nearSquares = (ArrayList<Square>)getObjectsInRange(towerRange, Square.class);
            Square lowHealthSquare = null;
            lowestHealth = 1.0;
            for(Square s : nearSquares)
            {
                if (s.getHealthDividedByMax()<=lowestHealth)
                {
                    lowHealthSquare = s;
                    lowestHealth = s.getHealthDividedByMax();
                }
            }
            return lowHealthSquare;
        }
        else
        {
            ArrayList<Circle> nearCircles = (ArrayList<Circle>)getObjectsInRange(towerRange, Circle.class);
            Circle lowHealthCircle = null;
            lowestHealth = 1.0;
            for(Circle c : nearCircles)
            {
                if (c.getHealthDividedByMax()<=lowestHealth)
                {
                    lowHealthCircle = c;
                    lowestHealth = c.getHealthDividedByMax();
                }
            }
            return lowHealthCircle;
        }
    }
    
    public ArrayList<Unit> getEnemies()
    {
        ArrayList<Unit> enemies;
        if(!circle)
        {
            enemies = (ArrayList<Unit>)(ArrayList<?>)getWorld().getObjects(Circle.class);
        }
        else
        {
            enemies = (ArrayList<Unit>)(ArrayList<?>)getWorld().getObjects(Square.class);
        }
        return enemies;
    }
    
    /**
     * Abstract method overrided by all subclasses
     * to shoot their class specific projectile
     */
    protected abstract void shoot();
    
    /**
     * Used to find distance from an actor
     */
    public double getDistance(Actor actor)
    {
        return Math.hypot(actor.getX() - getX(), actor.getY() - getY());
    }
    
    /**
     * Getter method to return boolean "circle"
     * which is true when the tower team is circle, false
     * if square
     */
    public boolean getCircle()
    {
        return circle;
    }
    
    public void hurt(int damage)
    {
        playSound();
        health-=damage;
        healthBar.update(health);
        getWorld().addObject(new HitParticle(), getX()+Greenfoot.getRandomNumber(getImage().getWidth())-getImage().getWidth()/2, getY()+Greenfoot.getRandomNumber(getImage().getHeight()/2));
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
    
    public void playSound()
    {
        damage[damageIndex].play();
        damageIndex++;
        if (damageIndex >= damage.length)
        {
            damageIndex = 0;
        }
    }
    
    public double getHealthPercentage()
    {
        return (double)health/maxHealth;
    }
    
    public int getHealth(){
        return health;
    }
}
