import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EndBoss extends Alien
{
    protected static int health = 100;
    private int extraTime;
    
    //Variablen um EvilShips zu erschaffen.
    long startTime;
    long endTime;
    long startTimeImg;
    
    //Variablen, um Laser zu erschaffen.
    boolean shootLaser = false;
    boolean shootLaserStart = false;
    
    long startTimeLaser;
    long endTimeLaser;
    
    //Variablen, um Laser zu löschen.
    boolean shotLaser = false;
    boolean shotLaserStart = false;
    
    long startTimeRemoval = System.currentTimeMillis();
    long endTimeRemoval = System.currentTimeMillis();
    
    //Variablen, um zwischen Schwierigkeiten zu unterscheiden
    String dif;
    boolean differences = false;

    EndBoss()
    {
        startTime = System.currentTimeMillis(); //Timer
        startTimeImg = startTime;
    }
    
    public void act() 
    {
        //&& getWorld().getObjects(EvilShip.class).size() != 0)
        if(Greenfoot.getRandomNumber(1000) == 0) //Mit einer Wahrscheinlichkeit von 1/1000 schießt der Magier seinen Laser ab;
        {
            shootLaser = true;
            shootLaserStart = true;
        }
        
        differences();
        laser(); 
        hitWizard();
        createShips();
        removeLaser();
        
        
    }   
    
    private void differences()
    {
        if(!differences)
        {
            dif = getWorldOfType(Game.class).getDifficulty();

            if(dif == "easy")
            {
                extraTime = 4000; //Leben des Magiers und Abstand zwischen neuen Wellen an Gegner werden verändert.
                health = 50; 
            }
            else if(dif == "normal")
            {
                extraTime = 2000;
                health = 75;
            }
            else if(dif == "hard")
            {
                extraTime = 0;
                health = 100;
            }

            differences = true;
        }
    }
        
    private void createShips()
    {
        endTime = System.currentTimeMillis();
        if((endTime - startTimeImg) > (14000 + extraTime) && (endTime - startTimeImg) < (18000 + extraTime))
            setImage("wizardEvil.png"); //Der Magier wird böse, wenn eine neue Welle bevorsteht.
        else
        {
            startTimeImg = startTime; //Danach wird der Magier wieder normal.
            setImage("wizardGood.png");
        }
        
        //Eine neue Welle an Gegner wird erschaffen.
        if((endTime - startTime) > (15000 + extraTime))
        {
            getWorld().addObject(new EvilShip(), 50, 50);
            getWorld().addObject(new EvilShip(), 125, 0);
            getWorld().addObject(new EvilShip(), 200, 50);
            getWorld().addObject(new EvilShip(), 400, 50);
            getWorld().addObject(new EvilShip(), 475, 0);
            getWorld().addObject(new EvilShip(), 550, 50);
            
            startTime = System.currentTimeMillis();
        }
        //Ich benutze zwei Variablen für die Startzeit, weil 'startTime' früher zurückgesetzt wird als 'startTimeImg'.
    }
    
    public void laser()
    {
        //Falls der Magier einen Laser abschießen soll...
        if(shootLaser)
        { 
            if(shootLaserStart)
            {
                startTimeLaser = System.currentTimeMillis(); //Der Timer startet.
                endTimeLaser = System.currentTimeMillis();
                Greenfoot.playSound("laser.mp3"); //Ein Sound wird abgespielt.
                shootLaserStart = false; //Es wird dafür gesorgt, dass diese Bedingung nicht mehr erfüllt ist, der Ton also z.B. nicht in Dauerschleife abgespielt wird.
                setImage("wizardShooting.png"); //Das Bild ändert sich.
            }
            if((endTimeLaser - startTimeLaser) > 1000) //Nach einer Sekunde wird der Laser erschaffen.
            {
                int y = 185;
                getWorld().addObject(new Laser("laserRed.png"), getWorld().getWidth() / 2, y);
                getWorld().addObject(new Laser("laserRed.png"), getWorld().getWidth() / 2, y + 20*3);
                getWorld().addObject(new Laser("laserRed.png"), getWorld().getWidth() / 2, y + 40*3);
                getWorld().addObject(new Laser("laserRed.png"), getWorld().getWidth() / 2, y + 60*3);
                getWorld().addObject(new Laser("laserRed.png"), getWorld().getWidth() / 2, y + 80*3);
                getWorld().addObject(new Laser("laserRed.png"), getWorld().getWidth() / 2, y + 100*3);
                getWorld().addObject(new Laser("laserRed.png"), getWorld().getWidth() / 2, y + 120*3);
                getWorld().addObject(new Laser("laserRed.png"), getWorld().getWidth() / 2, y + 140*3);
                getWorld().addObject(new Laser("laserEnd.png"), getWorld().getWidth() / 2, y + 160*3);
                
                //Variablen werden zurückgesetzt / so geändert, dass die removeLaser-Methode den Laser wieder entfernen kann.
                shotLaser = true;
                shotLaserStart = true;
                shootLaser = false;
            }
            endTimeLaser = System.currentTimeMillis();
        }
    }
    
    public void removeLaser()
    {
        if(shotLaser) //Wenn ein Laser abgeschossen wurde...
        { 
            if(shotLaserStart)
            {
                startTimeRemoval = System.currentTimeMillis(); //Der Timer startet.
                endTimeRemoval = System.currentTimeMillis();
                shotLaserStart = false; //Es wird dafür gesorgt, dass diese Bedingung nicht mehr 'true' ist.
            }
            if((endTimeRemoval - startTimeRemoval) > 1000) //Nach einer Sekunde...
            {
                shotLaser = false; //Werden die Variablen zurückgesetzt.
                getWorld().removeObjects(getWorld().getObjects(Laser.class)); //Werden die Laser gelöscht.
                setImage("wizardGood.png"); //Setzt sich das Bild zurück.
            }
            endTimeRemoval = System.currentTimeMillis();
        }
    }
    
    private void hitWizard() 
    {
        if(isTouching(ProjectileFriendly.class) && getWorld().getObjects(EvilShip.class).size() == 0) //Wenn der Magier abgeschossen wird + keine Schiffe mehr existieren...
        {
            health--; //Ein Lebenspunkt wird abgezogen.
        }
        if(health <= 0) //Wenn der Magier keine Leben mehr hat...
        {
            getWorld().addObject(new Explosion(7, 2),getX(),getY()); //Eine Explosion wird ausgelöst.
            getWorld().removeObject(this); //Der Magier verschwindet.
        }
    }
    
}
