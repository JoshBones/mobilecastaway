package castaway.ui.mainmenu;

import java.util.Random;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Describes and controls a wave object. both
 * randomly generated and rigged waves
 *
 * @author Josh
 */
public class Wave {

    private Graphics g;
    private int speed,top,delay,frame;//NB. frame is NOT the canvas frame, it is measured by the wave's life
    private int gravity= 2;
    private int canvasHeight;
    private Random rand = new Random();
    private boolean done=false;
    private boolean rigged;
    private boolean recedeStarted=false;
    private Image wave;

    public Wave(Graphics g,boolean rigged,int canvasHeight,Image wave) {
        this.g = g;
        this.wave=wave;
        top = canvasHeight;
        this.canvasHeight=canvasHeight;
        this.rigged=rigged;

        if (rigged){
            speed=34;
            delay=0;
        }
        else{
            speed =rand.nextInt(15)+ 19;
            delay=rand.nextInt(25) + 15;
        }

    }

    public void updateWave(){
        if (frame >= delay && !done){
            if (top - speed > top ){
                recedeStarted = true;
            }

            top -= speed;
            speed -= gravity;
            g.drawImage(wave, 0, top, g.TOP|g.LEFT);

            if (top >= canvasHeight && speed < 0)
                done=true;
        }
        frame++;
    }

    /**
     * wave is finished
     */
    public boolean isDone(){
        return done;
    }

    /**
     * advance menu to next in queue
     */
    public boolean advanceMenu(){
        if (recedeStarted && rigged){
            recedeStarted = false;
            return true;
        }
        return false;
    }

    public boolean isRigged() {
        return rigged;
    }
}
