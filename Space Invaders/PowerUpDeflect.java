import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class PowerUpDeflect extends PowerUp
{
    public PowerUpDeflect()
    {
        GreenfootImage img = new GreenfootImage("powerUpDeflect.png");
        img.setTransparency(100);
        setImage(img);
    }
    
    public void act()
    {
        activateOnClick();
        updateImage("powerUpDeflect.png");

        activeCounter++;

        if(activeCounter > 1000)
            active = false;
    }    
}
