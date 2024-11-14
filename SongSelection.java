import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class SongSelection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SongSelection extends Graphic
{
    private SongSelector next = new SongSelector(4, this);
    private SongSelector previous = new SongSelector(5, this);
    private ArrayList<GreenfootImage> songList = new ArrayList();
    private ArrayList<GreenfootSound> songs = new ArrayList();
    private static int currentSongIndex = -1;
    private int maxSong = 4;
    
    public SongSelection(String path)
    {
        super(path);
        for(int i = 0; i < maxSong; i++) //change 4 to add more songs
        {
            songList.add(new GreenfootImage("images/SongSelection/" + i + ".png"));
        }
        songs.add(new GreenfootSound("sounds/Music/battle1.mp3")); 
        songs.add(new GreenfootSound("sounds/Music/battle2.mp3"));
        songs.add(new GreenfootSound("sounds/Music/battle3.mp3"));
        songs.add(new GreenfootSound("sounds/Music/rush.mp3"));
    }
    
    public void addedToWorld(World world)
    {
        getWorld().addObject(next, getX() + 350, getY() + getImage().getHeight()/4);
        getWorld().addObject(previous, getX() - 350, getY() + getImage().getHeight()/4);
    }
    
    /**
     * Act - do whatever the SongSelection wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
    
    public void nextSong()
    {
        stopAllSongs();
        currentSongIndex++;
        if(currentSongIndex > maxSong-1)
        {
            currentSongIndex = 0;
        }
        setImage(songList.get(currentSongIndex));
        songs.get(currentSongIndex).playLoop();
    }
    
    public void previousSong()
    {
        stopAllSongs();
        currentSongIndex--;
        if(currentSongIndex < 0)
        {
            currentSongIndex = maxSong-1;
        }
        setImage(songList.get(currentSongIndex));
        songs.get(currentSongIndex).playLoop();
    }
    
    public void stopAllSongs()
    {
        for( GreenfootSound song: songs){
            song.stop();
        }
    }
    
    public void pauseAllSongs()
    {
        for( GreenfootSound song: songs){
            song.pause();
        }
    }
    
    public int getCurrentIndex()
    {
        return currentSongIndex;
    }
    
    public ArrayList<GreenfootSound> getPlaylist()
    {
        return songs;
    }
}
