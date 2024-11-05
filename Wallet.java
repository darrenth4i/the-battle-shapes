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
    private double amount;
    
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
        
        display = new Text("$" + (int) amount, 30);
    }
    
    public void act() {
        if (spawned) {
            getWorld().addObject(display, getX(), getY());
            spawned = false;
        }
        amount += 1; //* getMultiplier() When an upgrade to wallet is added
        display.updateText("$" + (int) amount, 30);
    }
    
    public void spend(int cost) {
        amount -= cost;
    }

    public boolean getCircle() {return circle;}
    public double getAmount() {return amount;}
}
