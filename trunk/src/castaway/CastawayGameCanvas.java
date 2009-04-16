package castaway;

import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Josh
 */
public abstract class CastawayGameCanvas extends GameCanvas implements Runnable, ActionListener {

    private Thread runner;

    public CastawayGameCanvas() {
        super(true);

        runner = new Thread();
    }

    public abstract void run();

    public void start(){
        runner.start();
    }

    public void stop(){
        runner.interrupt();
    }

    public void kill(){
        runner.interrupt();
        runner = null;
        game = null;
    }
}
