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

    public splashCanvas(EventListener e,String name) {
        super(e,name);

        showFrame(true,"white");
    }
    
    public void prePaint(){
        g.setColor(0x00000000);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(0x00ffffff);

        String s = WIDTH + "px x " + HEIGHT + "px";
        int sWidth = Font.getDefaultFont().stringWidth(s);
        int sHeight = Font.getDefaultFont().getHeight();
        g.drawString(s, HMIDDLE - sWidth/2, VMIDDLE - sHeight/2, g.TOP|g.LEFT);
    }
    
    protected void firePressed(){
        isRunning(false);
        this.doEvent(Event.EVENT_CONTROL_FORWARD);
    }
    
    public void destroy(){
        g = null;
    }
    
}
