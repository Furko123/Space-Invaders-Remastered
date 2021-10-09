import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class Spaceship extends Actor
{
    private int scaleFactor = 4;
    private int shootCooldown;
    private boolean ableToShoot = true;
    private PowerUp2x powerUp2x;
    private PowerUpShield powerUpShield;
    private PowerUp5x powerUp5x;
    private int spaceShipSpeed = 3;
    private int shootingSpeed = 80;
    private static int spaceshipX;
    private static int spaceshipY;
    private boolean gotStunned = false;
    private int counter = 0;

    public Spaceship(PowerUp2x p2, PowerUpShield pS, PowerUp5x p5)
    {
        GreenfootImage img = new GreenfootImage("spaceship.png");
        img.scale(img.getWidth() * scaleFactor, img.getHeight() * scaleFactor);
        setImage(img);

        powerUp2x = p2;
        powerUpShield = pS;
        powerUp5x = p5;

        if(Menu.difficulty == 1)
            shootingSpeed = 40;
        else if(Menu.difficulty == 2)
            shootingSpeed = 60;
        else if(Menu.difficulty == 3)
            shootingSpeed = 80;
    }

    public void act() 
    {        
        movement();
        shoot();
        stunned();
        death();

        spaceshipX = getX();
        spaceshipY = getY();
    }

    private void shoot()
    {
        if(Greenfoot.isKeyDown("space") && ableToShoot){
            getWorld().addObject(new ProjectileFriendly(), getX(), getY() -  getImage().getHeight() / 2);
            ableToShoot = false;
            if(powerUp2x.getActive())
                shootCooldown = shootingSpeed / 2;
            else
                shootCooldown = shootingSpeed;
            if(powerUp5x.getActive()){
                getWorld().addObject(new ProjectileFriendly(), getX(), getY() - getImage().getHeight() / 2);
                getWorld().addObject(new ProjectileFriendly(), getX() + 10, getY() - getImage().getHeight() / 2);
                getWorld().addObject(new ProjectileFriendly(), getX() - 10, getY() - getImage().getHeight() / 2);
                getWorld().addObject(new ProjectileFriendly(), getX() + 20, getY() - getImage().getHeight() / 2);
                getWorld().addObject(new ProjectileFriendly(), getX() - 20, getY() - getImage().getHeight() / 2);
                ableToShoot = false;
            }
        }

        shootCooldown--;

        if(shootCooldown == 0)
            ableToShoot = true;
    }

    private void movement()
    {
        if((Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) && getX() > getImage().getWidth() / 2 && !gotStunned)
            setLocation(getX() - spaceShipSpeed, 700);

        if((Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) && getX() < getWorld().getWidth() - getImage().getWidth() / 2  && !gotStunned)
            setLocation(getX() + spaceShipSpeed, 700);        
    }

    private void death()
    {
        if((isTouching(ProjectileHostile.class) == true || isTouching(Alien.class) || isTouching(Laser.class)) && powerUpShield.getActive() == false){
            getWorldOfType(Game.class).stopMusic();
            Greenfoot.setWorld(new Game(getWorldOfType(Game.class).getDifficulty(), getWorldOfType(Game.class).getLevel()));
        }
    }

    private void stunned()
    {
        if(isTouching(ProjectileBomb.class) == true && powerUpShield.getActive() == false){
            changeImage("spaceshipStunned.png");
            getWorld().removeObject(getOneIntersectingObject(ProjectileBomb.class));
            gotStunned = true;
        }

        if(gotStunned)
            counter += 1;

        if(counter >= 100){  
            changeImage("spaceship.png");
            counter = 0;
            gotStunned = false;
        } 
    }

    public void changeImage(String imgName)
    {
        GreenfootImage img = new GreenfootImage(imgName);
        img.scale(img.getWidth() * scaleFactor, img.getHeight() * scaleFactor);
        setImage(img);
    }

    public void setSpeed(int n)
    {
        spaceShipSpeed = n;
    }

    public int getSpeed()
    {
        return spaceShipSpeed;
    }

    public void setShootingSpeed(int n)
    {
        shootingSpeed = n;
    }

    public int getShootingSpeed()
    {
        return shootingSpeed;
    }

    public static int getSpaceshipX()
    {
        return spaceshipX;
    }

    public static int getSpaceshipY()
    {
        return spaceshipY;
    }
}