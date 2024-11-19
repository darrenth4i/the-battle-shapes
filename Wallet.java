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
    private double amount, multiplier, eventMultiplier;
    
    private Text display;
    private GreenfootImage walletImage;
    
    public Wallet(boolean c) {
        //set image
        walletImage = new GreenfootImage("/UIElements/wallet.png");
        walletImage.scale(125,125);
        setImage(walletImage);
        //determine whether this wallet is on circle team or on square team
        if (c) {
            circle = true;
        } else {
            circle = false;
        }
        spawned = true;
        amount = 0;
        //multiplier affected by the wallet upgrade
        multiplier = 1;   
        //eventmultiplier for special events that increase money production
        eventMultiplier = 1;
        //display current amount
        display = new Text("$" + (int) amount, 30, Color.BLACK, Color.WHITE);
    }
    
    public void act() {
        if (spawned) {
            //when first added to world
            getWorld().addObject(display, getX(), getY());
            spawned = false;
        }
        
        //every frame, increase amount in wallet depending on upgrades and event
        amount += 1 * multiplier * eventMultiplier;
        //display new amount
        display.updateText("$" + (int) amount, 30, Color.BLACK, Color.WHITE);
    }
    
    public void spend(int cost) {
        //method for spending units and upgrade buttons
        amount -= cost;
    }
    
    //getter and setter methods
    public void setMultiplier(double m) {multiplier = m;}
    public void setEventMultiplier(double m) {eventMultiplier = m;}
    public boolean getCircle() {return circle;}
    public double getAmount() {return amount;}
    public double getMultiplier() {return multiplier;}
}
