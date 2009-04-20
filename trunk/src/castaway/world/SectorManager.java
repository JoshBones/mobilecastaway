package castaway.world;

/**
 * Provides access to the sectors currently in memory, as well as methods for
 * loading and saving persistent sectors.
 * 
 * @author Andy
 */
public class SectorManager {
    
    // 3x3 grid of the sectors adjacent to the player
    private Sector sectorNW, sectorN, sectorNE,
                   sectorW, sectorMid, sectorE,
                   sectorSW, sectorS, sectorSE;
    
    public int getBoundaryTop()
    {
        return sectorN.getOffsetY();
    }
    
    public int getBoundaryBottom()
    {
        return sectorS.getOffsetY() + Sector.getSize();
    }
    
    public int getBoundaryLeft()
    {
        return sectorW.getOffsetX();
    }
    
    public int getBoundaryRight()
    {
        return sectorE.getOffsetX() + Sector.getSize();
    }
}