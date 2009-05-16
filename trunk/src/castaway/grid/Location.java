package castaway.grid;

/**
 *
 * @author Josh
 */
public class Location {

    private int top,left;

    public Location(int top, int left) {
        this.top = top;
        this.left = left;
    }

    public int getTop(){
        return top;
    }
    
    public int getLeft(){
        return left;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setLeft(int left) {
        this.left = left;
    }

}
