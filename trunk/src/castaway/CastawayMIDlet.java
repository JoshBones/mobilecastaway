package castaway;

import castaway.test.splashCanvas;
import castaway.ui.MainMenu;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author Josh
 */
public class CastawayMIDlet extends MIDlet {

    CanvasMap canvases = new CanvasMap();

    public void startApp() {
        canvases.add(new splashCanvas(new EventListener(this),"splashScreen"));
        canvases.get("splashScreen").start();
        canvases.add(new MainMenu(new EventListener(this),"mainMenu"));
        Display.getDisplay(this).setCurrent(canvases.get("splashScreen"));
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        canvases.disposeOfAll();
        canvases = null;
        notifyDestroyed();
    }
    
    public void changeCanvas(String cName){
        if (canvases.contains(cName)){
            canvases.get(cName).start();
            Display.getDisplay(this).setCurrent(canvases.get(cName));
        }
    }
}