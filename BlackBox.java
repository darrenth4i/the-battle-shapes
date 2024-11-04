import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class BlackBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlackBox extends UI
{
    private int transparency;
    
    private GreenfootImage img;
    
    
    public BlackBox(int t) {
        transparency = t;
        
        img = new GreenfootImage(90, 60);
        img.setColor(Color.BLACK);
        img.fillRect(0, 0, 90, 60); 
        img.scale(90,60);
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
}
