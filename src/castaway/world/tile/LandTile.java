package castaway.world.tile;

/**
 * Represents a landscape tile.
 * 
 * @author Andy
 */
public class LandTile {

    private LandType type;
    
    /**
     * Constructor.
     * 
     * @param type
     */
    public LandTile(LandType type) {
        this.type = type;
    }
    
    /**
     * @return the type
     */
    public LandType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(LandType type) {
        this.type = type;
    }
}