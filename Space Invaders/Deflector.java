import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Deflector extends Actor
{
    private Spaceship spaceship;
    private PowerUpDeflect powerUpDeflect;

    // legt Grafik fest
    public Deflector(Spaceship player, PowerUpDeflect pD)
    {
        GreenfootImage img = new GreenfootImage("deflector.png");
        img.scale(img.getWidth(), img.getHeight());
        img.setTransparency(0);
        setImage(img);

        spaceship = player;
        powerUpDeflect = pD;
    }
    
    public void act() 
    {
        // hält den Deflektor über dem Raumschiff
        setLocation(spaceship.getX(), 650);
        
        // je nachdem ob das Power-up aktiviert wird, wird der Deflektor sichtbar gemacht oder versteckt
        if(powerUpDeflect.getActive() == true){
            GreenfootImage img = getImage();
            img.setTransparency(255);
            setImage(img);
        }else{
            GreenfootImage img = getImage();
            img.setTransparency(0);
            setImage(img);
        }
    }    
}
