import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class PowerUpShield extends PowerUp
{
    public PowerUpShield()
    {
        GreenfootImage img = new GreenfootImage("powerUpShield.png");
        img.setTransparency(100);
        setImage(img);
    }

    public void act()
    {
        activateOnClick();
        updateImage("powerUpShield.png");

        activeCounter++;
        
        if(activeCounter > 1000)
            active = false;
    }
    
    public void setActive(boolean b)
    {
        active = b;
    }
    
    public void resetActiveCounter()
    {
        activeCounter = 1000;
    }
}