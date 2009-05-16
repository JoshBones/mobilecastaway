package castaway.grid;

/**
 *
 * @author Josh
 */
public class IsometricEngine {

    private int gridSquareSize; // assumes square grid (only size, not width/height);
    private int gridWidth,gridHeight; //in squares
    private GridSquare[][] grid;

    public IsometricEngine(int gridSquareSize, int gridHeight, int gridWidth) {
        this.gridSquareSize = gridSquareSize;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
    }

    public GridSquare[][] getGrid(){
        if(grid == null)
            createGrid();

        return grid;
    }

    private void createGrid(){// create a 2d array of grid squares
        grid = new GridSquare [gridHeight][gridWidth];

        int row=0;
        int column=0;

        while (row<gridHeight || column<gridWidth){
            if (column == gridWidth){
                row ++;
                column =0;
                if (row == gridHeight)
                    return;
            }

            grid[row][column] = getSquare(row,column);

            column ++;
        }
    }

    private GridSquare getSquare(int row, int column){// gets grid square containing location in px relative to grid top left
        int isoTopTop,isoTopLeft,isoLeftTop,isoLeftLeft;

        isoTopTop = (gridSquareSize/2)  * row;
        isoLeftLeft = column * gridSquareSize - (row%2==0 ? gridSquareSize/2 : 0);
        isoTopLeft = isoLeftLeft + (gridSquareSize/2);
        isoLeftTop = isoTopTop + (gridSquareSize/2);

        return new GridSquare(new IsometricLocation(isoTopTop,isoTopLeft,isoLeftTop,isoLeftLeft),row,column);
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

}
