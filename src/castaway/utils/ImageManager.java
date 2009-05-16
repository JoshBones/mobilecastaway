package castaway.utils;

import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Image;

/**
 * Retrieves images as requested by canvas. 
 * Restricts the number of images held in memory.
 * 
 * @author Josh
 * @version 1.0
 */
public class ImageManager {

    private final int maxImages = 10; //max number of images to hold in memory
    
    private Vector images = new Vector();
    private Vector imageNames = new Vector();
    private Vector lastUsedRound = new Vector();
    
    private int round =0;

    private String resourcePath;

    /**
     * used to set location of image folder
     * 
     * @param resourcePath file path location of images
     */
    public ImageManager(String resourcePath) {
        this.resourcePath=resourcePath;

        //warm vectors
        for (int i=0;i<maxImages;i++){
            images.addElement(null);
            imageNames.addElement(null);
            lastUsedRound.addElement(String.valueOf(0));
        }
    }

    /**
     * retrieves an image based on the filename passed in.
     * Either returns image if its in memory,
     * or attempts to retrieve then return image if it isn't
     * 
     * @param name image filename to retrieve.
     * @return Image retrieved or null if no such image exists
     * @throws IOException if no such image
     */
    public Image getImage(String name){
        round ++;
                
        if (imageNames.contains(name)){//found image in memory
            int index = imageNames.indexOf(name);
            lastUsedRound.setElementAt(String.valueOf(round), index);
            return (Image) images.elementAt(index); 
        }    
        else{//image not im memory
            try{
                Image newImg = Image.createImage(resourcePath + name);
                int chosenIndex = chooseNextIndex();

                imageNames.setElementAt(name, chosenIndex);
                images.setElementAt(newImg, chosenIndex);

                return (Image) images.elementAt(chosenIndex);
            }
            catch (IOException ioe){
                System.out.println("Error retrieving image " + name + " from " + resourcePath + ". " + ioe.getMessage());
                return null;
            }
        }

    }
    
    /**
     * searches for a position to place a new image in.
     * returns first empty position if possible,
     * otherwise clears an old index and returns it.
     * index chosen by a Least Recently Used (LRU) policy
     * 
     * @return index of vector to place new image in.
     */
    private int chooseNextIndex(){
        int chosenIndex=0;
        
        for (int i=0;i<maxImages;i++){
            if (imageNames.elementAt(i)  == null){
                return i; //found and empty index
            }
            
            if (Integer.parseInt(lastUsedRound.elementAt(i).toString()) < chosenIndex){
                chosenIndex = i; //marked an index as more suitable for replacement
            }
        }
        
        cleanup(chosenIndex);
        lastUsedRound.setElementAt(String.valueOf(round), chosenIndex);

        return chosenIndex;
    }

    /**
     * disposes of specified image
     * 
     * @param index index of image to dispose of
     */
    private void cleanup(int index){
        images.setElementAt(null, index);
        imageNames.setElementAt(null, index);
        lastUsedRound.setElementAt(null, index);
    }

    /**
     * disposes of all images in memory
     */
    public void cleanupAll(){
        for (int i=0;i<maxImages;i++){
            images.setElementAt(null, i);
            imageNames.setElementAt(null, i);
        }
        
        images = null;
        imageNames = null;
    }
}
