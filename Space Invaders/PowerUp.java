import greenfoot.*;

public class PowerUp extends Actor
{
    protected boolean active;
    protected int activeCounter;
    private boolean installed;
    private int powerUpIndex;

    public PowerUp()
    {
        active = false;
        activeCounter = 3000;
    }

    // aktiviert ein Power-up, wenn auf es geklickt wird
    protected void activateOnClick()
    {
        if(Greenfoot.mouseClicked(this) && !active && activeCounter > 3000){
            active = true;
            activeCounter = 0;
        }
    }

    // aktualisiert die Darstellung der Power-Ups
    protected void updateImage(String powerUpImage)
    {
        GreenfootImage img = getImage();
        GreenfootImage count = setPowerUpIndex();
        GreenfootImage original = new GreenfootImage(powerUpImage);

        // leert das Bild, fügt das Original neu ein, legt Transparenz fest und fügt Index hinzu
        img.clear();
        img.drawImage(original, 0, 0);
        if(active)
            img.setTransparency(255);
        else
            img.setTransparency(130);
        img.drawImage(count, 0, 25);
        setImage(img);
    }

    // gibt ein GreenfootImage zurück, das den Cooldown bzw. die Restdauer eines Power-ups enthält
    private GreenfootImage setPowerUpIndex()
    {
        Color transparent = new Color(255, 255, 255, 0);
        
        // Index für Dauer nach Aktivierung
        if(activeCounter > 0 && activeCounter < 1000){
            powerUpIndex = (activeCounter) * -1 / 100 + 10;
            GreenfootImage count = new GreenfootImage("" + powerUpIndex, 30, Color.GREEN, transparent, Color.BLACK);
            return count;
        // Index für Cooldown nach Benutzung
        }else if(activeCounter >= 1000 && activeCounter < 3000){
            powerUpIndex = (activeCounter) * -1 / 100 + 30;
            GreenfootImage count = new GreenfootImage("" + powerUpIndex, 30, Color.RED, transparent, Color.BLACK);
            return count;
        // Index für Bereitschaft nach Cooldown
        }else{
            GreenfootImage count = new GreenfootImage("0", 30, Color.GREEN, transparent, Color.BLACK);
            return count;
        }
    }

    public boolean getActive()
    {
        return active;
    }
}