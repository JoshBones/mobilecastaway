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
 *
 * @events:
 * EVENT_CONTROL_FORWARD = new game
 * EVENT_GAME_LOAD = load game
 * EVENT_GAME_EXIT
 */
public class MainMenu extends CastawayGameCanvas{

    private int selectedIndex=0;
    private int currentMenu=0; //0=main 1=options 2=load
    private int gotoMenu; // mark for menu change, handled once wave is receding
    private int waveTop = HEIGHT;
    private int waveStartFrame=0;
    
    private Image menu,menuH,wave,sand;
    
    private boolean paintMenu = false;
    private boolean paintWave = true;

    private String action="";
    private String name="";
    
    private Keyboard keys = new Keyboard();
    
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
        if (keys.hasFocus()){
            
        }
        else{
            if (selectedIndex == 0)
                selectedIndex=3;
            else
                selectedIndex--;
        }
    }

    protected void downPressed(){
        if(keys.hasFocus()){
            
        }
        else{
            if (selectedIndex == 3)
                selectedIndex=0;
            else
                selectedIndex++;
        }
    }

    protected void leftPressed(){
        if(keys.hasFocus()){

        }
        else{

        }
    }

    protected void rightPressed(){
        if(keys.hasFocus()){

        }
        else{

        }
    }

    protected void firePressed(){

        if(keys.hasFocus()){
            
        }
        else{
            paintWave = true;
            waveStartFrame = frame;
            enableKeypresses(false);

            switch(selectedIndex){
                case 0:
                    gotoMenu =1;
                    break;
                case 1:
                case 2:
                case 3:
                    action = Event.EVENT_GAME_EXIT;
                    break;
            }
        }
    }

    private void paintWave(){
        if (frame - waveStartFrame <=15){
            waveTop -= HEIGHT/15;
            if (frame == 15)
                paintMenu = (paintMenu) ? false : true;
            if (frame - waveStartFrame == 15 && gotoMenu != currentMenu)
                currentMenu = gotoMenu;
        }
        else{
            waveTop += HEIGHT/15;
        }
        
        if (frame - waveStartFrame == 16){
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
                Font f = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
                String title = "What is your name?";
                int txtWidth = WIDTH / 4 * 3;
                int txtHeight = f.getHeight()+2;

                g.setFont(f);
                
                g.fillRect((WIDTH - txtWidth)/2, VMIDDLE - 50 + txtHeight+10, txtWidth, txtHeight);

                g.setColor(0x00000000);
                g.drawString(title, this.HMIDDLE - (f.stringWidth(title)/2), VMIDDLE - 50, g.TOP|g.LEFT);
                g.drawRect((WIDTH - txtWidth)/2, VMIDDLE - 50 + txtHeight+10, txtWidth, txtHeight);
                break;
        }
        
        
    }
}
