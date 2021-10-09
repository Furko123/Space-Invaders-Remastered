import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class PowerUp5x extends PowerUp
{
    public PowerUp5x()
    {
        GreenfootImage img = new GreenfootImage("powerUp5x.png");
        img.setTransparency(100);
        setImage(img);
    }
    
    public void act() 
    {
        activateOnClick();
        updateImage("powerUp5x.png");

        activeCounter++;

        if(activeCounter > 1000)
            active = false;
    }    
}