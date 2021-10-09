import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class AlienBomber extends Alien
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
    
    //Variablen für die (durch Schwierigkeit und Level bestimmten) Unterschiede. 
    String dif;
    int level;
    boolean differences = false;
    
    AlienBomber()
    {
        //Das Startbild der Bomber wird festgelegt.
        EdgeCheck.alienHasTouchedEdge = false;
        GreenfootImage img = new GreenfootImage("bomberRedStill.png");
        img.scale(img.getWidth() * scaleFactor / 2, img.getHeight() * scaleFactor / 2);
        setImage(img);
    }

    public void act() 
    {
        ifAtEdge();
        movement();
        shootBomb(projectileProbability);
        if(!differences) //Solange es noch keine Unterschiede zwischen den Level und Schwierigkeiten gibt...
            differences();
        vanishing();
    }  
    
    //In dieser Methode werden Leben, Bewegung nach unten und Schussgeschwindigkeit der Bomber festgelegt.
    private void differences()
    {
        dif = getWorldOfType(Game.class).getDifficulty(); //Schwierigkeit und Level aus der Game-Klasse werden übernommen.
        level = getWorldOfType(Game.class).getLevel();

        if(dif == "easy") //Wenn die Schwierigkeit 'easy' ist...
        {
            if(level == 1) //Wenn das Level '1' ist...
            {
                timesHit = 1; //Bedeutet, dass der Bomber schon einmal getroffen wurde, also "onehit" ist.
                movingDown = 5; //Bedeutet, dass der Bomber sich 5 Pixel nach unten bewegt, wenn er den Rand erreicht.
            }
            else if(level == 2)//Wenn das Level '2' ist...
            {
                timesHit = 1;
                movingDown = 5;
            }
            projectileProbability = 10000; //Bedeutet, dass der Bomber bei jedem act-Schritt mit einer 1/10000 Chance eine Bombe abfeuert.
            //Eine 'if-Bedingung' für das dritte Level entfällt, denn dort werden keine Aliens mehr erschaffen.
        }
        else if(dif == "normal") //Wenn die Schwierigkeit 'normal' ist...
        {
            if(level == 1) //Wenn das Level '1' ist...
            {
                timesHit = 1;
                movingDown = 5;
            }
            else if(level == 2) //Wenn das Level '2' ist...
            {
                timesHit = 0;
                movingDown = 10;
            }
            projectileProbability = 8000;
        }
        else if(dif == "hard") //Wenn die Schwierigkeit 'hard' ist...
        {
            if(level == 1) //Wenn das Level '1' ist...
            {
                timesHit = 0;
                movingDown = 10;
            }
            else if(level == 2) //Wenn das Level '2' ist...
            {
                timesHit = 0;
                movingDown = 10;
            }
            projectileProbability = 6000;
        }
        
        differences = true; //Jetzt gibt es Unterschiede und die Methode muss nicht mehr aufgerufen werden.
    }

    private void movement()
    {
        //Dieser Teil ist notwendig, damit sich die Bilder der Bomber beim Bewegen verändern.
        //Dies muss mithilfe eines Counters gelöst werden, da einzelne act-Schritte zu schnell hintereinander ablaufen.
        if(counter > 80) //Nach 80 Schritten wird der Counter wieder auf Null gesetzt.   
            counter = 0;
        else if(counter <= 40) //Nach 40 oder weniger Schritten ist der Bomber bewegungslos
        {
            if(timesHit == 0) //Wenn der Bomber kein einziges mal getroffen wurde, ändert er sein Bild zu...
                changeImage("bomberRedStill.png"); //Bild: rot, bewegungslos
            else if(timesHit == 1) //Wenn der Bomber ein einziges mal getroffen wurde, ändert er sein Bild zu...
                changeImage("bomberStill.png"); //Bild: weiß, bewegungslos
        }
        else if(counter > 40) //Nach mehr als 40 Schritten ist der Bomber bewegt, es gilt das gleiche Prinzip
        {
            if(timesHit == 0)
                changeImage("bomberRedMoving.png");
            else if(timesHit == 1)
                changeImage("bomberMoving.png");
        }   
        
        if(!touchedEdge) //Wenn kein Alien gerade den Rand berührt hat, soll sich der Bomber ganz normal (also um 'movementSteps') bewegen.
            move(movementSteps); //Wir benutzen eine Variable anstatt einer Zahl, da sich der Bomber auch in die andere Richtung bewegen muss, also um -movementSteps.
         
        //Das Ganze brauchen wir um zu verhindern, dass der äußerste Bomber seine "Position" verlässt. Der Grund dafür ist schwer in den Kommentaren
        //zu erklären, sie können uns aber gerne darauf ansprechen :).
        if(touchedLeftEdge && getX() == 4){ //Wenn der Bomber gerade die linke Seite berührt hat und seine x-Koordinate vier beträgt...
            move(-1); //Bewegt er sich einen Schritt nach links.
            touchedLeftEdge = false; //Wird die Variable 'touchedLeftEdge' wieder auf 'false' gesetzt.
        }
        
        counter = counter + 1;
    }

    //Sorgt dafür, dass die Bomber die Richtung wechseln, sich nach unten bewegen und ihre Position relativ zueinander behalten.
    private void ifAtEdge()
    {
        if(getX() == 598 || getX() == 2) //Variable = 'true', wenn der Bomber den Rand berührt.
            touchedEdge = true;
         
        if(EdgeCheck.alienHasTouchedEdge){ //Wenn der Bomber von der EdgeCheck-Klasse "gesagt" bekommmt, dass eines der Aliens der Rand berührt hat...    
            movementSteps = -movementSteps; //Richtungsänderung
            touchedEdge = false; //Zurücksetzen der Variablen
            setLocation(getX(), getY() + movingDown); //Bewegung nach unten
        }  
        
        if(getX() == 2 && touchedEdge == false) //Position des linken Bombers wird konstant gehalten.
            touchedLeftEdge = true;

    }
    
    private void vanishing()
    {
        if(isTouching(ProjectileFriendly.class)) //Falls der Bomber von einem Projektil getroffen wird, erhöht sich die timesHit Variable.
        {
            if(timesHit == 0) //Falls der Bomber rot ist, also zwei Leben hat, wird folgender Sound abgespielt:
                Greenfoot.playSound("break.mp3");
            timesHit += 1;
        }
            
        if(timesHit >= 2) //Falls der Bomber zweimal getroffen wurde...
        {
            getWorld().addObject(new Explosion(3, 2),getX(),getY() ); //Wird eine Explosion ausgelöst.
            Greenfoot.playSound("plop.mp3"); //Spielt ein Sound ab.
            getWorld().removeObject(this); //Verschwindet der Bomber.
        }
    }

    //Mit dieser Methode können wir alle unsere Bilder hochscalen, sparen also Zeit.
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