import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class PowerUp2x extends PowerUp
{
    public PowerUp2x()
    {
        GreenfootImage img = new GreenfootImage("powerUp2x.png");
        img.setTransparency(100);
        setImage(img);
    }
    
    public void act() 
    {
        activateOnClick();
        updateImage("powerUp2x.png");

        activeCounter++;

        if(activeCounter > 1000)
            active = false;
    }    
}