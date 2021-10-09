import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.util.*;

public class Difficulty extends Actor
{
    private String difficulty;
    private int scaleFactor = 4;

    public Difficulty(String dif)
    {
        difficulty = dif;
    }

    public void act()
    {
        hoverOverImage();
        startGame();
    }

    // färbt en Schriftzug im Menü, wenn die Maus auf ihn bewegt wird
    private void hoverOverImage()
    {
        boolean hovering = false;

        if(Greenfoot.mouseMoved(null)){
            if(hovering != Greenfoot.mouseMoved(this)){
                hovering = !hovering;
                if(hovering){
                    GreenfootImage img = new GreenfootImage(difficulty + "Colored.png");
                    img.scale(img.getWidth() * scaleFactor, img.getHeight() * scaleFactor);
                    setImage(img);          
                }
            }else{
                GreenfootImage img = new GreenfootImage(difficulty + "White.png");
                img.scale(img.getWidth() * scaleFactor, img.getHeight() * scaleFactor);
                setImage(img);
            }
        }
    }

    // startet einen Dialog und übergibt diesem die ausgewählte Schwierigkeit
    private void startGame()
    {
        if(Greenfoot.mouseClicked(this)){
            getWorldOfType(Menu.class).stopSong();
            Greenfoot.setWorld(new DialogueScreen(0, difficulty));
        }
    }
}