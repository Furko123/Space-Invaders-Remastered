import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class Alien extends Actor
{
    //Diese Methode erschafft die normalen Projektile, welche von den Aliens abgeschossen werden.
    protected void shootProjectile(int n)
    {
        if(Greenfoot.getRandomNumber(n) == 0) //Mit der Variable n lässt sich die Wahrscheinlichkeit, dass ein Projektil erschaffen wird, einstellen.
            getWorld().addObject(new ProjectileHostile(), getX(), getY());
    }

    //Diese Methode erschafft die Bomben, welche zum Spieler fliegen.
    protected void shootBomb(int n)
    {
        if(Greenfoot.getRandomNumber(n) == 0) //Mit der Variable n lässt sich die Wahrscheinlichkeit, dass ein Projektil erschaffen wird, einstellen.
            getWorld().addObject(new ProjectileBomb(), getX(), getY());
    }
}