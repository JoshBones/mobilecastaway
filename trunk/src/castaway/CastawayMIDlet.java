package castaway;

import castaway.utils.Map;
import castaway.utils.ImageManager;
import castaway.events.EventListener;
import castaway.ui.splashCanvas;
import castaway.ui.mainmenu.MainMenu;
import castaway.ui.testGrid;
import castaway.canvas.CastawayGameCanvas;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author Josh
 */
public class CastawayMIDlet extends MIDlet {

    private final String SPLASH_SCREEN = "splashScreen";
    private final String MAIN_MENU = "mainMenu";
    
    private Map canvases = new Map();
    private ImageManager imgMan = new ImageManager("/castaway/resources/");

    public void startApp() {
        canvases.add(SPLASH_SCREEN,new splashCanvas(new EventListener(this),SPLASH_SCREEN,imgMan));
        
        canvases.add(MAIN_MENU,new MainMenu(new EventListener(this),MAIN_MENU,imgMan));
        
        canvases.add("testGrid", new testGrid(new EventListener(this),"testGrid",imgMan));
        ((testGrid) canvases.get("testGrid")).start();
        Display.getDisplay(this).setCurrent((testGrid) canvases.get("testGrid"));
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        try{
            canvases.disposeOfAll();
        }
        catch(Exception e){
            System.out.println(e);
        }
        canvases = null;
        notifyDestroyed(); 
    }
    
    public void changeCanvas(String cName){
        if (canvases.contains(cName)){
            ((CastawayGameCanvas) canvases.get(cName)).start();
            Display.getDisplay(this).setCurrent((CastawayGameCanvas)canvases.get(cName));
        }
    }
}
