import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class UFO extends Actor
{
    UFO()
    {
        GreenfootImage img = new GreenfootImage("ufo.png");
        img.scale(img.getWidth() * 7 / 2, img.getHeight() * 7 / 2);
        setImage(img);
    }
    
    public void act() 
    {
        move(2); //Das Ufo bewegt sich zwei Pixel.
        if(getX() >= 599) //Wenn das Ufo am rechten Rand ist...
            setLocation(1, Greenfoot.getRandomNumber(800)); //Taucht es am linken Rand mit zufälliger y-Koordinate wieder auf.
            
        if(Greenfoot.mouseClicked(this)) //Easter-Egg :).
            Greenfoot.playSound("plop.mp3");
    }    
}
