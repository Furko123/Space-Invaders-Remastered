import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ProjectileBomb extends Actor
{
    public ProjectileBomb()
    {
        GreenfootImage img = new GreenfootImage("projectileBomb.png");
        img.scale(img.getWidth() * 2, img.getHeight() * 2);
        setImage(img);
    }

    public void act()
    {
        // richtet Bombe auf Raumschiff aus
        move(1);
        if(Spaceship.getSpaceshipY() - 150 > getY()){
            turnTowards(Spaceship.getSpaceshipX(), Spaceship.getSpaceshipY());
        }
        
        // löscht Bombe, wenn sie den Rand oder das Raumschiff trifft oder abgeschossen wird
        if(isAtEdge() || isTouching(Spaceship.class) || isTouching(ProjectileFriendly.class)){
            getWorld().addObject(new Explosion(3, 3), getX(), getY());
        }
        
        if(isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }
}