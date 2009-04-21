package castaway.ui;

import castaway.canvas.CastawayGameCanvas;
import castaway.events.Event;
import castaway.events.EventListener;
import castaway.utils.Keyboard;
import javax.microedition.lcdui.Font;

/**
 *
 * @author Josh
 */
public class splashCanvas extends CastawayGameCanvas{

    private Keyboard k = new Keyboard();
    private String keyEntry="";

    public splashCanvas(EventListener e,String name) {
        super(e,name);

        showFrame(true,"white");
        k.setFocus(true);
        this.setKeyPressIgnore(1);
    }
    
    public void prePaint(){
        g.setColor(0x00000000);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(0x00ffffff);

        String s = WIDTH + "px x " + HEIGHT + "px";
        int sWidth = Font.getDefaultFont().stringWidth(s);
        int sHeight = Font.getDefaultFont().getHeight();
        g.drawString(s, HMIDDLE - sWidth/2, VMIDDLE - sHeight/2, g.TOP|g.LEFT);
        sWidth = Font.getDefaultFont().stringWidth(keyEntry);
        sHeight = Font.getDefaultFont().getHeight();
        g.drawString(keyEntry, HMIDDLE - sWidth/2, VMIDDLE - sHeight/2 - sHeight, g.TOP|g.LEFT);

        this.resetGraphics();
        k.paint(g, WIDTH, HEIGHT);
    }

    protected void upPressed(){
        if (k.hasFocus())
            k.gotKeypress(k.KEY_UP);
    }

    protected void downPressed(){
        if (k.hasFocus())
            k.gotKeypress(k.KEY_DOWN);
    }

    protected void leftPressed(){
        if (k.hasFocus())
            k.gotKeypress(k.KEY_LEFT);
    }

    protected void rightPressed(){
        if (k.hasFocus())
            k.gotKeypress(k.KEY_RIGHT);
    }

    protected void firePressed(){
        if (k.hasFocus()){
            String result = k.gotKeypress(k.KEY_FIRE);

            if (result.equals("DONE")){
                isRunning(false);
                this.doEvent(Event.EVENT_CONTROL_FORWARD);
            }

            keyEntry += (result.equals("SPACE")) ? " " : result;
        }
        else{
            isRunning(false);
            this.doEvent(Event.EVENT_CONTROL_FORWARD);
        } 
    }
    
    public void destroy(){
        g = null;
    }
    
}
