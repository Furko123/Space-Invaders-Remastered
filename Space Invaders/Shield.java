import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Shield extends Actor
{
    private int scaleFactor = 4;
    private int shieldDurability = 0;
    private Spaceship spaceship;
    private PowerUpShield powerUpShield;

    public Shield(Spaceship player, PowerUpShield pS)
    {
        GreenfootImage img = new GreenfootImage("shield.png");
        img.scale(img.getWidth() * scaleFactor, img.getHeight() * scaleFactor);
        img.setTransparency(0);
        setImage(img);

        spaceship = player;
        powerUpShield = pS;
    }

    public void act() 
    {
        // hält den Schild über dem Raumschiff
        setLocation(spaceship.getX(), 685);

        // je nachdem ob das Power-up aktiviert wird, wird der Schild sichtbar gemacht oder versteckt
        if(powerUpShield.getActive() == true){
            GreenfootImage img = getImage();
            img.setTransparency(255);
            setImage(img);
        }else{
            GreenfootImage img = getImage();
            img.setTransparency(0);
            setImage(img);
        }

        // löscht Aliens, die den Schild berühren, nach 3 Aliens wird der Schild zerstört.
        if(isTouching(Alien.class) && powerUpShield.getActive())
        {
            removeTouching(Alien.class);
            shieldDurability = shieldDurability + 1;
            if(shieldDurability == 3){
                powerUpShield.resetActiveCounter();
                powerUpShield.setActive(false);
            }
        }

        // zerstört den Schild, wenn er von einer Bombe oder dem Laser des Endboss getroffen wird
        if((isTouching(ProjectileBomb.class) || isTouching(Laser.class)) && powerUpShield.getActive())
        {
            removeTouching(ProjectileBomb.class);
            shieldDurability = 3;
            if(shieldDurability == 3){        
                powerUpShield.resetActiveCounter();
                powerUpShield.setActive(false);
            }
        }
    }    
}
