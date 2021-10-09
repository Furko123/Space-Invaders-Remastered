import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Explosion extends Actor
{
    GreenfootImage explosion;
    GreenfootImage explosion2 = new GreenfootImage("explosionNormal.png");
    GreenfootImage explosion3 = new GreenfootImage("explosionBomb.png");
    private int scaleFactor = 1;
    private int counter = 0;
    private int scale;
    
    //Im Konstruktor muss man Größe und Art der Explosion angeben.
    public Explosion(int s, int type)
    {
        scale = s;
        if(type == 2)
            explosion = explosion2; //Das ist die normale Explosion.
        if(type == 3)
            explosion = explosion3; //Und das ist die normale Explosion, nur in hellblau.
        explosion.scale(13,7);
        setImage(explosion);
    }
    
    public void act() 
    {
        scale();
    } 
    
    //So wird die Explosion schrittweise vergrößert.
    private void scale()
    {
        if(counter == 10) //Wird im Counter bis 10 gezählt, erhöht sich der 'scaleFactor' und der Counter beginnt wieder von vorne. 
        {
            scaleFactor += 1;
            counter = 0;
        }
        counter += 1; //Erhöhung des Counters;
        
        explosion.scale(13 * scaleFactor, 7 * scaleFactor); //Die Größe des Bildes wird mit dem 'scaleFactor' multipliziert.
        if(scaleFactor == scale) //Wenn der 'scaleFactor' gleich der 'scale' ist, wird die Explosion gelöscht.
            getWorld().removeObject(this);
    }
}
