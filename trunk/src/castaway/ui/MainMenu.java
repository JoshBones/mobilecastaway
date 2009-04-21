package castaway.ui;

import castaway.canvas.CastawayGameCanvas;
import castaway.events.Event;
import castaway.events.EventListener;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Josh
 *
 * throws:
 * EVENT_CONTROL_FORWARD = new game
 * EVENT_GAME_LOAD = load game
 * EVENT_GAME_EXIT
 */
public class MainMenu extends CastawayGameCanvas{

    private int selectedIndex=0;
    private int currentMenu=0; //0=main 1=options 2=load
    private Image menu,menuH,wave,sand;

    private int waveTop = HEIGHT;
    private boolean paintMenu = false;
    private boolean paintWave = true;

    private int waveStartFrame=0;

    private String action="";

    public MainMenu(EventListener listener, String name) {
        super(listener,name);

        showFrame(true);
        
        try{
            menuH = Image.createImage("/castaway/resources/MenuH.png");
            menu = Image.createImage("/castaway/resources/Menu.png");
            wave = Image.createImage("/castaway/resources/wave.png");
            sand = Image.createImage("/castaway/resources/sand.jpg");
        }
        catch(Exception e){}

        this.enableKeypresses(false);
    }

    public void destroy() {
        super.destroy();
    }

    public void prePaint() {
        g.setColor(0x00ffffff);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        paintSand();
        resetGraphics();

        if (paintMenu){
            paintMenu();
            resetGraphics();
        }

        if (waveTop <= HEIGHT && paintWave){
            paintWave();
        }
        else if (waveTop > HEIGHT && paintWave){
            waveTop = HEIGHT;
            paintWave=false;
            if (!action.equals(""))
                doEvent(action);
        }
    }

    protected void upPressed(){
        if (selectedIndex == 0)
            selectedIndex=3;
        else
            selectedIndex--;
    }

    protected void downPressed(){
        if (selectedIndex == 3)
            selectedIndex=0;
        else
            selectedIndex++;
    }

    protected void firePressed(){

        paintWave = true;
        waveStartFrame = frame;

        switch(selectedIndex){
            case 0:
            case 1:
            case 2:
            case 3:
                action = Event.EVENT_GAME_EXIT;
        }
    }

    private void paintWave(){
        if (frame - waveStartFrame <=15){
            waveTop -= HEIGHT/15;
        }
        else{
            waveTop += HEIGHT/15;
        }
        
        if (frame - waveStartFrame == 16){
            paintMenu = (paintMenu) ? false : true;
            this.enableKeypresses(true);
        }

        g.drawImage(wave, 0, waveTop, g.TOP|g.LEFT);
    }

    private void paintSand(){
        g.drawImage(sand, 0, 0, g.TOP|g.LEFT);
    }

    private void paintMenu(){
        int topOffset = 90;

        switch (currentMenu){
            case 0:
                for (int i=0;i<4;i++){
                    if (i == selectedIndex){
                        try{
                            g.setClip(HMIDDLE - (menuH.getWidth()/2), topOffset + (i * 30), 156, 25);
                            g.drawImage(menuH,HMIDDLE - (menuH.getWidth()/2),topOffset + (i * 30) - (i*25), g.TOP|g.LEFT);
                        }
                        catch(Exception e){}
                    }
                    else{
                        try{
                            g.setClip(HMIDDLE - (menu.getWidth()/2), topOffset + (i * 30), 156, 25);
                            g.drawImage(menu,HMIDDLE - (menu.getWidth()/2),topOffset + (i * 30) - (i*25), g.TOP|g.LEFT);
                        }
                        catch(Exception e){}
                    }
                }
                break;
            case 1:
                
                break;
        }
        
        
    }
}
