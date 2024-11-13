import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Text images
 */
public class Text extends UI
{
    private GreenfootImage img;

    public Text(String text, int size) {
        //simple text with customizable size
        updateText(text, size);
    }

    public Text(String text, int size, Color color, Color outline) {
        //customize the text color and the outline color
        updateText(text, size, color, outline);
    }

    public void updateText(String text, int size) {
        //update for simple text
        img = new GreenfootImage(text, size, Color.BLACK , null);
        setImage(img);
    }

    public void updateText(String text, int size, Color color, Color outline) {
        //update for more customizable text
        img = new GreenfootImage(text, size, color , outline);
        setImage(img);
    }
}
