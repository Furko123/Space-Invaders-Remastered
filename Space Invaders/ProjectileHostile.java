import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ProjectileHostile extends Actor
{
    public ProjectileHostile()
    {
        GreenfootImage img = new GreenfootImage("projectileHostile.png");
        img.scale(img.getWidth(), img.getHeight());
        setImage(img);
    }
    
    public void act() 
    {
        setLocation(getX(), getY() + 3);
        checkForCollision();
    }
    
    private void checkForCollision()
    {
        Actor shield = getOneIntersectingObject(Shield.class);
        Actor deflector = getOneIntersectingObject(Deflector.class);
        
        // löscht das feindliche Projektil und erschafft ein freundliches an der gleichen Stelle
        if(isTouching(Deflector.class) && deflector.getImage().getTransparency() == 255){
            getWorld().addObject(new ProjectileFriendly(), getX(), getY());
            getWorld().removeObject(this);
        // löscht das feindliche Projektil, wenn es den Rand oder den aktiven Schild berührt
        }else if(isAtEdge() || (isTouching(Shield.class) && shield.getImage().getTransparency() == 255))
            getWorld().removeObject(this);
    }
}
