import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wallet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wallet extends UI
{
    private boolean circle, spawned;
    private int amount;
    private int money;
    
    private Text display;
    
    public Wallet(boolean c) {
        getImage().setTransparency(0);
        
        if (c) {
            circle = true;
        } else {
            circle = false;
        }
        spawned = true;
        amount = 0;
        
        display = new Text("$" + amount, 30);
    }
    
    public void act() {
        if (spawned) {
            getWorld().addObject(display, getX(), getY());
            spawned = false;
        }
        amount ++;
        if(amount % 100 == 0)
        {
            money = amount;
        }
        display.updateText("$" + money, 30);
    }
    
    public void spend(int cost) {
        amount -= cost;
    }

    public boolean getCircle() {return circle;}
    public int getAmount() {return amount;}
}
