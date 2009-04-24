package castaway.canvas;

import castaway.events.Event;
import castaway.events.InvalidEventTypeException;
import castaway.events.EventListener;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 * Base canvas class for all canvases in the castaway project.
 * implements several common features including a game loop,
 * pre and post paint events, painting, an eventListener,
 * and debugging options including printing frame counter to screen
 *
 * @author Josh
 * @version 1.0
 */
public abstract class CastawayGameCanvas extends GameCanvas implements Runnable{

    private EventListener el;
    private Thread runner;
    private String name;
    
    private int frameDelay=50;//20 fps
    private boolean isRunning=true;// is game loop running?
    private int keyDelay=0;
    private int keyPressIgnore=3;//how many frames to ignore keypresses for once a keypress is registered
    protected int frame=0;//current frame;
    private boolean showFrame=false;
    private boolean drawFrameWhite=false;
    private boolean enableKeypress=true;

    protected Graphics g = getGraphics();

    /**
     * height of canvas
     */
    protected final int HEIGHT;
    /**
     * width of canvas in pixels
     */
    protected final int WIDTH;
    /**
     * the horizontal middle of the canvas in pixels
     */
    protected final int HMIDDLE;
    /**
     * the vertical middle of the canvas in pixels
     */
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

    /**
     * Handles execution of game loop, delegating controls to other methods in the following order:<br>
     * prePaint()<br>
     * flushGraphics()<br>
     * resetGraphics()<br>
     * keyPressed()<br>
     * postPaint()<br>
     *<br>
     * then sleeps thread for as long as necessary to maintain specified FPS.
     */
    public void run(){
        long loopStartTime;

        while (isRunning){
            loopStartTime = System.currentTimeMillis();

            frame++;            

            prePaint();

            g.setClip(0, 0, WIDTH, HEIGHT);
            if (showFrame){
                if (drawFrameWhite)
                    g.setColor(0x00ffffff);
                else
                    g.setColor(0x00000000);

                g.drawString(String.valueOf(frame), 5, 5, g.TOP|g.LEFT);
            }

            flushGraphics();

            this.resetGraphics();

            if (keyDelay ==0 && enableKeypress)
                keyPressed();
            else{
                if (keyDelay >0)
                    keyDelay --;
            }

            postPaint();

            try{
                if (frameDelay - (loopStartTime - System.currentTimeMillis()) < 0)
                g.drawString("frame delayed!!",15,5,g.TOP|g.LEFT);

                Thread.sleep((frameDelay - (loopStartTime - System.currentTimeMillis()) >0) ? frameDelay - (loopStartTime - System.currentTimeMillis()) : 0);
            }
            catch(Exception e){}
        }
    }

    /**
     * Called by canvas handler before ending class
     * Must properly dispose of all resources
     */
    public void destroy(){
        g = null;
        el = null;
        name = null;
    }

    /**
     * overidden by subclass to implement pre-painting activity
     */
    protected void prePaint(){}

    /**
     * overidden by subclass to implement post-painting activity
     */
    protected void postPaint(){}

    /**
     * registers keypresses
     */
    public void keyPressed(){
        if ((getKeyStates() & DOWN_PRESSED) !=0){
            downPressed();
            keyDelay = keyPressIgnore;
        }
        if ((getKeyStates() & UP_PRESSED) !=0){
            upPressed();
            keyDelay = keyPressIgnore;
        }
        if ((getKeyStates() & LEFT_PRESSED) !=0){
            leftPressed();
            keyDelay = keyPressIgnore;
        }
        if ((getKeyStates() & RIGHT_PRESSED) !=0){
            rightPressed();
            keyDelay = keyPressIgnore;
        }
        if ((getKeyStates() & FIRE_PRESSED) !=0){
            firePressed();
            keyDelay = keyPressIgnore;
        }
    }

    /**
     * overidden by subclass to handle up keypress
     */
    protected void upPressed(){}
    
    /**
     * overidden by subclass to handle down keypress
     */
    protected void downPressed(){}

    /**
     * overidden by subclass to handle left keypress
     */
    protected void leftPressed(){}

    /**
     * overidden by subclass to handle right keypress
     */
    protected void rightPressed(){}

    /**
     * overidden by subclass to handle fire keypress
     */
    protected void firePressed(){}

    /**
     * called by MIDlet to start canvases game loop
     */
    public void start(){
        runner = new Thread(this);
        runner.start();
    }

    /**
     * called by midlet to stop game loop
     */
    public void stop(){
        runner.interrupt();
    }

    /**
     * called by midlet to stop and dispose of game loop
     */
    public void killThread(){
        try{
            runner.interrupt();
        }
        catch (Exception e){}
        runner = null;
    }

    /**
     * used by subclass to register an event with canvases eventListener
     */
    public boolean doEvent(String eventType){
        try{
            el.doEvent(new Event(eventType,this));
        }
        catch(InvalidEventTypeException iete){
            return false;
        }
        return true;
    }

    /**
     * returns canvases name
     */
    public String getName() {
        return name;
    }

    /**
     * allows subclass to override default FPS
     */
    public void setFramesPerSecond(int framesPerSecond) {
        this.frameDelay = (1000 / framesPerSecond);
    }

    /**
     * sets the number of frames in which keypresses are ignored after one has been registered
     */
    public void setKeyPressIgnore(int keyPressIgnore) {
        this.keyPressIgnore = keyPressIgnore;
    }

    /**
     * returns true if game loop is currently running
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * used to change the state of the game loop
     */
    public void isRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * used to toggle whether or not a frame counter is printed to the top left corner of the display
     */
    public void showFrame(boolean show){
        this.showFrame=show;
    }

    /**
     * used to toggle whether or not a frame counter is printed to the top left corner of the display
     *
     * @param colour sets the frame counters colour. only legal value is 'white'
     */
    public void showFrame(boolean show,String colour){
        this.showFrame=show;
        if (colour.equalsIgnoreCase("white")){
            this.drawFrameWhite=true;
        }
    }

    /**
     * resets the graphics object's state WITHOUT clearing the canvas
     */
    protected void resetGraphics(){
        g.setClip(0, 0, WIDTH, HEIGHT);
        g.setColor(0x00ffffff);
    }

    /**
     * allows subclass to temporarily disable keypresses
     */
    public void enableKeypresses(boolean enable){
        this.enableKeypress = enable;
    }
}
