import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.util.*; 

public class DialogueScreen extends World
{
    private int nextlevel;
    private int level;
    private DialogueText dialoguetext;
    private DialogueBox dialoguebox;
    private int dialogueTextNumber = 0;
    private List<String> dialogue = new ArrayList<String>();
    private String difficulty;
    private GreenfootSound backgroundMusic;
    private boolean test;
    
    public DialogueScreen(int leveldb, String dif)
    {    
        // Zuerst erstellen wir einen DialogueScreen, der (je nach Level) unterschiedliche Texte hat. 
        super(600, 800, 1);
        nextlevel = leveldb + 1;
        level = leveldb;
        difficulty = dif; 
        prepare();
        //Die Schwierigkeit wird hier mitgegeben, damit wir sie später wieder ein neues Game erstellen können.
        
        // Der Text wird in einer Liste abgespeichert. Einen spezifischen String können wir mit einem Index abrufen.
        if(level == 0)
        {
            dialogue.add("Tutorial & Level 1");
            dialogue.add("Drücke 'Space' \n um zu schießen.");
            dialogue.add("Steuere das Space- \n ship mit den Pfeiltasten \n oder 'a' und 'd'.");
            dialogue.add("Klicke auf die Power- \n ups rechts unten \n in der Ecke...");
            dialogue.add("..um tolle Spezialfähig- \n keiten zu aktivieren!");
            dialogue.add("So kannst du doppelt \n so schnell schießen...");
            dialogue.add("...oder dich mit \n einem Schild schützen.");
            dialogue.add("Nimm dich vor den \n Geschossen der Feinde \n in Acht.");
            dialogue.add("Ich habe gehört, einige \n können dich sogar \n verfolgen!");
            dialogue.add("Viel Spaß! \n Besiege sie alle!");
        }
        else if(level == 1)
        {
            dialogue.add("Level 2");
            dialogue.add("Ausgezeichnet!");
            dialogue.add("Ich hoffe doch, du \n hast niemanden \n entkommen lassen?");
            dialogue.add("Nein? \n Das ist sehr gut!");
            dialogue.add("Vorsicht, die Aliens \n werden sich weiter \n entwickelt haben!");
            dialogue.add("Wenn du ein rotes \n Alien siehst...");
            dialogue.add("...musst du es zwei- \n oder sogar drei- \n mal abschießen.");
            dialogue.add("Und jetzt los. \n Vernichte sie alle!");
        }
        else if(level == 2)
        {
            dialogue.add("Ah, du bist zurück.");
            dialogue.add("Dieser Angriff \n ist zurückgeschlagen!");
            dialogue.add("Aber du musst \n immer wachsam \n bleiben!");
            dialogue.add("Meine Gegner \n ruhen nie!");
            dialogue.add("...");
            dialogue.add("...");
            dialogue.add("WAS?");
            dialogue.add("Dich gehen lassen?");
            dialogue.add("Niemals! Du darfst \n nicht eher \n ruhen, bis...");
            dialogue.add("...jedes dieser \n widerlichen Viecher \n ausgerottet ist!");
            dialogue.add("...");
            dialogue.add("Nein?");
            dialogue.add("Dann wirst \n du sterben!");
        }
        else if(level == 3)
        {
            addObject(new Pictures("furkanDiscord.png"), getWidth() / 4, 400);
            addObject(new Pictures("maximDiscord.png"), getWidth() / 2, 400);
            addObject(new Pictures("malteDiscord.png"), getWidth() - (getWidth() / 4), 400);
            dialogue.add("Credits");
            dialogue.add("Musik von Furkan");
            dialogue.add("Bilder von Maxim");
            dialogue.add("Dokumentation von Malte");
            dialogue.add("Code von uns allen");
            dialogue.add("Danke fürs Spielen!");
            backgroundMusic = new GreenfootSound("credits.mp3");
            backgroundMusic.playLoop();
        }
    }
    
    public void act()
    {
        // Der Methode textausgeben() geben wir einen String von der Liste dialogue mit.
        textausgeben(dialogue.get(dialogueTextNumber));
        
        // Jedes Mal wenn auf die Box geklickt wird, wird der Index der Liste, also der Integer dialogueTextNumber, um 1 erhöht,
        // so dass beim Klicken auf die Box der nächste Text ausgegeben wird.
        if(dialoguebox.getMouseClickedBox() || dialoguetext.getMouseClickedText())
        {
            dialogueTextNumber = dialogueTextNumber + 1;
            dialoguebox.setMouseClickedBox();
            dialoguetext.setMouseClickedText();
            
            // Da in der for-Schleife (eigentlich eine if "Schleife") die Variable verändert wird, müssen wir diese für den nächsten Text wieder zurücksetzen.
            dialoguetext.resetVariables();
        }
        
        // Sobald wir den letzten Text erreicht haben, wird das Game für das nächste Level erstellt. Haben wir das letzte Level schon abgeschlossen
        // wird das Spiel beendet.
        if(dialogueTextNumber == dialogue.size() && level < 3)
        {
            Greenfoot.setWorld(new Game(difficulty, nextlevel));
        }
        else if(dialogueTextNumber == dialogue.size() && level == 3)
        {
            backgroundMusic.stop();
            Greenfoot.stop();
        }
    }
    
    private void prepare()
    {
        // In dieser Methode werden jediglich der Hintergrund und die erforderlichen Objekte erstellt.
        setBackground(blackBackground());
        prepareBox();
    }

    private void prepareBox()
    {
        // Im Folgenden werden alle wichtigen Objekte erstellt und deren Koordinaten bestimmt. In dem wir den Objekten einer Variable zuschreiben, sind
        // wir auch in der Lage, auf diese zurückzugreifen und deren Methoden oder Variablen zu nutzen.
        DialogueBox db = new DialogueBox("dialogueBox.png");
        addObject(db, 300, 700);
        
        dialoguebox = db;
        
        if(level != 3)
        {
            // Damit die Story auch Sinn ergibt, ist der Wizard bei den Credits nicht mehr dabei.
            Wizard wi = new Wizard();
            addObject(wi,130, 700);
            DialogueText dt = new DialogueText();
            addObject(dt, 350, 700);
            dialoguetext = dt;
        }
        else
        {
            DialogueText dt = new DialogueText();
            addObject(dt, 300, 700);
            dialoguetext = dt;
        }
        
    }

    private GreenfootImage blackBackground()
    {
        // Hier erstellen wir ein Bild mit den Ausmaßen des In-Game Bildschirmes. Diesen füllen wir mit der Farbe schwarz.
        GreenfootImage img = new GreenfootImage(getWidth(), getHeight());
        img.setColor(Color.BLACK);
        img.fill();
        return img;
    }
    
    // Der Text wird über eine Methode des dialoguetext's ausgegeben. Weil wir diese Methode in der Act-Methode aufrufen hat sie praktisch
    // gesehen die gleiche Funktion einer for Schleife, obwohl sie einen if-statement benutzt.
    private void textausgeben(String text)
    {
        dialoguetext.textausgeben(text);
    }
    
    public int getDialogueTextNumber()
    {
        return dialogueTextNumber;
    }

}