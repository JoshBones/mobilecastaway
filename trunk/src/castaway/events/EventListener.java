package castaway.events;

import castaway.*;

/**
 *
 * @author Josh
 * @description Event listener manages and executes canvas change results of events
 */
public class EventListener implements Runnable {

    Event currentEvent;
    Thread eventHandlerThread;
    CastawayMIDlet game;

    public EventListener(CastawayMIDlet game) {
        this.game = game;
    }
    
    public void doEvent(Event e){
        this.currentEvent = e;
        eventHandlerThread = new Thread(this);
        eventHandlerThread.start();
    }

    public void run() {
        handleEvent();
        currentEvent = null;
        eventHandlerThread.interrupt();
    }

    private void handleEvent(){
        String type=currentEvent.getEventType();

        if (type.indexOf(Event.EVENT_GAME_KEYWORD) == 0){
            handleGameEvent();
        }
        else if (type.indexOf(Event.EVENT_CONTROL_KEYWORD) == 0){
            handleControlEvent();
        }
        else if (type.indexOf(Event.EVENT_GO_AREA_KEYWORD) == 0){
            //call GO_AREA event handler
        }
        else if (type.indexOf(Event.EVENT_DEATH_KEYWORD) == 0){
            // call DEATH event handler
        }
        else if (type.indexOf(Event.EVENT_WIN_KEYWORD) == 0){
            // call WIN event handler
        }
    }

    private void handleGameEvent(){
        String type = currentEvent.getEventType();
        
        if (type.equals(Event.EVENT_GAME_SAVE)){
            
        }
        else if (type.equals(Event.EVENT_GAME_LOAD)){
            
        }
        else if (type.equals(Event.EVENT_GAME_EXIT)){
            game.destroyApp(true);
        }
    }

    private void handleControlEvent(){
        String type = currentEvent.getEventType();

        if (type.equals(Event.EVENT_CONTROL_FORWARD)){
            if (currentEvent.getReferrer().getName().equals("splashScreen")){
                castaway.ui.splashCanvas s = (castaway.ui.splashCanvas) currentEvent.getReferrer();
                s.killThread();
                game.changeCanvas("mainMenu");
            }
        }
        else if (type.equals(Event.EVENT_CONTROL_START)){

        }
        else if (type.equals(Event.EVENT_CONTROL_END)){

        }
        else if (type.equals(Event.EVENT_CONTROL_PAUSE)){

        }
    }

}
