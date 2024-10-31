import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    
    protected int walkIndex;
    protected int attackIndex;
    protected int idleIndex;
    protected int deathIndex;
    
    protected Units()
    {
        isAttacking = false;
        attackFrame = 0; //Placeholder
        //Sets image size
        imageScale = 0.40;
        getImage().scale((int)(getImage().getWidth()*imageScale),(int)(getImage().getHeight()*imageScale));
    }
    
    
    /**
     * Act - Chooses whether to move, attack, or stand still if on cooldown
     */
    public void act()
    {
        walk();
        if(atkCooldown < timer)
        {
            attackAnimation(attackFrame);
        }
        else
        {
            idle();
        }
    }
    
    
    /**
     * Animation for an attack
     */
    protected void attackAnimation(int attackFrame)
    {
        if(isAttacking)
        {
            //Animation code here
            if(attackIndex == attackFrame)
            {
                attack();
            }
            if(attackIndex == 15) //Arbitrary number, replace with total animation index later
            {
                isAttacking = false;
            }
            attackIndex++;
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
        if(checkFront()&& !isAttacking)
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
        return getOneObjectAtOffset(-getImage().getWidth(), 0, Units.class) != null;
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
     * idle animation
     */
    protected void idle()
    {
        
    }
}
