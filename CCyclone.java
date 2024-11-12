import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Warrior here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CCyclone extends Circle
{   
    public CCyclone()
    {
        super();
        imageScale = 0.35;
        attackXOffset = -0;
        attackYOffset = -0;
        
        loadAnimationFrames("images/Units/CCyclone");
        knockbacks = 2;
        atkCooldown = 0;
        speed = 2;
        atk = 2;
        health = 16;
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
            attackIndex++;
            if(attackIndex == attackAnim.size()) //Arbitrary number, replace with total animation index later
            {
                attack();
                isAttacking = false;
                attackIndex = 0;
                timer = 0;
            }
            animationTimer.mark();
        }
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "CCyclone";
    }
}
