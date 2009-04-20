package castaway.world;

/**
 * Provides access to the sectors currently in memory, as well as methods for
 * loading and saving persistent sectors.
 * 
 * @author Andy
 */
public class SectorManager {

    // 3x3 grid of the sectors adjacent to the player
    private Sector sectorNW;
    private Sector sectorN;
    private Sector sectorNE;
    private Sector sectorW;
    private Sector sectorMid;
    private Sector sectorE;
    private Sector sectorSW;
    private Sector sectorS;
    private Sector sectorSE;
    
    public int getBoundaryTop()
    {
        return -1; // TODO
    }
    
    public int getBoundaryBottom()
    {
        return -1; // TODO
    }
    
    public int getBoundaryLeft()
    {
        return -1; // TODO
    }
    
    public int getBoundaryRight()
    {
        return -1; // TODO
    }
}