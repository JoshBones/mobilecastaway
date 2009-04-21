package castaway.events;

/**
 *
 * @author Josh
 */
public class InvalidEventTypeException extends Exception {

    /**
     * Creates a new instance of <code>InvalidTypeException</code> without detail message.
     */
    public InvalidEventTypeException() {
    }


    /**
     * Constructs an instance of <code>InvalidTypeException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidEventTypeException(String msg) {
        super(msg);
    }
}
