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
    private String name;
    
    private int frameDelay=50;//20 fps
    private boolean isRunning=true;// is game loop running?
    private int keyDelay=0;
    private int keyPressIgnore=4;//how many frames to ignore keypresses for once a keypress is registered

    protected Graphics g = getGraphics();

    protected final int HEIGHT;
    protected final int WIDTH;
    protected final int HMIDDLE;
    protected final int VMIDDLE;
    
    public CastawayGameCanvas(EventListener el, String name) {
        super(true);
        this.el = el;
        this.name = name;
        this.setFullScreenMode(true);
        HEIGHT = getHeight();
        WIDTH = getWidth();
        HMIDDLE = WIDTH/2;
        VMIDDLE= HEIGHT/2;
    }

    // contains game loop
    public void run(){
        long loopStartTime;

        while (isRunning){
            loopStartTime = System.currentTimeMillis();

            prePaint();

            flushGraphics();

            keyPressed();

            postPaint();

            try{
                Thread.sleep((frameDelay - (loopStartTime = System.currentTimeMillis()) >0) ? frameDelay - (loopStartTime = System.currentTimeMillis()) : 0);
            }
            catch(Exception e){}
        }
    }

    // Called by canvas handler before ending class
    // Must properly dispose of all resources
    public void destroy(){
        g = null;
        el = null;
        name = null;
    }

    public abstract void prePaint();

    public abstract void postPaint();

    public void keyPressed(){

        if (keyDelay >0)
            keyDelay --;

        if ((getKeyStates() & DOWN_PRESSED) !=0 && keyDelay ==0){
            downPressed();
            keyDelay = keyPressIgnore;
        }
        if ((getKeyStates() & UP_PRESSED) !=0 && keyDelay ==0){
            upPressed();
            keyDelay = keyPressIgnore;
        }
        if ((getKeyStates() & LEFT_PRESSED) !=0 && keyDelay ==0){
            leftPressed();
            keyDelay = keyPressIgnore;
        }
        if ((getKeyStates() & RIGHT_PRESSED) !=0 && keyDelay ==0){
            rightPressed();
            keyDelay = keyPressIgnore;
        }
        if ((getKeyStates() & FIRE_PRESSED) !=0 && keyDelay ==0){
            firePressed();
            keyDelay = keyPressIgnore;
        }
    }

    protected void upPressed(){}
    protected void downPressed(){}
    protected void leftPressed(){}
    protected void rightPressed(){}
    protected void firePressed(){}

    public void start(){
        runner = new Thread(this);
        runner.start();
    }

    public void stop(){
        runner.interrupt();
    }

    public void killThread(){
        try{
            runner.interrupt();
        }
        catch (Exception e){}
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

    public String getName() {
        return name;
    }

    public void setFramesPerSecond(int framesPerSecond) {
        this.frameDelay = (1000 / framesPerSecond);
    }

    public void setKeyPressIgnore(int keyPressIgnore) {
        this.keyPressIgnore = keyPressIgnore;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void isRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

}
