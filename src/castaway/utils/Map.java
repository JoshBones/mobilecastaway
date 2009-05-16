package castaway.utils;

import castaway.canvas.*;
import java.util.Vector;

/**
 *
 * @author Josh
 */
public class Map {

    private Vector key = new Vector();
    private Vector object = new Vector();

    // add new canvas
    public void add(String key, Object canvas){
        this.key.addElement(key);
        this.object.addElement(canvas);
    }

    // return specified canvas
    public Object get (String name){
        try{
            return object.elementAt(this.key.indexOf(name));
        }
        catch(Exception e){
            return null;
        }
    }
    
    // remove and return specified canvas
    public Object remove (String name){
        Object toReturn = object.elementAt(this.key.indexOf(name));
        object.removeElementAt(this.key.indexOf(name));
        this.key.removeElementAt(this.key.indexOf(name));
        return toReturn;
    }
    
    public void disposeOf(String name){
        object.setElementAt(null, this.key.indexOf(name));
        this.key.setElementAt(null, this.key.indexOf(name));
    }
    
    public void disposeOfAll(){
        for (int i=0;i<object.size();i++){
            key.setElementAt(null, i);
            object.setElementAt(null, i);
        }
        
        key = null;
        object = null;
    }
    
    public boolean contains(String name){
        return (this.key.indexOf(name) != -1) ? true : false;
    }
}
