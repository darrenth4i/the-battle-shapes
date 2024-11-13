import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;
import java.util.ArrayList;

/**
 * Write a description of class WalletUpgrade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpgradeButton extends PlayerUI
{
    private GreenfootImage icon;
    private GreenfootImage[] buttons;

    private String type;
    private int level, cost;
    private boolean buttonPressed, spawned, circle, clicked;

    private Tower tower;
    private Wallet wallet;
    private Text text;

    public UpgradeButton(String type, boolean circle) {
        this.type = type;
        level = 0;
        buttonPressed = false;
        clicked = false;
        spawned = true;
        this.circle = circle;
        cost = 500;

        buttons = new GreenfootImage[4];
        for (int i = 1; i < buttons.length; i++) {
            buttons[i-1] = new GreenfootImage("/UIElements/upgradebutton_" + type + "_" + i + ".png");
            buttons[i-1].scale(180,60);
        }

        setImage(buttons[0]);
    }

    public void act() {
        if (spawned) {
            ArrayList<Tower> towers = (ArrayList<Tower>)getWorld().getObjects(Tower.class);
            if (towers.size() != 0) {
                if (towers.get(0).getCircle() == circle) {
                    tower = towers.get(0);
                } else {
                    tower = towers.get(1);
                }
            }
            ArrayList<Wallet> wallets = (ArrayList<Wallet>)getWorld().getObjects(Wallet.class);
            if (wallets.size() != 0) {
                if (wallets.get(0).getCircle() == circle) {
                    wallet = wallets.get(0);
                } else {
                    wallet = wallets.get(1);
                }
            }

            text = new Text("$" + cost, 15, Color.BLACK, Color.WHITE);
            getWorld().addObject(text, getX() - 57, getY() + 10);

            spawned = false;
        }   

        setImage(buttons[level]);

        if (level < 2) {
            if (clicked)
            {
                wallet.spend(cost);
                cost = 1000;
                level++;
                if (type.equals("tower")) {
                    tower.updateLevel(level);
                } else {
                    wallet.setMultiplier(level);
                }
                text.updateText("$" + cost, 20, Color.BLACK, Color.WHITE);
            } 
            if(clicked)
            {
                getImage().scale(7*getImage().getWidth()/8, 7*getImage().getHeight()/8);
                clicked = false;      
            }
            else 
            {
                getImage().scale(getImage().getWidth(), getImage().getHeight());
            }
        } else if (level == 2) {
            getWorld().removeObject(text);
        }
    }
    
    /**
     * Setter method for clicked variable
     */
    public void setClicked(boolean c){
        clicked = c;
    }
    
    /**
     * Return if self is part of circle team
     */
    public boolean getCircle(){
        return circle;
    }
    
    /**
     * String to return type of UpgradeButton
     */
    public String getType(){
        return type;
    }
    
    /**
     * Method to return the coordinates of the button
     */
    public Coordinate getCoordinate(){
        return new Coordinate(getX(), getY());
    }
    
    /**
     * Method to return cost of upgrade
     */
    public int getCost(){
        return cost;
    }
}
