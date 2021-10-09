import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EdgeCheck extends Actor
{
    public static boolean alienHasTouchedEdge = false;
    public static boolean evilShipHasTouchedEdge = false;

    public EdgeCheck()
    {
        AlienBomber.touchedEdge = false;
        AlienSquishy.touchedEdge = false;
        AlienTank.touchedEdge = false;
        EvilShip.touchedEdge = false;
        
        GreenfootImage img = new GreenfootImage("squishyStill.png"); //Wir nehmen ein beliebiges Bild und machen es dann durchsichtig.
        img.setTransparency(0);
        setImage(img);
    }

    public void act() 
    {
        checkAliens();
        checkEvilShips();
    }   

    //Wenn eines der Alien-Objekte den Rand berührt, gibt das diese Methode an alle Alien-Objekte zurück, sodass alle ihre Richtung wechseln, etc.
    private void checkAliens()
    {
        if(AlienBomber.touchedEdge || AlienSquishy.touchedEdge || AlienTank.touchedEdge) 
            alienHasTouchedEdge = true;
        else
            alienHasTouchedEdge = false;
    }
    public static boolean alienHasTouchedEdge()
    {
        return alienHasTouchedEdge;
    }

    //Wenn eines der EvilShip-Objekte den Rand berührt, gibt dass diese Methode an alle EvilShip-Objekte zurück, sodass alle ihre Richtung wechseln, etc.
    private void checkEvilShips()
    {
        if(EvilShip.touchedEdge == true)
            evilShipHasTouchedEdge = true;
        else
            evilShipHasTouchedEdge = false;
    }
    public static boolean evilShipHasTouchedEdge()
    {
        return evilShipHasTouchedEdge;
    }
}
