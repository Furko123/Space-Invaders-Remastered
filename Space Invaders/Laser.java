import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Laser extends Actor
{
    GreenfootImage img = new GreenfootImage(100, 100);
    String picture;
    int counter;
    
    Laser(String pic)
    {
        changeImage(pic); //So kann man zwischen den zwei verschiedenen Typen (Strahl + Ende) unterscheiden.
        picture = pic;
    }
    
    public void act() 
    {
        if(picture.equals("laserEnd.png")) //Falls dieses Objekt das Ende des Lasers ist...
        {
            //Hier werden durch verschiedene Frames hintereinander abgespielt (in Dauerschleife).
            if(counter == 20)
                changeImage("laserF1.png"); 
            if(counter == 40)
                changeImage("laserF2.png");
            if(counter == 60)
                changeImage("laserF3.png");
            if(counter >= 80)
            {
                changeImage("laserF4.png");
                counter = 0;
            }
            counter += 1;
        }
    }    
    
    private void changeImage(String pic)
    {
        GreenfootImage img = new GreenfootImage(pic);
        img.scale(img.getWidth() * 3, img.getHeight() * 3);
        setImage(img);
    }
}
