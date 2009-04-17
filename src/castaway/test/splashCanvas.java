/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package castaway.test;

import castaway.CastawayGameCanvas;
import castaway.Event;
import castaway.EventListener;
import javax.microedition.lcdui.Font;

/**
 *
 * @author Josh
 */
public class splashCanvas extends CastawayGameCanvas{
    
    boolean isRunning = true;
    int frameDelay =50;

    public splashCanvas(EventListener e) {
        super(e);
    }
    
    public void run(){
        long loopStartTime;
        int keyStates;

        while(isRunning){
            loopStartTime = System.currentTimeMillis();

            g.setColor(0x00000000);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(0x00ffffff);

            String s = "SPLASH!";
            int sWidth = Font.getDefaultFont().stringWidth(s);
            int sHeight = Font.getDefaultFont().getHeight();
            g.drawString("SPLASH!", HMIDDLE - sWidth/2, VMIDDLE - sHeight/2, g.TOP|g.LEFT);

            keyStates = this.getKeyStates();

            if ((keyStates & FIRE_PRESSED) != 0){
                isRunning = false;
                this.doEvent(Event.EVENT_CONTROL_FORWARD);
            }

            flushGraphics();

            try{
                Thread.sleep(frameDelay - (loopStartTime - System.currentTimeMillis())>= 0 ? frameDelay - (loopStartTime - System.currentTimeMillis()) : 0);
            }
            catch(Exception e){}
        }
    }
    
    public void destroy(){
        g = null;
        this.doEvent(Event.EVENT_CONTROL_END);
        killThread();
    }
    
}
