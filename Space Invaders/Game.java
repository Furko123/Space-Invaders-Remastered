import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class Game extends World
{
    private String difficulty;
    private int level;
    protected static int staticLevel;
    protected static String staticDifficulty;
    private GreenfootSound backgroundMusic;

    //Konstruktor
    public Game(String dif, int levelgiven)
    {    
        //Wir erstellen eine Spielwelt 'Game', der die Schwierigkeit und das Level von dem DialogueScreen übergeben werden. In Abhängigkeit
        //von diesen Werten werden dann verschiedene Methoden aufgerufen.
        super(600, 800, 1);        
        difficulty = dif;        
        level = levelgiven;
        staticLevel = level;
        staticDifficulty = difficulty;
        prepare();
    }

    //In der act-Methode wird die startDialogue-Methode aufgerufen.
    public void act()
    {
       startDialogue();
    }
    
    private void prepare()
    {
        Greenfoot.setSpeed(58); //Die Geschwindigkeit des Spieles wird festgelegt.
        setBackground(blackBackground()); //Der schwarze Hintergrund wird erschaffen.

        prepareSpaceship(); //Das Spaceship wird erschaffen.
        if(level == 1 || level == 2) //Wenn wir im ersten oder zweiten Level sind werden die Aliens ganz normal erschaffen.
            prepareAliens();
        else if(level == 3) //Wenn wir im dritten Level (dem Bosskampf) sind, wird der Endkampf vorbereitet.
            prepareEndFight();
        prepareMusic(); //Die Musik startet.
    }

    private void prepareSpaceship()
    {
            PowerUp2x p2 = new PowerUp2x();
            PowerUpShield pS = new PowerUpShield();
            PowerUpDeflect pD = new PowerUpDeflect();
            PowerUp5x p5 = new PowerUp5x();
        if(level == 1)
        {
            addObject(p2, 480, 750);
            addObject(pS, 550, 750);
        }
        else if(level == 2)
        {
            addObject(p2, 480, 750);
            addObject(pS, 550, 750);
            addObject(pD, 410, 750);
        }
        else
        {
            addObject(p2, 480, 750);
            addObject(pS, 550, 750);
            addObject(pD, 410, 750);
            addObject(p5, 340, 750);
        }
    
        Spaceship player = new Spaceship(p2, pS, p5);
        addObject(player, getWidth() / 2, 700);

        Shield s = new Shield(player, pS);
        addObject(s, getWidth() / 2, 700);
        
        Deflector d = new Deflector(player, pD);
        addObject(d, getWidth() / 2, 700);
    }

    //Die Aliens werden erschaffen.
    private void prepareAliens() 
    {
        prepareAlienBombers();
        prepareAlienSquishies();
        prepareAlienTanks();
    }

    private void prepareAlienBombers()
    {
        for(int i = 10; i > 0; i = i - 1) //Zehn Bomber werden erschaffen.      
            addObject(new AlienBomber(), 50 + i * 50, 100);
        
        addObject(new EdgeCheck(), 0, 0); //Eine Objekt der EdgeCheck-Klasse wird erschaffen, dies muss nur einmal geschehen.
    }
    
    private void prepareAlienSquishies()
    {
        for(int i = 10; i > 0; i = i - 1) //Zehn Squishies werden erschaffen.     
            addObject(new AlienSquishy(), 50 + i * 50, 150);
    }
    
    private void prepareAlienTanks()
    {
        for(int i = 5; i > 0; i = i - 1) //Fünf Tanks werden erschaffen.      
            addObject(new AlienTank(), 25 + i * 100, 200);
    }
    
    protected void prepareEndFight()
    {
        addObject(new EndBoss(), getWidth() / 2, 100); //Der Endboss wird erschaffen.
        addObject(new Healthbar(), getWidth() / 2, 20); //Die Lebensanzeige wird erschaffen.
        addObject(new EdgeCheck(), 0, 0); //Ein Objekt der EdgeCheck-Klasse wird erschaffen.
        prepareEvilShips(); //Die Evilships werden erschaffen.
    }
    
    protected void prepareEvilShips()
    {
        addObject(new EvilShip(), 50, 50);
        addObject(new EvilShip(), 125, 0);
        addObject(new EvilShip(), 200, 50);
        addObject(new EvilShip(), 400, 50);
        addObject(new EvilShip(), 475, 0);
        addObject(new EvilShip(), 550, 50);
    }
    
    private void prepareMusic()
    {
       if(level == 1 || level == 2)
       {
           backgroundMusic = new GreenfootSound("level1and2DeletedMelody.mp3"); // Platzhalter
           backgroundMusic.setVolume(50);
           backgroundMusic.playLoop();
       }
       else
       {
           backgroundMusic = new GreenfootSound("bossBattle.mp3");
           backgroundMusic.playLoop();
       }
    }

    private GreenfootImage blackBackground()
    {
        GreenfootImage img = new GreenfootImage(getWidth(), getHeight());
        img.setColor(Color.BLACK);
        img.fill();
        return img;
    }
    
    private void startDialogue()
    {
        if(getObjects(Alien.class).size() == 0)
        {
            stopMusic();
            Greenfoot.setWorld(new DialogueScreen(level, difficulty));
        }
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public String getDifficulty()
    {
        return difficulty;
    }
    
    protected static int returnStaticLevel()
    {
        return staticLevel;
    }
    
    protected static String returnStaticDifficulty()
    {
        return staticDifficulty;
    }
    
    public void stopMusic()
    {
        backgroundMusic.stop();
    }
}