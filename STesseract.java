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
    public STesseract()
    {
        super();
        imageScale = 0.35;
        attackFrame = 0;
        attackXOffset = -0;
        attackYOffset = -50;
        
        loadAnimationFrames("images/Units/STesseract");
        knockbacks = 1;
        atkCooldown = 5;
        speed = 2;
        atk = 20;
        health = 370;
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
    
    
    protected void loadAnimationFrames(String path)
    {
        //Important: Ensure all folders are labelled with "attack", "move", and "stand"
        for(int i = 0; i < new File(path+"/attack").listFiles().length-1; i++)
        {
            attackAnim.add(new GreenfootImage(path + "/attack/" + i + ".gif"));
            attackAnim.get(i).scale((int)(attackAnim.get(i).getWidth()*imageScale),(int)(attackAnim.get(i).getHeight()*imageScale));
        }
        for(int i = 0; i < new File(path+"/move").listFiles().length-1; i++)
        {
            walkAnim.add(new GreenfootImage(path + "/move/" + i + ".gif"));
            walkAnim.get(i).scale((int)(walkAnim.get(i).getWidth()*imageScale),(int)(walkAnim.get(i).getHeight()*imageScale));
        }
        for(int i = 0; i < new File(path+"/stand").listFiles().length-1; i++)
        {
            idleAnim.add(new GreenfootImage(path + "/stand/" + i + ".gif"));
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
