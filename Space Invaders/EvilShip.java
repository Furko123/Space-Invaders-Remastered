import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EvilShip extends Alien
{
    private int movementSteps = 1;
    public static boolean touchedEdge = false;
    private int scaleFactor = 7;
    private int counter = 1;
    private boolean touchedLeftEdge = false;
    private boolean touchedRightEdge = false;
    private boolean edgeBlock = false;
    private int goDownCounter = 0;
    private int timesHit = 0; 
    private int movingDown = 4;
    private boolean reachedPos = false;
    GreenfootImage img;
    
    long startTime;
    long endTime;
    
    EvilShip()
    {
        EdgeCheck.alienHasTouchedEdge = false;
        
        img = new GreenfootImage("spaceshipPowerUp.png");
        img.scale(img.getWidth() * scaleFactor / 2, img.getHeight() * scaleFactor / 2);
        img.setTransparency(0);
        setImage(img);
        
        int opacity = 0;
    }
    
    public void act() 
    {
        if(reachedPos == false)
            goToStartPos();
        if(reachedPos)
        {
            ifAtEdge();
            movement();
            shootProjectile(800);
            vanishing();
        }
    }
    
    private void movement()
    {
        if(timesHit == 0)
            changeImage("spaceshipPowerUp.png"); 
        else if(timesHit == 1)
            changeImage("spaceshipPowerUpBroken.png"); //Wenn ein Schiff einmal getroffen wird ändert es sein Bild. 
        
        //Gleiches Prinzip wie in den Alien-Klassen. 
        if(!touchedEdge)
            move(movementSteps);
            
        if(touchedLeftEdge && getX() == 4){
            move(0);
            touchedLeftEdge = false;
        }
        
        if(touchedRightEdge && getX() == 596){
            move(1);
            touchedRightEdge = false;
        }
    }

    private void ifAtEdge()
    {
        if(getX() == 598 || getX() == 2)   
            touchedEdge = true;
         
        if(EdgeCheck.evilShipHasTouchedEdge){        
            movementSteps = -movementSteps;
            touchedEdge = false;
            setLocation(getX(), getY() + movingDown);
        }  
        
        if(getX() == 2 && touchedEdge == false)
            touchedLeftEdge = true;

        if(getX() == 598 && touchedEdge == false)
            touchedRightEdge = true;
    }
    
    public void changeImage(String imgName)
    {
        GreenfootImage img = new GreenfootImage(imgName);
        img.scale(img.getWidth() * scaleFactor / 2, img.getHeight() * scaleFactor / 2);
        setImage(img);
    }
    
    //Diese Methode sorgt dafür, dass die Schiffe am Magier vorbei nach unten ins Bild fliegen. 
    private void goToStartPos()
    {
        int opacity = 0;
        
        for(int i = 0; i < 200; i++)
        {   
            setLocation(getX(), getY() + 1); //Das Schiff geht einen Pixel nach unten.
            opacity += 1; //Das Schiff wird sichtbarer.
            if(i >= 189) //Das Schiff wird gegen Ende hin schneller sichtbar.
                opacity += 4;
            img.setTransparency(opacity);
            setImage(img);
            if(i % 5 == 0) //Durch den Delay wirkt die Bewegung flüssiger.
                Greenfoot.delay(1);
        }
        reachedPos = true; //Sorgt dafür, dass die Schiffe sich danach ganz normal bewegen.
    }
    
    private void vanishing()
    {
        if(isTouching(ProjectileFriendly.class))
        {
            if(timesHit == 0)
                Greenfoot.playSound("break.mp3");
            timesHit += 1;
        }
            
        if(timesHit >= 2)
        {
            getWorld().addObject(new Explosion(3, 2),getX(),getY() );
            Greenfoot.playSound("plop.mp3");
            getWorld().removeObject(this);
        }
    }
}
