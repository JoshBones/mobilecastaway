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
    private int keyDelay=0;
    
    public MainMenu(EventListener listener, String name) {
        super(listener,name);
        
    }

    public void destroy() {
        super.destroy();
    }

    public void prePaint() {
        paintMenu();
    }

    public void postPaint() {}

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

    private void paintMenu(){
        Image img;
        int topOffset = 50;
        
        for (int i=0;i<4;i++){
            
            if (i == selectedIndex){
                try{
                    img = Image.createImage("/castaway/resources/MenuH.png");
                    g.setClip(HMIDDLE - (img.getWidth()/2), topOffset + (i * 30), 156, 25);
                    g.drawImage(img,HMIDDLE - (img.getWidth()/2),topOffset + (i * 30) - (i*25), g.TOP|g.LEFT);
                }
                catch(Exception e){}
            }
            else{
                try{
                    img = Image.createImage("/castaway/resources/Menu.png");
                    g.setClip(HMIDDLE - (img.getWidth()/2), topOffset + (i * 30), 156, 25);
                    g.drawImage(img,HMIDDLE - (img.getWidth()/2),topOffset + (i * 30) - (i*25), g.TOP|g.LEFT);
                }
                catch(Exception e){}
            }         
        }
    }
}
