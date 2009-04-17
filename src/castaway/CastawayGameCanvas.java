package castaway;

import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Josh
 */
public abstract class CastawayGameCanvas extends GameCanvas implements Runnable{

    private Thread runner;
    private EventListener el;
    
    public CastawayGameCanvas(EventListener el) {
        super(true);
        runner = new Thread();
        this.el = el;
    }

    public abstract void run();

    // Called by canvas handler to before ending class
    // Must properly dispose of all resources
    public abstract void destroy();

    public void start(){
        runner.start();
    }

    public void stop(){
        runner.interrupt();
    }

    public void killThread(){
        runner.interrupt();
        runner = null;
    }
    
    public boolean doEvent(String eventType){
        try{
            el.doEvent(new Event(eventType));
        }
        catch(InvalidEventTypeException iete){
            return false;
        }
        return true;
    }
}
