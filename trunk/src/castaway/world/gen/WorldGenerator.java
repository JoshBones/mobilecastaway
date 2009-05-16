package castaway.world.gen;

/**
 * Generates a random world. There are constraints on the min/max amount of
 * certain objects to ensure that game difficulty doesn't vary too
 * significantly.
 * 
 * Randomisation affects:
 *   - Island shape
 *   - Placement of flora and fauna
 *   - Placement of 'scripted' areas which always exist on an island
 *   - Graphics associated with berries/fruits etc so they are initially
 *     unidentifiable.
 * 
 * @author Andy
 */
public class WorldGenerator {

    //tiles are either land/water

    //1st step is define island boundaries within a limited but flexible size range

    //next we need to split the map into water/land, where most of the land clumps towards the middle, most of the water towards the edges.

    //need an algorithm to ensure it isnt just a random mix of land/water tiles

    //perhaps an algorithm that:
        //1. fills map with water
        //2. traces the boundary of the island
        //3. backfills the island once boundary defined
        //4. modifies tiles as appropriate to add shore tiles

}
