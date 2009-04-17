package castaway;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Josh
 */
public abstract class CastawayGameCanvas extends GameCanvas implements Runnable{

    private EventListener el;
    private Thread runner;

    protected Graphics g = getGraphics();

    protected final int HEIGHT;
    protected final int WIDTH;
    protected final int HMIDDLE;
    protected final int VMIDDLE;
    
    public CastawayGameCanvas(EventListener el) {
        super(true);
        this.el = el;
        this.setFullScreenMode(true);
        HEIGHT = getHeight();
        WIDTH = getWidth();
        HMIDDLE = WIDTH/2;
        VMIDDLE= HEIGHT/2;
    }

    // contains game loop
    public abstract void run();

    // Called by canvas handler before ending class
    // Must properly dispose of all resources
    public abstract void destroy();

    public void start(){
        runner = new Thread(this);
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
            el.doEvent(new Event(eventType,this));
        }
        catch(InvalidEventTypeException iete){
            return false;
        }
        return true;
    }

}
