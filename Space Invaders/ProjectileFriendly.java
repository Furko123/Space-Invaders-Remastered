import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class ProjectileFriendly extends Actor
{
    private boolean toDestroy = false;
    private boolean block = true;
    private int projectileFriendlySpeed = 5;

    public ProjectileFriendly()
    {
        if(Menu.difficulty == 1)
            projectileFriendlySpeed = 15;
        else if(Menu.difficulty == 2)
            projectileFriendlySpeed = 10;
        else if(Menu.difficulty == 3)
            projectileFriendlySpeed = 5;                
    }

    public void act() 
    {
        setLocation(getX(), getY() - projectileFriendlySpeed); 
        hitAlien();
    }

    // löscht das Projektil eine act-Schritt nach einem Treffer (Alien/Rand/Bombe)
    protected void hitAlien()
    {
        if(isTouching(Alien.class) || isAtEdge() || isTouching(ProjectileBomb.class))
            toDestroy = true;

        if(toDestroy && !block)
            getWorld().removeObject(this);

        if(toDestroy && block)
            block = false;
    }

    public void setProjectileFriendlySpeed(int n)
    {
        projectileFriendlySpeed = n;
    }

    public int getProjectileFriendlySpeed()
    {
        return projectileFriendlySpeed;
    }
}