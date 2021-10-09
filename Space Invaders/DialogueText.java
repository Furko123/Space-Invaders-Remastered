import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class DialogueText extends Actor
{
    private String text = "";
    private int testlength;
    private int i;
    private boolean nextTextt;

    public DialogueText() 
    {
        // Ergänzen Sie Ihren Quelltext hier...
    }  

    public void act()
    {
        if(Greenfoot.mouseClicked(this))
            nextTextt = Greenfoot.mouseClicked(this);
    }

    // Im einem If-statement wird geschaut ob i kleiner ist als die Länge vom Text. Ist dies der Fall, dann wird bei jedem Mal, wo der Code 
    // abläuft der angezeigte Text um einen Buchstaben des Textes erhöht und es wird 100ms Sekunden gewartet, so dass sich am Ende der
    // Text allmählich vervollständigt und somit das Sprechen simuliert.
    public void textausgeben(String test)
    {
        Color hintergrund = new greenfoot.Color(0, 0, 0, 0);
        testlength = test.length();
        if(i < testlength)
        {

            text = text + test.charAt(i);
            GreenfootImage textAn = new GreenfootImage(text , 30, greenfoot.Color.WHITE, hintergrund);
            setImage(textAn);
            try
            {
                Thread.sleep(75);
            }
            catch(Exception ex)
            {
                System.out.println("Fehler");
            }

            i = i + 1;
        }
    }
    
    // Da i immer vergrößert wird und auch so bleibt, auch wenn ein neuer Text gegeben wird, würde es nicht weitergehen sobald so viele Buchstaben
    // ausgegeben wurde wie der erste Text hatte. Deswegen werden alle Variablen hier nocheinmal geresettet, wenn ein neuer Text gegeben wurde. 
    public void resetVariables()
    {
        testlength = 0;
        text = "";
        i = 0;
    }
    
    // Im DialogueScreen wird mit dieser Methode abgefragt, ob der Index der Liste erhöht werden soll und somit der nächste Text ausgegeben werden
    // soll
    public boolean getMouseClickedText()
    {
        return nextTextt;
    }
    
    // Sobald der Index erhöht wird, wird nextText wieder resettet also auf false gestellt, so dass der Prozess wiederholt werden kann.
    public void setMouseClickedText()
    {
        nextTextt = false;
    }
}