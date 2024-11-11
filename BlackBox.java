import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class BlackBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlackBox extends PlayerUI
{
    private int transparency;
    
    private SpawnUnitButton attachedButton;
    private GreenfootImage img;
        
    public BlackBox(int t, SpawnUnitButton button) {
        transparency = t;
        attachedButton = button;
        
        img = new GreenfootImage(attachedButton.getImage().getWidth(), attachedButton.getImage().getHeight());
        img.setColor(Color.BLACK);
        img.fillRect(0, 0, attachedButton.getImage().getWidth(), attachedButton.getImage().getHeight()); 
        img.scale(90, attachedButton.getImage().getHeight());
        img.setTransparency(transparency);
        setImage(img);
    }
    
    public BlackBox(int t, int width, int height) {
        transparency = t;
        
        img = new GreenfootImage(width, height);
        img.setColor(Color.BLACK);
        img.fillRect(0, 0, width, height); 
        img.scale(width, height);
        img.setTransparency(transparency);
        setImage(img);
    }
    
    public void hide() {
        img.setTransparency(0);
        setImage(img);
    }
    
    public void show() {
        img.setTransparency(transparency);
        setImage(img);
    }
    
    public void changeTransparency(int transparency)
    {
        img.setTransparency(transparency);
        setImage(img);
    }
}
