import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wallet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wallet extends PlayerUI
{
    private boolean circle, spawned;
    private double amount, multiplier;
    
    private Text display;
    private GreenfootImage walletImage;
    
    public Wallet(boolean c) {
        walletImage = new GreenfootImage("/UIElements/wallet.png");
        walletImage.scale(150,150);
        setImage(walletImage);
        
        
        if (c) {
            circle = true;
        } else {
            circle = false;
        }
        spawned = true;
        amount = 0;
        multiplier = 0.5;   
        
        display = new Text("$" + (int) amount, 30, Color.BLACK, Color.WHITE);
    }
    
    public void act() {
        if (spawned) {
            getWorld().addObject(display, getX(), getY());
            spawned = false;
        }
        amount += 1 * multiplier;
        display.updateText("$" + (int) amount, 30, Color.BLACK, Color.WHITE);
    }
    
    public void spend(int cost) {
        amount -= cost;
    }
    
    public void setMultiplier(double m) {multiplier = m;}
    public boolean getCircle() {return circle;}
    public double getAmount() {return amount;}
}
