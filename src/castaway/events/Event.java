package castaway.events;

import castaway.canvas.CastawayGameCanvas;
import castaway.*;

/**
 *
 * @author Josh
 * @description Event Class is used to register canvas change events with event listener
 */
public class Event {

    public static final String EVENT_GAME_KEYWORD = "game";
    public static final String EVENT_GAME_LOAD = "gameLoad";
    public static final String EVENT_GAME_SAVE = "gameSave";
    public static final String EVENT_GAME_EXIT = "gameExit";

    public static final String EVENT_CONTROL_KEYWORD ="control";
    public static final String EVENT_CONTROL_START = "controlStart";
    public static final String EVENT_CONTROL_END = "controlEnd";
    public static final String EVENT_CONTROL_PAUSE = "controlPause";
    public static final String EVENT_CONTROL_FORWARD = "controlForward";

    public static final String EVENT_GO_AREA_KEYWORD = "goArea";
    public static final String EVENT_GO_AREA_LEFT = "goAreaLeft";
    public static final String EVENT_GO_AREA_RIGHT = "goAreaRight";
    public static final String EVENT_GO_AREA_TOP = "goAreaTop";
    public static final String EVENT_GO_AREA_BOTTOM = "goAreaBottom";

    public static final String EVENT_DEATH_KEYWORD = "death";
    public static final String EVENT_DEATH_STARVATION = "deathStarvation";
    public static final String EVENT_DEATH_INJURY = "deathInjury";
    public static final String EVENT_DEATH_MALARIA = "deathMalaria";
    public static final String EVENT_DEATH_INSANITY = "deathInsanity";

    public static final String EVENT_WIN_KEYWORD = "win";
    public static final String EVENT_WIN_ESCAPE = "winEscape";
    public static final String EVENT_WIN_RESCUE = "winRescue";
    public static final String EVENT_WIN_LIVE_WITH_TRIBE = "winLiveWithTribe";

    private final String[] EVENT_GAME_TYPES = {EVENT_GAME_LOAD,EVENT_GAME_SAVE,EVENT_GAME_EXIT};
    private final String[] EVENT_CONTROL_TYPES = {EVENT_CONTROL_START,EVENT_CONTROL_END,EVENT_CONTROL_PAUSE,EVENT_CONTROL_FORWARD};
    private final String[] EVENT_GO_AREA_TYPES = {EVENT_GO_AREA_LEFT,EVENT_GO_AREA_RIGHT,EVENT_GO_AREA_TOP,EVENT_GO_AREA_BOTTOM};
    private final String[] EVENT_DEATH_TYPES = {EVENT_DEATH_STARVATION,EVENT_DEATH_INJURY,EVENT_DEATH_MALARIA,EVENT_DEATH_INSANITY};
    private final String[] EVENT_WIN_TYPES = {EVENT_WIN_ESCAPE,EVENT_WIN_RESCUE,EVENT_WIN_LIVE_WITH_TRIBE};

    private String type;
    private CastawayGameCanvas referrer;

    public Event(String type,CastawayGameCanvas referrer)throws InvalidEventTypeException {
        boolean valid = false;

        for (int i=0;i<EVENT_GAME_TYPES.length;i++){
            if (type.equals(EVENT_GAME_TYPES[i])){
                valid = true;
                break;
            }
        }

        for (int i=0;i<EVENT_CONTROL_TYPES.length;i++){
            if (type.equals(EVENT_CONTROL_TYPES[i])){
                valid = true;
                break;
            }
        }

        for (int i=0;i<EVENT_GO_AREA_TYPES.length;i++){
            if (type.equals(EVENT_GO_AREA_TYPES[i])){
                valid = true;
                break;
            }
        }

        for (int i=0;i<EVENT_DEATH_TYPES.length;i++){
            if (type.equals(EVENT_DEATH_TYPES[i])){
                valid = true;
                break;
            }
        }

        for (int i=0;i<EVENT_WIN_TYPES.length;i++){
            if (type.equals(EVENT_WIN_TYPES[i])){
                valid = true;
                break;
            }
        }

        if (!valid)
            throw new InvalidEventTypeException ("Invalid event type");
        
        this.type = type;
        this.referrer = referrer;
    }

    public String getEventType(){
        return type;
    }

    public CastawayGameCanvas getReferrer() {
        return referrer;
    }
}
