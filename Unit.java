import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import java.util.ArrayList;

/**
 * Superclass for every Unit in the Simulation
 * 
 * @author Justin Ye and Andy Li
 * @version Oct 31
 */
public abstract class Unit extends SuperSmoothMover
{
    // Health of the unit
    protected int health, maxHealth;
    // Shield from defense tower
    protected int shield;

    //Damage per hit
    protected int atk;
    //Time between attacks
    protected int atkCooldown;
    protected int timer;
    //Walk speeds
    protected double speed;
    //Number of times unit could be knocked back 
    protected int knockbacks;
    protected ArrayList<Integer> knockbackHealth = new ArrayList<Integer>();
    //Range which the unit can reach
    protected int range;
    //Range where unit starts attacking
    protected int standingRange;
    //frame of the attack 
    protected int attackFrame;
    //Percentage of the image size
    protected double imageScale;
    //boolean for when it is attacking
    protected boolean isAttacking;
    //boolean for when it is knocked back
    protected boolean isKnockedBack;
    protected int knockbackTimer; 

    protected int stage;

    protected int attackXOffset;
    protected int attackYOffset;

    protected int moveXOffset;
    protected int moveYOffset;
    protected boolean prepareMoveOffset;
    
    protected int totalYOffset;

    protected int standingXPos;
    protected int startYPos;
    
    protected int feetYPos;
    
    protected boolean justAddedToWorld = true;

    protected int walkIndex;
    protected ArrayList<GreenfootImage> walkAnim = new ArrayList<GreenfootImage>();
    protected int attackIndex;
    protected ArrayList<GreenfootImage> attackAnim = new ArrayList<GreenfootImage>();
    protected int idleIndex;
    protected ArrayList<GreenfootImage> idleAnim = new ArrayList<GreenfootImage>();
    protected int deathIndex;
    
    protected Shield barrier = new Shield(this);

    protected SimpleTimer animationTimer = new SimpleTimer();

    
    /**
     * Constructor for Units that can evolve.
     * 
     * @param stage - The form the unit will use.
     */
    public Unit(int stage)
    {   
        this();
        this.stage = stage;
    }
    
    /**
     * Constructor for Units.
     */
    public Unit()
    {   timer = 10000;
        animationTimer.mark();
        knockbackTimer = 0;
        isKnockedBack = false;
        isAttacking = false;
        atkCooldown = 60;
        attackFrame = 0; //Placeholder
    }

    /**
     * Sets variables that require position in the world
     */
    protected void addedToWorld(World world)
    {
        if(justAddedToWorld)
        {
            range = attackAnim.get(0).getWidth()/2 + 25;
            standingRange = attackAnim.get(0).getWidth()/2;
            startYPos = getY()+totalYOffset;
            setLocation(getX(), startYPos);
            maxHealth = health;
            //System.out.println(maxHealth);
            //System.out.println(maxHealth/knockbacks);
            //System.out.println(knockbacks);
            feetYPos = getY() + idleAnim.get(0).getHeight()/2;
            for(int i = 0; i < knockbacks; i++)
            {
                knockbackHealth.add((Integer)(maxHealth/knockbacks*i));
            }
            standingXPos = getX();
            justAddedToWorld = false;
        }
    }

    /**
     * Act - Chooses whether to move, attack, or stand still if on cooldown
     */
    public void act()
    {
        if(shield != 0)
        {
            getWorld().addObject(barrier, getX(), getY());
        }
        if(!isKnockedBack)
        {
            //dying code
            if (health <= 0)
            {
                createGhost();
                getWorld().removeObject(this);
                return;
            }
            //Attacking code
            if(atkCooldown <= timer && isAttacking)
            {
                if(!prepareMoveOffset)
                {
                    feetYPos = getY() + idleAnim.get(0).getHeight()/2;
                    setLocation(getX() - moveXOffset, getY() - moveYOffset);
                    startYPos -= moveYOffset;
                    prepareMoveOffset = true;
                }
                attackAnimation(attackFrame);
            }
            //idling code
            else if(timer < atkCooldown)
            {
                idleIndex = animate(idleAnim, idleIndex);
                timer++;
                if(!prepareMoveOffset)
                {
                    feetYPos = getY() + idleAnim.get(0).getHeight()/2;
                    setLocation(getX() - moveXOffset, getY() - moveYOffset);
                    startYPos -= moveYOffset;
                    prepareMoveOffset = true;
                }
            }
            //movement code
            else
            {
                if(prepareMoveOffset)
                {
                    feetYPos = getY() + idleAnim.get(0).getHeight()/2 - moveYOffset;
                    setLocation(getX() + moveXOffset, getY() + moveYOffset);
                    startYPos += moveYOffset;
                    prepareMoveOffset = false;
                }
                walkIndex = animate(walkAnim, walkIndex);
                walk();
            }
        }
        else
        {
            if(knockbackTimer < 10)
            {
                knockbackTimer++;
                knockback();
            }
            else
            {
                setRotation(0);
                isKnockedBack = false;
                knockbackTimer = 0;
                setLocation(getX(), startYPos-moveYOffset);
            }
        }
    }

    /**
     * Walks forward if nothing is obstructing movement
     */
    protected abstract void walk();

    /**
     * Checks the path of the unit for any obstructions
     * @return If the path is clear
     */
    protected abstract boolean checkFront();

    /**
     * Gets the name of the unit
     */
    protected abstract String getName();
    
    protected abstract void knockback();

    /**
     * Does damage to a target
     */
    protected abstract void attack();
    
    protected abstract void createGhost();

    /**
     * Animation for an attack
     */
    protected void attackAnimation(int attackFrame)
    {
        if(animationTimer.millisElapsed() < 20){
            return;
        }
        if(isAttacking)
        {
            if(attackIndex == attackAnim.size()) 
            {
                isAttacking = false;
                attackIndex = 0;
                timer = 0;
                return;
            }
            if(attackIndex == 0)
            {
                setLocation(getX() + attackXOffset, getY() + attackYOffset);
            }
            //Animation code here
            setImage(attackAnim.get(attackIndex));
            if(attackIndex == attackFrame)
            {
                attack();
            }
            attackIndex++;
            animationTimer.mark();
        }
    }
    
    protected boolean getAttacking()
    {
        return isAttacking;
    }
    
    protected boolean getKnockedback()
    {
        return isKnockedBack;
    }

    /**
     * Takes damage from attack
     * 
     * @param damage - The damage the unit will take
     */
    protected void hurt(int damage)
    {
        getWorld().addObject(new HitParticle(), getX()+Greenfoot.getRandomNumber(20)-10, getY()+Greenfoot.getRandomNumber(20)-10);

        if(shield < 1) //mitigates the damage dealt if shield is present
        {
            this.health -= damage;
        }
        else 
        {
            shield--;
            barrier.setSpawnTimer(0);
            barrier.animation();
            if(shield == 0)
            {
                getWorld().removeObject(barrier);
            }
        }

        if (knockbackHealth.size() > 0 && health <= knockbackHealth.get(knockbackHealth.size()-1).intValue()&&!isKnockedBack&&knockbackTimer==0)
        {
            //health = knockbackHealth.get(knockbackHealth.size()-1);
            isKnockedBack = true;
            isAttacking = false;
            attackIndex = 0;
            setImage(idleAnim.get(0));
            timer = 10000;
            knockback();
            knockbackHealth.remove(knockbackHealth.size()-1);
        }
    }

    /**
     * Recovers a certain amount of health up to max health
     * 
     * @param recover - The health the unit will heal
     */
    protected void heal(int recover)
    {
        getWorld().addObject(new HealEffect(), getNormalX(), feetYPos);
        this.health += recover;
        if(health > maxHealth)
        {
            health = maxHealth;
        }
    }

    /**
     * Protects the Unit from the next few attacks.
     * 
     * @param instance - Number of hits the shield will protect the Unit from
     */
    protected void shield(int instance)
    {
        barrier.setSpawnTimer(0);
        barrier.animation();
        shield += instance;
    }
    
    /**
     * Destroys the shield
     */
    protected void shieldBreak()
    {
        shield = 0;
    }
    
    /**
     * Gets the shield level
     * 
     * @return - Strength of the shield/hits the shield has before breaking
     */
    protected int getShield()
    {
        return shield;
    }

    /**
     * Gets the health percentage
     * 
     * @return - Percentage of health over total health
     */
    protected double getHealthDividedByMax()
    {
        return (double)health/maxHealth;
    }
    
    /**
     * Gets the idle sprite's height
     * 
     * @return - The expected height of the sprite
     */
    protected int getNormalHeight()
    {
        return idleAnim.get(0).getHeight();
    }

    /**
     * Simple Animations
     * 
     * @param animation - Sprites used for the animation
     * @param index - The current frame
     * @return index + 1
     */
    protected int animate(ArrayList<GreenfootImage> animation, int index)
    {
        if(animationTimer.millisElapsed() < 20){
            return index;
        }
        setImage(animation.get(index));
        index++;
        if(index > animation.size()-1)
        {
            index = 0;
        }
        animationTimer.mark();
        return index;
    }

    /**
     * Loads in every frame for every animation
     * 
     * @param path - The file path for the unit
     */
    protected void loadAnimationFrames(String path)
    {
        //Important: Ensure all folders are labelled with "attack", "move", and "stand"
        for(int i = 0; i < new File(path+"/attack").listFiles().length-1; i++)
        {
            attackAnim.add(new GreenfootImage(path + "/attack/" + i + ".png"));
            attackAnim.get(i).scale((int)(attackAnim.get(i).getWidth()*imageScale),(int)(attackAnim.get(i).getHeight()*imageScale));
        }
        for(int i = 0; i < new File(path+"/move").listFiles().length-1; i++)
        {
            walkAnim.add(new GreenfootImage(path + "/move/" + i + ".png"));
            walkAnim.get(i).scale((int)(walkAnim.get(i).getWidth()*imageScale),(int)(walkAnim.get(i).getHeight()*imageScale));
        }
        for(int i = 0; i < new File(path+"/stand").listFiles().length-1; i++)
        {
            idleAnim.add(new GreenfootImage(path + "/stand/" + i + ".png"));
            idleAnim.get(i).scale((int)(idleAnim.get(i).getWidth()*imageScale),(int)(idleAnim.get(i).getHeight()*imageScale));
        }
    }

    /**
     * Gets the X value that is expected
     * 
     * @return expected X position
     */
    public int getNormalX()
    {
        return standingXPos;
    }

    /**
     * Gets the Y value that is expected
     * 
     * @return expected Y position
     */
    public int getNormalY()
    {
        return startYPos;
    }
    
    public int getFeet()
    {
        return feetYPos;
    }
    
    public double getSpeed()
    {
        return speed;
    }
}
