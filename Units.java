import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import java.util.ArrayList;

/**
 * Superclass for every Unit in the Simulation
 * 
 * @author Justin Ye and Andy Li)
 * @version Oct 31
 */
public abstract class Units extends SuperSmoothMover
{
    // Health of the unit
    protected int health;
    //Damage per hit
    protected int atk;
    //Time between attacks
    protected static int atkCooldown;
    protected int timer;
    //Walk speeds
    protected int speed;
    //Number of times unit could be knocked back 
    protected int knockbacks;
    //frame of the attack 
    protected int attackFrame;
    //Percentage of the image size
    protected double imageScale;
    //boolean for when it is attacking
    protected boolean isAttacking;
    
    protected int attackOffset;
    
    protected int walkIndex;
    protected ArrayList<GreenfootImage> walkAnim = new ArrayList<GreenfootImage>();
    protected int attackIndex;
    protected ArrayList<GreenfootImage> attackAnim = new ArrayList<GreenfootImage>();
    protected int idleIndex;
    protected ArrayList<GreenfootImage> idleAnim = new ArrayList<GreenfootImage>();
    protected int deathIndex;
    protected GreenfootImage knockback;
    
    protected Units()
    {
        isAttacking = false;
        atkCooldown = 60;
        attackFrame = 0; //Placeholder
        //Sets image size
        imageScale = 0.35;
        loadAnimationFrames("images/Units/Fodder/StageOne");
        getImage().scale((int)(getImage().getWidth()*imageScale),(int)(getImage().getHeight()*imageScale));
    }
    
    
    /**
     * Act - Chooses whether to move, attack, or stand still if on cooldown
     */
    public void act()
    {
        if(atkCooldown <= timer&&isAttacking)
        {
            attackAnimation(attackFrame);
        }
        else if(getImage() == attackAnim.get(attackAnim.size()-1))
        {
            setLocation(getX() - attackOffset, getY());
            setImage(idleAnim.get(0));
        }
        else if(timer < atkCooldown)
        {
            idleIndex = animate(idleAnim, idleIndex);
            timer++;
        }
        else
        {
            walkIndex = animate(walkAnim, walkIndex);
            walk();
        }
    }
    
    
    /**
     * Animation for an attack
     */
    protected void attackAnimation(int attackFrame)
    {
        if(isAttacking)
        {
            if(attackIndex == 0)
            {
                setLocation(getX() + attackOffset, getY());
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
     * Does damage to a target
     */
    protected void attack()
    {
        Units target = (Units)getOneObjectAtOffset(-getImage().getWidth(), 0, Units.class);
        if(target != null)
        {
            target.hurt(atk);
        }
    }
    
    /**
     * Walks forward if nothing is obstructing movement
     */
    protected void walk()
    {
        if(checkFront() && !isAttacking)
        {
            move(speed);
        }
        else
        {
            isAttacking = true;
        }
    }
    
    /**
     * Checks the path of the unit for any obstructions
     * @return If the path is clear
     */
    protected boolean checkFront()
    {
        //if it is empty, the front is clear
        return getOneObjectAtOffset(-getImage().getWidth(), 0, Units.class) == null;
    }
    
    /**
     * Takes damage from attack
     */
    protected void hurt(int damage)
    {
        this.health -= damage;
        if (health <= 0)
        {
            //Death animation method
        }
    }
    
    protected void heal(int recover)
    {
        this.health += recover;
    }
    
    /**
     * Animates and deletes object upon death
     */
    protected void die()
    {
        if(deathIndex != 12) //Arbitrary number, replace with total animation index later
        {
            //play animation
            deathIndex++;
        }
        else
        {
            getWorld().removeObject(this);
        }
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
    
    private void loadAnimationFrames(String path)
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
}
