package castaway.world.tile;

import castaway.world.entity.WorldEntity;

/**
* A tile representing a world entity.
* 
* @author Andy
*/
public class ObjectTile {

   private WorldEntity entity;
   
   /**
    * Constructor.
    * 
    * @param type
    */
   public ObjectTile(WorldEntity entity) {
       this.entity = entity;
   }

    /**
     * @return the entity
     */
    public WorldEntity getEntity() {
        return entity;
    }
    
    /**
     * @param entity the entity to set
     */
    public void setEntity(WorldEntity entity) {
        this.entity = entity;
    }
}