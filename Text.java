import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends UI
{
    private GreenfootImage img;
    public Text(String text, int size) {
        updateText(text, size);
    }
    
    public Text(String text, int size, Color color, Color outline) {
        updateText(text, size, color, outline);
    }

    public void updateText(String text, int size) {
        img = new GreenfootImage(text, size, Color.BLACK , null);
        setImage(img);
    }
    
    public void updateText(String text, int size, Color color, Color outline) {
        img = new GreenfootImage(text, size, color , outline);
        setImage(img);
    }
}
