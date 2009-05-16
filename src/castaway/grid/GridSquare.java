package castaway.grid;

/**
 *
 * @author Josh
 */
public class GridSquare {

    private IsometricLocation location;//can retrieve overlayed square or visible diamond
    private static int width,height; //all grid squares share width and height
    private int row,column;

    public GridSquare(IsometricLocation location, int row, int column) {
        this.location = location;
        this.row = row;
        this.column = column;

        if (width == 0){
            height = (location.getIsoLeftTop() - location.getIsoTopTop()) * 2;
            width = (location.getIsoTopLeft() - location.getIsoLeftLeft()) * 2;
        }
    }

    public static void setHeight(int height) {
        GridSquare.height = height;
    }

    public static void setWidth(int width) {
        GridSquare.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public void setLocation(IsometricLocation location) {
        this.location = location;
    }

    public IsometricLocation getLocation() {
        return location;
    }

}
