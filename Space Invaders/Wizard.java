import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Wizard extends Actor
{
    public void act() 
    {
        hoverOverImage();
    }
    
    // ändert das Bild des Magiers im Dialogmenü zur bösen Version, wenn die Maus auf ihn bewegt wird
    private void hoverOverImage()
    {
        boolean hovering = false;

        if(Greenfoot.mouseMoved(null)){
            if(hovering != Greenfoot.mouseMoved(this)){
                hovering = !hovering;
                if(hovering){
                    GreenfootImage img = new GreenfootImage("wizardEvil.png");
                    img.scale(img.getWidth(), img.getHeight());
                    setImage(img);          
                }
            }else{
                GreenfootImage img = new GreenfootImage("wizardGood.png");
                img.scale(img.getWidth(), img.getHeight());
                setImage(img);
            }
        }
    }
}
