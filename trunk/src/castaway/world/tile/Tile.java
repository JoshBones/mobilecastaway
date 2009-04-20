package castaway.world.tile;

/**
 * A map tile.
 * 
 * @author Andy
 */
public abstract class Tile {
    
    private String image;
    
    /**
     * @return image for this tile
     */
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
}