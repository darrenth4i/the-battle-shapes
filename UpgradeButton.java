import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootImage;

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
    
    public UpgradeButton(String type) {
        this.type = type;
        
        buttons = new GreenfootImage[5];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new GreenfootImage("/UIElements/upgradebutton_" + type + "_" + i + ".png");
        }
    }
}
