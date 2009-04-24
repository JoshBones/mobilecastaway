package castaway.ui;

import castaway.canvas.CastawayGameCanvas;
import castaway.events.Event;
import castaway.events.EventListener;
import castaway.utils.Keyboard;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Font;

/**
 *
 * @author Josh
 */
public class MainMenu2 extends CastawayGameCanvas{

    //Menu Constants
    private final String MENU_MAIN = "main";
    private final String MENU_NEW = "new";
    private final String MENU_LOAD = "load";
    private final String MENU_OPTIONS = "options";

    //Menu controls
    private String currentMenu = "";
    private String nextMenu = MENU_MAIN;
    private boolean paintMenu = false;
    private int selectedIndex;
    private int topOffset = 90;

    //Images
    private Image sand,wave,mnuMain,mnuMainHighlighted;

    //Wave Controls
    private boolean paintWave = true;
    private int waveTop=HEIGHT;
    private int forcePullWaveDown = 2; //'gravity' acting on wave
    private int waveSpeed;
    private int waveDelay; //randomises time before next wave
    private boolean rigWave=false;// rigWave to be BIG if we need it to cover the menu
    private boolean rigDone = false;

    //Events
    String action=""; //pending action. executed when wave is finished

    public MainMenu2(EventListener el,String name) {
        super(el,name);

        this.setKeyPressIgnore(2);

        getImages();

    }
    
    private void getImages(){
        try { mnuMainHighlighted = Image.createImage("/castaway/resources/MenuH.png"); }
        catch(Exception e) { mnuMainHighlighted = null; }

        try{ mnuMain = Image.createImage("/castaway/resources/Menu.png"); }
        catch(Exception e) { mnuMain = null; }
            
        try { wave = Image.createImage("/castaway/resources/wave.png"); }
        catch(Exception e) { wave = null; }
            
        try { sand = Image.createImage("/castaway/resources/sand.jpg"); }
        catch(Exception e) { sand = null; }
    }
    
    protected void prePaint(){
        g.drawImage(sand, 0, 0, g.TOP|g.LEFT);

        this.resetGraphics();

        //paint un-rigged wave. needs to go under menu
        if (paintWave && frame > 15 && waveDelay <=0 && !rigWave){
            paintWave();
        }

        this.resetGraphics();

        if (paintMenu)
            paintMenu();

        this.resetGraphics();

        //paint rigged wave, needs to go over menu
        if (rigWave){
            paintWave();
        }
            
        waveDelay--;
    }
    
    private void paintWave(){
        int waveSpeedIn = waveSpeed;
        boolean initialRun = false;

        // if wave movement has just begun, set speed (rand)
        if (waveTop >= HEIGHT){
            initialRun = true;
            waveSpeed = new java.util.Random().nextInt(20)+ 14;
        }
            
        // if wave movement has just begun, set speed (rigged)
        if (rigWave && waveTop >= HEIGHT){
            waveSpeed = 34;
            initialRun = true;
        }
            
        //move wave at speed
        waveTop -= waveSpeed;
        g.drawImage(wave, 0, waveTop, g.TOP|g.LEFT);

        if (waveSpeedIn <=0 && waveSpeed >=0){
            //point of reversal
            if (rigWave && !initialRun){
                currentMenu = nextMenu;
                nextMenu="";
                selectedIndex=0;
                paintMenu = true;
                rigDone = true;
            }
        }

        //if wave has receded, stop wave movement
        if (waveTop >= HEIGHT){
            //rigged wave is completed
            if (rigDone){
                rigWave = false;

                //if theres a pending event, execute it
                if (!action.equals(""))
                    doEvent(action);
            }
                

            //paintWave = false;
            if (rigWave)
                waveDelay = 0;
            else
                waveDelay = new java.util.Random().nextInt(25) + 15;

            waveTop = HEIGHT+1;
        }

        //apply gravity to wave speed
        waveSpeed -= forcePullWaveDown;

        //restrict wave recede speed
        if (waveSpeed < -18)
            waveSpeed = -18;
    }

    private void paintMenu(){

        if (currentMenu.equals(MENU_MAIN)){
            for (int i=0;i<4;i++){
                if (i == selectedIndex){
                    try{
                        g.setClip(HMIDDLE - (mnuMainHighlighted.getWidth()/2), topOffset + (i * 30), 156, 25);
                        g.drawImage(mnuMainHighlighted,HMIDDLE - (mnuMainHighlighted.getWidth()/2),topOffset + (i * 30) - (i*25), g.TOP|g.LEFT);
                    }
                    catch(Exception e){}
                }
                else{
                    try{
                        g.setClip(HMIDDLE - (mnuMain.getWidth()/2), topOffset + (i * 30), 156, 25);
                        g.drawImage(mnuMain,HMIDDLE - (mnuMain.getWidth()/2),topOffset + (i * 30) - (i*25), g.TOP|g.LEFT);
                    }
                    catch(Exception e){}
                }
            }
        }
        else if (currentMenu.equals(MENU_LOAD)){

        }
        else if (currentMenu.equals(MENU_OPTIONS)){

        }
        else if (currentMenu.equals(MENU_NEW)){

        }

    }

    protected void firePressed(){
        
        if (currentMenu.equals(MENU_MAIN)){
            
            switch(selectedIndex){
                case 0:
                case 1:
                case 2:
                case 3:
                    action = Event.EVENT_GAME_EXIT;
                    break;
            }

        }
        else if (currentMenu.equals(MENU_LOAD)){

        }
        else if (currentMenu.equals(MENU_OPTIONS)){

        }
        else if (currentMenu.equals(MENU_NEW)){

        }

        rigWave = true;
        if (waveDelay >0)
            waveDelay =0;
    }

    protected void upPressed(){

        if (currentMenu.equals(MENU_MAIN)){
            if (selectedIndex == 0)
                selectedIndex=3;
            else
                selectedIndex--;
        }
        else if (currentMenu.equals(MENU_LOAD)){

        }
        else if (currentMenu.equals(MENU_OPTIONS)){

        }
        else if (currentMenu.equals(MENU_NEW)){

        }

    }

    protected void downPressed(){

        if (currentMenu.equals(MENU_MAIN)){
            if (selectedIndex == 3)
                selectedIndex=0;
            else
                selectedIndex++;
        }
        else if (currentMenu.equals(MENU_LOAD)){

        }
        else if (currentMenu.equals(MENU_OPTIONS)){

        }
        else if (currentMenu.equals(MENU_NEW)){

        }

    }

    public void destroy(){
        sand = null;
        wave = null;
        mnuMain = null;
        mnuMainHighlighted = null;
    }
}
