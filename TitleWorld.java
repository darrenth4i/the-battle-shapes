import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * By: Darren T, Justin Y, Brennan L, Andy L, Braden C
 * Written by: Darren T
 * 2024-11-19
 * 
 * A simulation based on the game The Battle Cats
 * 
 * Features:
 *      Custom Made Graphics: Nearly every unit (with the exception of STesseract)
 *                  was drawn/animated by Justin and Andy in the art style of 
 *                  The Battle Cats!
 *                  
 *      Variety of Settings: You have the option to customize... 
 *                              - Unit selection (must not be duplicates)
 *                              - Tower starting health
 *                              - Tower type
 *                              - Randomized/Smart Moves
 *                              - Music selection
 *
 *      Comeback Mechanics: A variety of events may happen when one side is losing
 *                  to give them a chance to comeback. This includes...
 *                  
 *                      Conscription - Spawn a wave of warriors
 *                      Double Money - Double the amount of money gained
 *                      Meteor - Rain a barrage of meteors on the enemies
 *                      Blood Splatter - Instantly kill all enemies on field
 *
 *      Upgradeable Units/Towers: Basic Units and Towers have the ability to level
 *                  up! This gives them a new look and increases their strength!
 *                  
 *                      Towers increase level by spending money
 *                      Units increase level by being used a set amount of times
 *                      
 *      Storyline???: A short but ominous cutscene begins at the start of the
 *                  simulation detailing a brief encounter between the Square
 *                  and the Circle. Could Circle be hiding a dark secret? What
 *                  happens if he triumphs over the Squares...
 *
 * Sources:
 * 
 * Images:
 *      STesseract - https://upload.wikimedia.org/wikipedia/commons/5/55/Tesseract.gif
 *      Background - https://battle-cats.fandom.com/wiki/Backgrounds 
 *      Wallet Upgrade Icon - https://pixelartmaker.com/art/9a48a6406ace91d
 *      Tower Upgrade Icon - https://www.pixilart.com/art/simple-pixel-sword-55db663a1a68ede
 *      Blood Splat - https://tibia.fandom.com/wiki/Blood_Effect 
 *      Meteor Projectile - https://www.freepik.com/vectors/flame-projectile/3 
 *      Level up - https://pngtree.com/freepng/yellow-modern-level-up-game-design-vector_6049009.html 
 *               - https://www.deviantart.com/woodlandbuckle/art/Super-Saiyan-Aura-Dokkan-Battle-Animation-896175892 
 *      SReaper Button Icon - https://static.wikia.nocookie.net/elderscrolls/images/5/5d/Ancient_traveler%27s_skull.png/revision/latest?cb=20120908013428
 *      Railgun Projectile - https://orangemushroom.net/2018/06/16/kmst-ver-1-2-069-maplestory-black-mage-begins/wild-grenade-effect-explosion/
 *      
 *      The following image(s) were made by Andy L
 *          Title Screen, End Screen, Cutscene Animation, Cursor, CBomb, CCyclone, CDragon, CFodder, CHealer,
 *          CRanger, CTank, CWarrior, Defense, Offense, Support
 *          
 *      The following image(s) were made by Justin Y
 *          SFodder, SHealer, SRailgun, SRanger, SReaper, STank, SWarrior
 *      
 *      Notification - Darren T
 * 
 * Sounds:
 *      SRanger SFX - https://pixabay.com/sound-effects/pistol-shot-233473/     
 *                  - https://pixabay.com/sound-effects/explosion-91872/ 
 *                  - https://pixabay.com/sound-effects/sniper-rifle-5989/ 
 *      SFodder SFX - https://mixkit.co/free-sound-effects/punch/ 
 *      SWarrior SFX - https://tuna.voicemod.net/sound/e1f670d0-ab4a-4a53-946b-12c53d3a4a4b
 *                   - https://pixabay.com/sound-effects/sword-slash-and-swing-185432/ 
 *                   - https://pixabay.com/sound-effects/sword-thud-47635/ 
 *      STank SFX - https://tuna.voicemod.net/sound/450c554f-b173-464d-a7dd-c9ae07a780a9 
 *                - https://pixabay.com/sound-effects/bubblebeam-41985/ 
 *                - https://tuna.voicemod.net/sound/f4c4735c-cd3c-42d4-a0ac-3339e4b1353c 
 *      SHealer SFX - https://pixabay.com/sound-effects/healpop-46004/ 
 *                  - https://www.myinstants.com/en/instant/holy-sound-99033/ 
 *                  - https://pixabay.com/sound-effects/snap-96332/
 *      STesseract SFX - https://pixabay.com/sound-effects/ship-engine-interior-buzz-loop-248613/ 
 *      SRailgun SFX - https://pixabay.com/sound-effects/laser-zap-90575/ 
 *      SReaper SFX - https://www.myinstants.com/en/instant/death-bong-30415/ 
 *      CFodder SFX - https://pixabay.com/sound-effects/thump-105302/
 *                  - https://pixabay.com/sound-effects/table-smash-47690/
 *      CWarrior SFX - https://pixabay.com/sound-effects/sword-swing-whoosh-sound-effect-1-241824/
 *                   - https://pixabay.com/sound-effects/sword-swing-whoosh-sound-effect-2-241823/
 *                   - https://mixkit.co/free-sound-effects/laser/
 *      CTank SFX - https://pixabay.com/sound-effects/bonk-sound-effect-36055/
 *      CHealer SFX - Magic Spell Impact Sound Effect | Royalty-free Music - Pixabay
 *                  - https://pixabay.com/sound-effects/magic-spell-shoot-sound-effect-236816/
 *                  - https://pixabay.com/sound-effects/buffer-spell-88994/
 *      CRanger SFX - https://pixabay.com/sound-effects/bow-and-arrow-shoot-sound-effect-2-239699/
 *                  - https://pixabay.com/sound-effects/gunfire-single-shot-colt-peacemaker-94951/
 *                  - https://mixkit.co/free-sound-effects/sci-fi/
 *      CBomb SFX - https://freesound.org/people/hoffy1138/sounds/276968/ 
 *      CDragon SFX - Ultrakill
 *      CCyclone SFX - Battle Cats
 *      Shockwave SFX - Ultrakill
 *      Cutscene Animation SFX - https://pixabay.com/sound-effects/nature-sounds-240504/ 
 *                             - https://pixabay.com/sound-effects/search/cricket/ 
 *                             - https://www.youtube.com/watch?v=qIEfecwYiUA 
 *      
 *      The following songs were made by Justin Y
 *          Square Victory Theme, Circle Victory Theme, Main Theme, Loading Theme
 *      
 *      The following songs were made by Andy L as REMIXES of Battle Cats themes
 *          Battle #1, Celestial Battle, Slow Battle, Rush
 *      
 * Code:
 *      Code was used that was made by Mr. Cohen such as SuperStatBar class, 
 *      SuperSmoothMover class, ProgressBar class, Coordinate class
 *      
 *      ChatGPT was used for the moveTowardsTarget() code in TowerProjectile and 
 *      was also technically used for followCursor() in Cursor (since the code 
 *      comes from Darren's (my) vehicle simulation and I used ChatGPT for 
 *      the same code)
 */
/**
 * The world which contains the title
 * 
 * @author Andy Li and Justin 
 * @version 1
 */
public class TitleWorld extends World
{
    private GreenfootSound mainTheme = new GreenfootSound("sounds/mainTheme.mp3");
    /**
     * Constructor for objects of class TitleWorld.
     * 
     */
    public TitleWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 700, 1, false);
        prepare();
        setBackground("images/Backgrounds/title.png");
        setPaintOrder(Effect.class, Title.class, MenuButtons.class, BlackBox.class);
    }
    /**
     * Constructor for objects of class TitleWorld.
     * 
     */
    public TitleWorld(boolean thisIsOnlyHereToMakeSimulationRestartPlayMusic)
    {    
        this();
        started();
        addObject(new FullscreenTransition(), 512, 300);
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Title title = new Title("TheBattleShapes.png");
        addObject(title,512,1000);
        MenuButtons play = new StartButton(0);
        addObject(play,512,3000);
    }
    
    public void started()
    {
        mainTheme.setVolume(60);
        mainTheme.playLoop();
    }
    
    public void stopped()
    {
        mainTheme.pause();
    }
    
    public void stopMusic()
    {
        mainTheme.stop();
    }
}
