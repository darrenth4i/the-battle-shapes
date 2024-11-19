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
    private GreenfootSound[] atkSoundEffect;
    private int atkSoundEffectIndex;
    public CCyclone()
    {
        super();
        imageScale = 0.35;
        attackXOffset = -0;
        attackYOffset = -0;
        
        loadAnimationFrames("images/Units/CCyclone");
        knockbacks = 3;
        atkCooldown = 0;
        speed = 3;
        atk = 2;
        health = 28;
        atkSoundEffect = new GreenfootSound[10];
        for (int i = 0; i < atkSoundEffect.length; i++){
            atkSoundEffect[i] = new GreenfootSound ("/attackSounds/" + this.getClass().getName() + ".wav");
            atkSoundEffect[i].setVolume (70);
        }
    }
    
    public void addedToWorld(World world)
    {
        if(justAddedToWorld)
        {
            super.addedToWorld(world);
            range += 40;
            standingRange += 40;
        }
    }
    
    /**
     * Act - do whatever the Cyclone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }

    public void playAtkSoundEffect()
    {
        atkSoundEffect[atkSoundEffectIndex].play();
        atkSoundEffectIndex++;
        if (atkSoundEffectIndex >= atkSoundEffect.length)
        {
            atkSoundEffectIndex = 0;
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
            attackIndex++;
            if(attackIndex == attackAnim.size())
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
