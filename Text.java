import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    public Text(String text, int size) {
        updateText(text, size);
    }

    public void updateText(String text, int size) {
        GreenfootImage img = new GreenfootImage(text, size, Color.BLACK , Color.WHITE);
        setImage(img);  
    }
}
