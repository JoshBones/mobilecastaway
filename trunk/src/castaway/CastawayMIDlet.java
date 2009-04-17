package castaway;

import castaway.test.splashCanvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author Josh
 */
public class CastawayMIDlet extends MIDlet {

    EventListener listener = new EventListener(this);
    CanvasMap canvases = new CanvasMap();

    public void startApp() {
        canvases.add("SplashScreen", new splashCanvas(listener));
        canvases.get("SplashScreen").start();
        Display.getDisplay(this).setCurrent(canvases.get("SplashScreen"));
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        listener = null;
        canvases.disposeOfAll();
        canvases = null;
        notifyDestroyed();
    }
}
