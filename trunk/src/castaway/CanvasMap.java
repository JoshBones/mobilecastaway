package castaway;

import java.util.Vector;

/**
 *
 * @author Josh
 */
public class CanvasMap {

    private Vector canvasName = new Vector();
    private Vector canvas = new Vector();

    // add new canvas
    public void add(String canvasName, CastawayGameCanvas canvas){
        this.canvasName.addElement(canvasName);
        this.canvas.addElement(canvas);
    }

    // return specified canvas
    public CastawayGameCanvas get (String name){
        return (CastawayGameCanvas) canvas.elementAt(canvasName.indexOf(name));
    }
    
    // remove and return specified canvas
    public CastawayGameCanvas remove (String name){
        CastawayGameCanvas toReturn = (CastawayGameCanvas) canvas.elementAt(canvasName.indexOf(name));
        canvas.removeElementAt(canvasName.indexOf(name));
        canvasName.removeElementAt(canvasName.indexOf(name));
        return toReturn;
    }
    
    public void disposeOf(String name){
        canvas.setElementAt(null, canvasName.indexOf(name));
        canvasName.setElementAt(null, canvasName.indexOf(name));
    }
    
    public void disposeOfAll(){
        for (int i=0;i<canvas.size();i++){
            canvasName.setElementAt(null, i);
            canvas.setElementAt(null, i);
        }
        
        canvasName = null;
        canvas = null;
    }
    
    public boolean contains(String name){
        return (canvasName.indexOf(name) != -1) ? true : false;
    }
}
