import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class DialogueBox extends Actor
{
    private int diaTextNumber;
    private boolean nextText;
    
    // Im Konstruktor wird der Name der Bilddatei gegeben.
    public DialogueBox(String bild)
    {
        GreenfootImage img = new GreenfootImage(bild);
        setImage(img);
    }     
    
    // In der Act-Methode wird immer kontrolliert, ob die Box angeklickt wurde. Ist dies der Fall wird nextText true.
    public void act()
    {
        if(Greenfoot.mouseClicked(this))
            nextText = Greenfoot.mouseClicked(this);
    }   
    
    // Im DialogueScreen wird mit dieser Methode abgefragt, ob der Index der Liste erhöht werden soll und somit der nächste Text ausgegeben werden
    // soll
    public boolean getMouseClickedBox()
    {
        return nextText;
    }
    
    // Sobald der Index erhöht wird, wird nextText wieder resettet also auf false gestellt, so dass der Prozess wiederholt werden kann.
    public void setMouseClickedBox()
    {
        nextText = false;
    }
}  
