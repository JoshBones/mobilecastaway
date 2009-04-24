package castaway.ui;

import castaway.canvas.CastawayGameCanvas;
import castaway.events.Event;
import castaway.events.EventListener;
import castaway.utils.Keyboard;
import java.io.InputStream;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;

/**
 *
 * @author Josh
 */
public class splashCanvas extends CastawayGameCanvas{

    private Keyboard k = new Keyboard();
    private String keyEntry="";

    private Image logo;

    private int stage;
    public splashCanvas(EventListener e,String name) {
        super(e,name);

        showFrame(true,"white");
        k.setKeyLayout(Keyboard.LAYOUT_ALPHA);
        this.setKeyPressIgnore(1);

        try{
            logo = Image.createImage("/castaway/resources/fungus.jpg");
        }
        catch(Exception ex){}
    }
    
    public void prePaint(){
        g.setColor(0x00000000);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(0x00ffffff);
        
        if (k.hasFocus()){
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
        else{
            g.drawImage(logo, HMIDDLE - (logo.getWidth()/2), VMIDDLE - (logo.getHeight()/2), g.TOP|g.LEFT);
            String s1= "FuNgUs";
            String s2= "GaMeS";
            g.drawString(s1, HMIDDLE - (Font.getDefaultFont().stringWidth(s1)/2),VMIDDLE - (logo.getHeight()/2) + logo.getHeight()+10 , g.TOP|g.LEFT);
            g.drawString(s2, HMIDDLE - (Font.getDefaultFont().stringWidth(s2)/2),VMIDDLE - (logo.getHeight()/2) + logo.getHeight()+13 + Font.getDefaultFont().getHeight() , g.TOP|g.LEFT);
        }
        
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
            else if (result.equals("<<")){
                keyEntry = keyEntry.substring(0,keyEntry.length()-1);
            }
            else{
                keyEntry += (result.equals("SPACE")) ? " " : result;
            }
        }
        else{
            if (stage==0){
                k.setFocus(true);
                stage++;
            }
            else{
                isRunning(false);
                this.doEvent(Event.EVENT_CONTROL_FORWARD);
            }
        } 
    }
    
    public void destroy(){
        g = null;
    }
    
}
