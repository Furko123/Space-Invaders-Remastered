import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Healthbar extends Actor
{
    Color color = new Color(192, 57, 43,1);

    int health = 100;
    int healthBarWidth = 100;
    int healthBarHeight = 15;
    int pixelsPerHealthPoint = (int)healthBarWidth / health;
    
    String dif;
    boolean differences = false;

    Healthbar()
    {
        update();
    }
    
    public void act()
    {
        if(!differences)
            differences();
        update();
    }
    
    //Die Leben des Magiers werden in Abhängigkeit von der Schwierigkeit festgelegt.
    private void differences()
    {
        dif = getWorldOfType(Game.class).getDifficulty();

        if(dif == "easy")
        {
            health = 50;
            EndBoss.health = 50;
        }
        else if(dif == "normal")
        {
            health = 75;
            EndBoss.health = 75;
        }
        else if(dif == "hard")
        {
            health = 100;
            EndBoss.health = 100;
        }
        differences = true;
    }
    
    //Hier wird die Lebensanzeige geupdatet.
    public void update()
    {
        health = EndBoss.health; //Leben = Leben des Magiers.
        setImage(new GreenfootImage(healthBarWidth + 2, healthBarHeight +2)); 
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.WHITE); 
        myImage.drawRect(0, 0, healthBarWidth + 1, healthBarHeight + 1); //Der weiße Umriss wird erschaffen.
        myImage.setColor(Color.RED); 
        myImage.fillRect(1,1, health*pixelsPerHealthPoint, healthBarHeight); //Die Healthbar wird in Abhängigkeit von den Lebenspunkten gefüllt.
    }
}
