package castaway.world;

import java.util.Vector;

import castaway.utils.Persistent;
import castaway.world.tile.Tile;

/**
 * Represents a portion of the world map, containing the tiles of that region.
 * 
 * @author Andy
 */
public class Sector implements Persistent {
    
    //--------------------
    // Static Members
    //--------------------
    
    // Constants
    public static final int DEFAULT_SECTOR_SIZE = 10; // TODO: Use config file for this instead
    
    // Sector size
    private static int size = DEFAULT_SECTOR_SIZE;
    
    /**
     * @return the size
     */
    public static int getSize() {
        return size;
    }
    
    //--------------------
    // Non-static Members
    //--------------------
    
    // The offset of this sector
    private int offsetX, offsetY;
    
    // The tiles within this sector
    private Vector[][] tiles;
    
    /**
     * Constructor.
     */
    public Sector(int offsetX, int offsetY) {
        tiles = new Vector[size][size];
    }
    
    /**
     * Gets the tiles at the specified coordinate. This coordinate is relative
     * to the offset of this sector (ie. 0,0 would be the top-left of the
     * sector).
     * 
     * @param x
     * @param y
     * @return array of tiles at the current position
     */
    public Tile[] getTiles(int x, int y) {
        Tile[] tileList = new Tile[tiles[x][y].size()];
        tiles[x][y].copyInto(tileList);
        
        return tileList;
    }
    
    /**
     * Returns the number of tiles that exist at the specified coordinate.
     * 
     * @param x
     * @param y
     * @return
     */
    public int getNumTiles(int x, int y) {
        return tiles[x][y].size();
    }
    
    /**
     * Add a tile at the specified coordinate and z-position.
     * 
     * @param tile
     * @param x
     * @param y
     * @param zPos
     */
    public void addTile(Tile tile, int x, int y, int zPos) {
        tiles[x][y].insertElementAt(tile, zPos);
    }
    
    /**
     * Add a tile to the top at the specified coordinate.
     * 
     * @param tile
     * @param x
     * @param y
     */
    public void addTile(Tile tile, int x, int y) {
        tiles[x][y].addElement(tile);
    }
    
    /**
     * Remove a tile from the specified coordinate.
     * 
     * @param x
     * @param y
     * @param tile
     * @return
     */
    public boolean removeTile(int x, int y, Tile tile) {
        return tiles[x][y].removeElement(tile);
    }
    
    /**
     * Remove a tile from the specified coordinate.
     * 
     * @param x
     * @param y
     * @param zPos
     */
    public void removeTile(int x, int y, int zPos) {
        tiles[x][y].removeElementAt(zPos);
    }
    
    //--------------------
    // Getters/Setters
    //--------------------
    
    /**
     * @return the offsetX
     */
    public int getOffsetX() {
        return offsetX;
    }

    /**
     * @param offsetX the offsetX to set
     */
    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    /**
     * @return the offsetY
     */
    public int getOffsetY() {
        return offsetY;
    }

    /**
     * @param offsetY the offsetY to set
     */
    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
}