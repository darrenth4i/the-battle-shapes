import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.io.File;

/**
 * Write a description of class Warrior here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class STesseract extends Square
{   
    private SimpleTimer attackTimer = new SimpleTimer(); 
    private int attackDelay = 100;
    private int currentFrame = 0;
    public STesseract()
    {
        super();
        imageScale = 0.6;
        attackFrame = 0;
        attackXOffset = 0;
        attackYOffset = 0;
        loadAnimationFrames("images/Units/STesseract");
        knockbacks = 1;
        atkCooldown = 5;
        speed = 1;
        atk = 2;
        health = 30;
    }
    
    /**
     * Act - do whatever the Warrior wants to do. This method is called whenever
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
    
    protected void attack()
    {
        List<Circle> potentialTargets = getObjectsInRange(range, Circle.class);
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
