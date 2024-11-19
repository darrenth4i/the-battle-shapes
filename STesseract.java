import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.io.File;

/**
 * Write a description of class Warrior here.
 * 
 * @author Justin Ye 
 * @version 10
 */
public class STesseract extends Square
{   
    private SimpleTimer attackTimer = new SimpleTimer(); 
    private int attackDelay = 100;
    private int currentFrame = 0;
    /** 
     * This constructor creates the STesseract, since its a special unit it has no stage, 
     * it will still take atk, health, atkCooldown and frames to use methods from superclass Square 
     * and Unit.
     */
    public STesseract()
    {
        super();
        imageScale = 0.6;
        attackFrame = 0;
        attackXOffset = 0;
        attackYOffset = 0;
        loadAnimationFrames("images/Units/STesseract");
        knockbacks = 1;
        atkCooldown = 1;
        speed = 1;
        atk = 2;
        health = 36;
    }
    
    /**
     * Act - do whatever the STesseract wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (attackTimer.millisElapsed() >= attackDelay) {
            attack(); 
            attackTimer.mark(); 
        }
        super.act();
    }
    
    /**
     * Takes one circle in range or tower in range and for every frame it attacks, making it good for 
     * wiping out small hordes of Fodders
     */
    protected void attack()
    {
        List<Circle> potentialTargets = getObjectsInRange(range, Circle.class);
        List<Tower> towerTarget = getObjectsInRange(2 * range,Tower.class);
        Tower tower = towerTarget.size() > 0 ? towerTarget.get(0) : null;
        if (getWorld() == null) {
            return;
        }
        
        if (potentialTargets.size() > 0) {
            Circle target = potentialTargets.get(0);
            setImage(attackAnim.get(currentFrame)); 
            if (target != null && target.getWorld() != null) {
                target.hurt(atk);
            }
            currentFrame++;
            if (currentFrame >= attackAnim.size()) {
                currentFrame = 0; 
            }
        }
    }
    
    /**
     * Loads in the gif files instead of png because for some reason the png files get corrupted when 
     * trying to implement them.
     */
    protected void loadAnimationFrames(String path)
    {
        //Important: Ensure all folders are labelled with "attack", "move", and "stand"
        for(int i = 0; i < new File(path+"/attack").listFiles().length-1; i++)
        {
            attackAnim.add(new GreenfootImage(path + "/attack/" + i + ".gif"));
            attackAnim.get(i).scale((int)(attackAnim.get(i).getWidth()*imageScale),(int)(attackAnim.get(i).getHeight()*imageScale));
        }
        for(int i = 0; i < new File(path+"/attack").listFiles().length-1; i++)
        {
            walkAnim.add(new GreenfootImage(path + "/attack/" + i + ".gif"));
            walkAnim.get(i).scale((int)(walkAnim.get(i).getWidth()*imageScale),(int)(walkAnim.get(i).getHeight()*imageScale));
        }
        for(int i = 0; i < new File(path+"/attack").listFiles().length-1; i++)
        {
            idleAnim.add(new GreenfootImage(path + "/attack/" + i + ".gif"));
            idleAnim.get(i).scale((int)(idleAnim.get(i).getWidth()*imageScale),(int)(idleAnim.get(i).getHeight()*imageScale));
        }
    }
    
    /**
     * Gets the name of the unit
     */
    protected String getName(){
        return "STesseract";
    }
}
