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
    protected static int atkCooldown;
    protected int timer;
    //Walk speeds
    protected int speed;
    //Number of times unit could be knocked back 
    protected int knockbacks;
    protected ArrayList<Integer> knockbackHealth = new ArrayList<Integer>();
    //Range which the unit can reach
    protected int range;
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

    protected int standingXPos;
    protected int startYPos;

    protected int walkIndex;
    protected ArrayList<GreenfootImage> walkAnim = new ArrayList<GreenfootImage>();
    protected int attackIndex;
    protected ArrayList<GreenfootImage> attackAnim = new ArrayList<GreenfootImage>();
    protected int idleIndex;
    protected ArrayList<GreenfootImage> idleAnim = new ArrayList<GreenfootImage>();
    protected int deathIndex;
    protected GreenfootImage knockback;

    protected SimpleTimer animationTimer = new SimpleTimer();

    public Unit(int stage)
    {   
        this.stage = stage;
        animationTimer.mark();
        knockbackTimer = 0;
        isKnockedBack = false;
        isAttacking = false;
        atkCooldown = 60;
        attackFrame = 0; //Placeholder
    }

    protected void addedToWorld(World world)
    {
        range = attackAnim.get(0).getWidth()/2;
        startYPos = getY();
        maxHealth = health;
        System.out.println(maxHealth);
        System.out.println(maxHealth/knockbacks);
        System.out.println(knockbacks);
        for(int i = 0; i < knockbacks; i++)
        {
            knockbackHealth.add((Integer)(maxHealth/knockbacks*i));
        }
    }

    /**
     * Act - Chooses whether to move, attack, or stand still if on cooldown
     */
    public void act()
    {
        if(animationTimer.millisElapsed() < 18){
            return;
        }
        if(!isKnockedBack)
        {
            if(atkCooldown <= timer && isAttacking)
            {
                if(!prepareMoveOffset)
                {
                    setLocation(getX() - moveXOffset, getY() - moveYOffset);
                    prepareMoveOffset = true;
                }
                attackAnimation(attackFrame);
            }
            else if(getImage() == attackAnim.get(attackAnim.size()-1))
            {
                setLocation(getX() - attackXOffset, getY() - attackYOffset);
                setImage(idleAnim.get(0));
            }
            else if(timer < atkCooldown)
            {
                idleIndex = animate(idleAnim, idleIndex);
                timer++;
                if(!prepareMoveOffset)
                {
                    setLocation(getX() - moveXOffset, getY() - moveYOffset);
                    prepareMoveOffset = true;
                }
            }
            else
            {
                standingXPos = getX();
                if(prepareMoveOffset)
                {
                    setLocation(getX() + moveXOffset, getY() + moveYOffset);
                    prepareMoveOffset = false;
                }
                walkIndex = animate(walkAnim, walkIndex);
                walk();
            }
            if (health <= 0)
            {
                getWorld().removeObject(this);
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
        animationTimer.mark();
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

    protected abstract void knockback();

    /**
     * Does damage to a target
     */
    protected abstract void attack();

    /**
     * Animation for an attack
     */
    protected void attackAnimation(int attackFrame)
    {
        if(isAttacking)
        {
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
            if(attackIndex == attackAnim.size()) //Arbitrary number, replace with total animation index later
            {
                isAttacking = false;
                attackIndex = 0;
                timer = 0;
            }
        }
    }

    /**
     * Takes damage from attack
     */
    protected void hurt(int damage)
    {
        getWorld().addObject(new HitParticle(), getX(), getY());

        if(shield<1) //mitigates the damage dealt if shield is present
        {
            this.health -= damage;
        }
        else 
        {
            shield--;
        }

        if (knockbackHealth.size() > 0 && health <= knockbackHealth.get(knockbackHealth.size()-1).intValue()&&!isKnockedBack&&knockbackTimer==0)
        {
            health = knockbackHealth.get(knockbackHealth.size()-1);
            isKnockedBack = true;
            isAttacking = false;
            attackIndex = 0;
            setImage(idleAnim.get(0)); // Replace with knockback Sprite later.
            timer = 10000;
            knockback();
            knockbackHealth.remove(knockbackHealth.size()-1);
        }
    }

    protected void heal(int recover)
    {
        this.health += recover;
    }

    protected void shield(int instance)
    {
        shield = instance;
    }

    protected double getHealthDividedByMax()
    {
        return (double)health/maxHealth;
    }

    /**
     * Simple Animations
     */
    protected int animate(ArrayList<GreenfootImage> animation, int index)
    {
        setImage(animation.get(index));
        index++;
        if(index > animation.size()-1)
        {
            index = 0;
        }
        return index;
    }

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

    public int getNormalX()
    {
        return standingXPos;
    }

    public int getNormalY()
    {
        return startYPos;
    }
}
