import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;



/**
 * Taken from Mr.Cohen's Bug Simulation project 2024
 */

public class ProgressBar extends PlayerUI
{

    // When using the simplest constructor, this allows control of whether bars
    // should hide by default when at 100%
    private static final boolean HIDE_AT_MAX_DEFAULT = false;
    
    // Declare Instance Variables
    private int[] maxVals;
    private int[] currVals;
    private double currPercentVal;
    private int[] missingBarSize;
    private int[] filledBarSize;
    private boolean hideAtMax;
    private boolean hasBorder;

    // for multiple bars
    private int barCount;
    private int barHeight;

    // Declare Instance Images
    private GreenfootImage bar;
    private GreenfootImage blank;

    // Some constants - can be changed to suit size of related objects
    private int width;
    private int height;
    private int offset;
    private int borderThickness;

    // Declare Instance Objects
    private Actor target;
    
    // Declare some Color objects
    private Color[] filledColor;
    private Color[] missingColor;
    private Color borderColor;


    /**
     *  The most detailed constructor! Can specify a border including thickness and color.
     *  
     *  @param  maxVal  the maximum value for this stat
     *  @param currVal  the starting value for this stat
     *  @param  owner   the Actor that this stat bar will follow (null for DONT FOLLOW). Can be changed to just an Actor if needed
     *  @param  width   the width of the stat bar
     *  @param height   the height of the stat bar
     *  @param offset   the y-offset for positioning this bar in relation to it's owner
     *  @param filledColor  the color to be used to represent the current value
     *  @param missingColor the color to be used to represent the missing value
     *  @param  hideAtMax   set to true to have this statBar hide itself when currVal == maxVal
     *  @param borderColor  the Color of the border
     *  @param borderThickness  the thickness of the border. This value should be at least 1.
     */
    public ProgressBar (int maxVal,  int currVal, Actor owner, int width, int height, int offset, Color filledColor, Color missingColor, boolean hideAtMax, Color borderColor, int borderThickness){
        this (new int[]{maxVal}, new int[]{currVal}, owner, width, height, offset, new Color[] {filledColor}, new Color[] {missingColor}, hideAtMax, borderColor, borderThickness);
    }

    /**
     *  The king of all StatBar constuctors!
     * 
     *  Takes details for an array of bars, otherwise the same as above. Note that all arrays must be the same length.
     * 
     *  @param  maxVal[]  the maximum values for each stat
     *  @param currVal[]  the starting values for each stat
     *  @param  owner   the Actor that this stat bar will follow (null for DONT FOLLOW). Can be changed to just an Actor if needed
     *  @param  width   the width of the stat bar
     *  @param height   the height of the stat bar
     *  @param offset   the y-offset for positioning this bar in relation to it's owner
     *  @param filledColor[]  the colors to be used to represent the current values
     *  @param missingColor[] the colors to be used to represent the missing values
     *  @param  hideAtMax   set to true to have this statBar hide itself when currVal == maxVal
     *  @param borderColor  the Color of the border
     *  @param borderThickness  the thickness of the border. This value should be at least 1.
     */
    public ProgressBar (int maxVals[],  int currVals[], Actor owner, int width, int height, int offset, Color filledColor[], Color missingColor[], boolean hideAtMax, Color borderColor, int borderThickness){
        this.barCount = maxVals.length;
        this.barHeight = (height - (2* borderThickness))/barCount;

        this.target = (Actor)owner;

        this.width = width;
        this.height = height;
        this.offset = offset;
        this.hideAtMax = hideAtMax;

        this.maxVals = maxVals;
        
        this.filledColor = filledColor;
        this.missingColor = missingColor;

        bar = new GreenfootImage (width, height);
        blank = new GreenfootImage (1, 1);

        if (borderColor == null){
            borderThickness = 0;
            hasBorder = false;
        } else {
            hasBorder = true;
            this.borderColor = borderColor;
            this.borderThickness = borderThickness;
        }

        update(currVals);

    }
    
    /**
     * Update method for a single bar StatBar objects.
     * 
     * If your StatBar has multiple bars, use update (int[]) instead.
     * 
     * @param newCurrVal    the new current value for this bar.
     */
    public void update (int newCurrVal){
        update (new int[]{newCurrVal});
    }
    
    /**
     * The update method.
     * 
     * Take an array of integers (array length should be the same as the number of bars in this StatBar). Updates
     * the current values for each bar with the values provided. 
     * 
     * @param newCurrVals   an array of the same length as this StatBar has bars, consisting of new values to update the bars.
     */
    public void update (int newCurrVals[])
    {
        boolean updateRequired = !(Arrays.equals(currVals, newCurrVals));

        if (updateRequired){
            currVals = newCurrVals;

            if (hideAtMax){ // if the hide when full feature is on, figure it if this bar should hide
                boolean full = true; // set full to true, until I find one that isn't
                for (int i = 0; i < barCount; i++){ // look through all of my bars for one that isn't full
                    if (currVals[i] != maxVals[i]){ // check if the current value is not the same as the max (not full)
                        full = false; // if I find one that's not full
                        break;        // no point looking at the rest so break out of the for loop
                    }
                }
                if (full) // This will only happen if I looked at all bars, and they are all full
                {
                    this.setImage(blank); // set image to a 1x1 transparent image I created above
                } else {

                    redraw();
                    this.setImage(bar);   
                }
            }
            else
            {

                redraw();
                this.setImage(bar);
            }
        }
    }

    /**
     * Set the maximum value - for StatBar objects with a single bar only.
     * 
     * @param maxVal    the new maximum value for this bar
     */
    public void setMaxVal (int maxVal){
        setMaxVal (new int[]{maxVal});
    }

    /**
     * Set the maximum values for all bars in this StatBar.
     * 
     * @param maxVals   An array containing maximum values for every bar in this StatBar. Should have the same 
     *                  length as the StatBar has bars.
     */
    public void setMaxVal (int maxVals[]){
        for (int i = 0; i < barCount; i++){
            if (maxVals[i] <= 0){
                return; // invalid
            }
        }
        this.maxVals = maxVals;
    }

    /**
     *   The Actual drawing method that draws the bars onto the image for this Actor
     *      
     *      This method is private because we don't want another method to 
     *      waste time calling this if no changes have been made to the 
     */
    private void redraw(){

        if (hasBorder){
            bar.setColor (borderColor);
            for (int i = 0; i < borderThickness; i++){
                bar.drawRect (i, i, width - 1 - (i * 2), height - 1 - (i * 2));
            }
        }

        int extraHeight = 0;
        for (int i = 0; i < barCount; i++){
            if (i % 2 == 0 && height % 2 == 1){
                extraHeight = 1;
            }
            currPercentVal = (double) currVals[i] / maxVals[i];
            int filledBarWidth = (int) (currPercentVal * (width-(borderThickness * 2)));
            int missingBarWidth = width - (borderThickness*2) - filledBarWidth;
            bar.setColor(filledColor[i]);
            bar.fillRect(borderThickness, borderThickness + (i * barHeight), filledBarWidth, barHeight + extraHeight);
            bar.setColor(missingColor[i]);
            bar.fillRect(filledBarWidth + borderThickness, borderThickness + (i * barHeight), missingBarWidth, barHeight +extraHeight);
        }

    }
}
