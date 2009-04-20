package castaway.world;

import castaway.world.tile.Tile;

/**
 * Provides access to the tiles and entities in the world.
 * 
 * @author Andy
 */
public class Map {

    /**
     * The region of the map which currently exists in memory.
     */
    private SectorManager sectors;
    
    /**
     * Gets a tile at the specified coordinates.
     * 
     * @param x
     * @param y
     * @return tile
     * @throws IndexOutOfBoundsException
     */
    public Tile getTile(int x, int y) throws IndexOutOfBoundsException {
        
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