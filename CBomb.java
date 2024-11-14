import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Warrior here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CBomb extends Circle
{   
    public CBomb()
    {
        super();
        imageScale = 0.35;
        attackFrame = 16;
        attackXOffset = -0;
        attackYOffset = -50;
        
        loadAnimationFrames("images/Units/CBomb");
        knockbacks = 2;
        atkCooldown = 5;
        speed = 2;
        atk = 20;
        health = 70;
    }
    
    public void addedToWorld(World world)
    {
        if(justAddedToWorld)
        {
            super.addedToWorld(world);
            range = 300;
            standingRange = 70;
        }
    }
    
    /**
     * Act - do whatever the Warrior wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    /**
     * Does damage to a target
     */
    protected void attack()
    {
        List<Square> potentialTargets = getObjectsInRange(range, Square.class);
        List<Tower> towerTarget = getObjectsInRange(range,Tower.class);
        Tower tower = towerTarget.size() > 0 ? towerTarget.get(0) : null;
        if(potentialTargets.size() > 0||towerTarget.size() > 0)
        {
            for(int i = 0; i < potentialTargets.size(); i++)
            {
                if(potentialTargets.get(i) != null)
                {
                    potentialTargets.get(i).shieldBreak();
                    potentialTargets.get(i).hurt(atk);
                }
            }
            if(tower != null && !tower.getCircle())
            {
                tower = towerTarget.get(0);
                tower.hurt(atk);
            }
        }
    }
    
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
                getWorld().removeObject(this);
            }
            animationTimer.mark();
        }
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "CBomb";
    }
}
