import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class Menu extends World
{
    Difficulty easy = new Difficulty("easy");
    Difficulty normal = new Difficulty("normal");
    Difficulty hard = new Difficulty("hard");
    public static int difficulty = 0;
    GreenfootSound sound = new GreenfootSound("intromusic.mp3");
    private GreenfootSound menuMusic;
    
    public Menu()
    {    
        super(600, 800, 1);
        Greenfoot.start();
        menuMusic = new GreenfootSound("stack.mp3");
        menuMusic.playLoop();
        prepare();
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(easy)){
            difficulty = 1;
            sound.stop();
        }else if(Greenfoot.mouseClicked(normal)){
            difficulty = 2;
            sound.stop();
        }else if(Greenfoot.mouseClicked(hard)){
            difficulty = 3;
            sound.stop();
        }
    }
    
    private void prepare()
    {
        Greenfoot.setSpeed(50);        
        setBackground(blackBackground());
        addObject(new UFO(), 1, Greenfoot.getRandomNumber(800));
        prepareDifficultyButtons();
    }

    private void prepareDifficultyButtons()
    {
        addObject(easy, getWidth() / 2, 250);
        addObject(normal, getWidth() / 2, 400);
        addObject(hard, getWidth() / 2, 550);
    }
    
    private GreenfootImage blackBackground()
    {
        GreenfootImage img = new GreenfootImage(getWidth(), getHeight());
        img.setColor(Color.BLACK);
        img.fill();
        return img;
    }
    
    public void stopSong()
    {
        menuMusic.stop();
    }
}