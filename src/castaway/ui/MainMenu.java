package castaway.ui;

import castaway.CastawayGameCanvas;
import castaway.Event;
import castaway.EventListener;
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
    private Image menu,menuH,wave,sand;

    private int waveTop = HEIGHT;
    private boolean paintMenu = false;

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
        }

        resetGraphics();
        
        if (waveTop <= HEIGHT){
            paintWave();
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
        switch(selectedIndex){
            case 0:
            case 1:
            case 2:
            case 3:
                this.doEvent(Event.EVENT_GAME_EXIT);
        }
    }

    private void paintWave(){
        if (frame <=15){
            waveTop -= HEIGHT/15;
        }
        else{
            paintMenu = true;
            this.enableKeypresses(true);
            waveTop += HEIGHT/15;
        }

        g.drawImage(wave, 0, waveTop, g.TOP|g.LEFT);
    }

    private void paintSand(){
        g.drawImage(sand, 0, 0, g.TOP|g.LEFT);
    }

    private void paintMenu(){
        int topOffset = 90;
        
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
    }
}
