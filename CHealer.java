import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.io.File;
import java.util.ArrayList;

/**
 * Write a description of class Healer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CHealer extends Circle
{
    /**
     * Act - do whatever the Healer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public CHealer(int stage)
    {
        super(stage);
        switch(stage)
        {
            case 1:
            attackXOffset = 0;
            attackYOffset = -35;
            loadStageOneAnimationFrames("images/Units/CHealer/StageOne");    
            
            attackFrame = 26;
            atkCooldown = 180;
            knockbacks = 2;
            speed = 1;
            atk = 15;
            health = 12;
            break;
            
            case 2:
            attackXOffset = -10;
            attackYOffset = -34;
            loadStageTwoAnimationFrames("images/Units/CHealer/StageTwo");
            
            attackFrame = 26;
            atkCooldown = 150;
            knockbacks = 2;
            speed = 1;
            atk = 20;
            health = 24;
            break;
            
            case 3:
            attackXOffset = 10;
            attackYOffset = -50;
            loadAnimationFrames("images/Units/CHealer/StageThree");
            
            attackFrame = 30;
            atkCooldown = 120;
            knockbacks = 3;
            speed = 1;
            atk = 40;
            health = 30;
            break;
        }
    }
    
    public void addedToWorld(World world)
    {
        if(justAddedToWorld)
        {
            super.addedToWorld(world);
            range = 300;
            standingRange = range - range/10;
        }
    }
    
    /**
     * Heals an ally
     */
    protected void attack()
    {
        List<Circle> potentialTargets = getObjectsInRange(range, Circle.class);
        if(potentialTargets.size() > 0)
        {
            Circle target = potentialTargets.get(0);
            for(int i = 0; i < potentialTargets.size(); i++)
            {
                if(potentialTargets.get(i).getHealthDividedByMax() < target.getHealthDividedByMax())
                {
                    target = potentialTargets.get(i);
                }
            }
            if(target != null)
            {
                //System.out.println("Chit");
                target.heal(atk);
            }
            else
            {
                //System.out.println("Cmiss");
            }
        }
    }
    
    //Accidentally messed up animations
    
    private void loadStageOneAnimationFrames(String path)
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
    
    private void loadStageTwoAnimationFrames(String path)
    {
        //Important: Ensure all folders are labelled with "attack", "move", and "stand"
        for(int i = 0; i < new File(path+"/attack").listFiles().length-1; i++)
        {

            attackAnim.add(new GreenfootImage(path + "/attack/" + "0(2)-"+ i + " 2.png"));
            attackAnim.get(i).scale((int)(attackAnim.get(i).getWidth()*(imageScale*1.2)),(int)(attackAnim.get(i).getHeight()*(imageScale*1.2)));
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
     * Gets the name of the unit
     */
    protected String getName(){
        return "CHealer";
    }
}
