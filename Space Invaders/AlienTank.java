import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)


//Diese Klasse gleicht sich größtenteils mit der AlienBomber-Klasse, es gibt jedoch einige kleine Unterschiede.
public class AlienTank extends Alien
{
    private int movementSteps = 1;
    public static boolean touchedEdge = false;
    private int scaleFactor = 7;
    private int counter = 1;
    private boolean touchedLeftEdge = false;
    private boolean edgeBlock = false;
    private int goDownCounter = 0;
    private int timesHit = 0;
    private int movingDown = 10;
    private int projectileProbability = 1600;
    
    String dif;
    int level;
    boolean differences = false;
    
    AlienTank()
    {
        EdgeCheck.alienHasTouchedEdge = false;
        GreenfootImage img = new GreenfootImage("tankStill.png");
        img.scale(img.getWidth() * scaleFactor / 2, img.getHeight() * scaleFactor / 2);
        setImage(img);
    }

    public void act() 
    {
        ifAtEdge();
        movement();
        shootProjectile(projectileProbability);
        if(!differences)
            differences();
        vanishing();
    }  
    
    public void differences()
    {
        dif = getWorldOfType(Game.class).getDifficulty();
        level = getWorldOfType(Game.class).getLevel();

        if(dif == "easy")
        {
            if(level == 1)
            {
                timesHit = 2;
                movingDown = 5;
                
            }
            else if(level == 2)
            {
                timesHit = 1;
                movingDown = 5;
                
            }
            projectileProbability = 3000;
        }
        else if(dif == "normal")
        {
            if(level == 1)
            {
                timesHit = 1;
                movingDown = 5;
                
            }
            else if(level == 2)
            {
                timesHit = 1;
                movingDown = 10;
                
            }
            projectileProbability = 2000;
        }
        else if(dif == "hard")
        {
            if(level == 1)
            {
                timesHit = 1;
                movingDown = 10;
                
            }
            else if(level == 2)
            {
                timesHit = 0;
                movingDown = 10;
                
            }
            projectileProbability = 1500;
        }
        
        differences = true;
    }

    private void movement()
    {
        if(counter > 80)        
            counter = 0;
        else if(counter <= 40)
        {
            if(timesHit == 0)
                changeImage("tankRedStill3.png"); //Bei den Tanks gibt es, im Gegensatz zu den anderen beiden Alien-Klassen, drei Zustände:
            else if(timesHit == 1)
                changeImage("tankRedStill2.png");
            else if(timesHit == 2)
                changeImage("tankStill.png");
        }
        else if(counter > 40)
        {
            if(timesHit == 0)
                changeImage("tankRedMoving3.png"); //Unter bestimmten Bedingungen muss der Tank dreimal getroffen werden, um zu verschwinden.
            else if(timesHit == 1)
                changeImage("tankRedMoving2.png");
            else if(timesHit == 2)
                changeImage("tankMoving.png");
        }          
               
        if(!touchedEdge)
            move(movementSteps);
            
        if(touchedLeftEdge && getX() == 4){
            move(-1);
            touchedLeftEdge = false;
        }
        
        counter = counter + 1;
    }

    private void ifAtEdge()
    {
        if(getX() == 598 || getX() == 2)   
            touchedEdge = true;
         
        if(EdgeCheck.alienHasTouchedEdge){        
            movementSteps = -movementSteps;
            touchedEdge = false;
            setLocation(getX(), getY() + movingDown);
        }  
        
        if(getX() == 2 && touchedEdge == false)
            touchedLeftEdge = true;

    }
    
    private void vanishing()
    {
        if(isTouching(ProjectileFriendly.class))
        {
            if(timesHit == 0 || timesHit == 1)
                Greenfoot.playSound("break.mp3");
            timesHit += 1;
        }
    
        if(timesHit >= 3)
        {
            getWorld().addObject(new Explosion(3, 2),getX(),getY() );
            Greenfoot.playSound("plop.mp3");
            getWorld().removeObject(this);
        }
    }

    public void changeImage(String imgName)
    {
        GreenfootImage img = new GreenfootImage(imgName);
        img.scale(img.getWidth() * scaleFactor / 2, img.getHeight() * scaleFactor / 2);
        setImage(img);
    }

    public static boolean touchedEdge()
    {
        return touchedEdge;
    }
    
    public void setMovingDown(int n)
    {
        movingDown = n;
    }
    
    public int getMovingDown()
    {
        return movingDown;
    }
}