package castaway.world.tile;

/**
 * Enumerator for different types of LandTile. 
 * 
 * @author Andy
 */
public final class LandType {
    
    public static final LandType WATER = new LandType();
    public static final LandType GRASS = new LandType();
    public static final LandType SAND = new LandType();
    public static final LandType DIRT = new LandType();
    
    private LandType() {
    }
}