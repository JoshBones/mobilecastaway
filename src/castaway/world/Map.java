package castaway.world;

import castaway.world.tile.Tile;

/**
 * Provides access to the tiles and entities in the world.
 * 
 * @author Andy
 */
public class Map {

    /**
     * The map divided into sectors.
     */
    private SectorManager sectors;
    
    /**
     * Get an array of the tiles at the current coordinate, from the bottom
     * position upwards.
     * 
     * @param x
     * @param y
     * @return tile
     * @throws IndexOutOfBoundsException
     */
    public Tile[] getTiles(int x, int y) throws IndexOutOfBoundsException {
        
        // Check boundaries
        if (x < sectors.getBoundaryLeft() ||
            x > sectors.getBoundaryRight()) {
            throw new IndexOutOfBoundsException(
                    "Cannot access tiles outside of adjacent sectors. " +
                    "x: " + x + ". y: " + y + ".");
        }
        if (y < sectors.getBoundaryTop() ||
            y > sectors.getBoundaryBottom()) {
            throw new IndexOutOfBoundsException(
                    "Cannot access tiles outside of adjacent sectors. " +
                    "x: " + x + ". y: " + y + ".");
            }
        
        return null;
    }
}