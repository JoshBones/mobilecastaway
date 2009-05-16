package castaway.ui.mainmenu;

import castaway.utils.ImageManager;

import javax.microedition.lcdui.Graphics;

/**
 * Contains and paints a vertical menu
 *
 * @author Josh
 */

public class VerticalMenu implements Menu{

    //menu info
    private int itemHeight;
    private String imgNormal,imgHighlighted;
    private int selectedIndex;
    private int padding=3;
    
    //canvas info
    private Graphics g;
    private int canvasWidth,canvasHeight;
    private int topOffset;
    private ImageManager imgMan;

    public VerticalMenu(int itemHeight, String imgNormal, String imgHighlighted, int selectedIndex, Graphics g, int canvasWidth, int canvasHeight, ImageManager imgMan) {
        this.itemHeight = itemHeight;
        this.imgNormal = imgNormal;
        this.imgHighlighted = imgHighlighted;;
        this.selectedIndex = selectedIndex;
        this.g = g;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.imgMan = imgMan;
        int x = imgMan.getImage(imgNormal).getHeight();
        topOffset = canvasHeight /4;
    }

    public void updateMenu(){
        for (int i=0;i<(imgMan.getImage(imgNormal).getHeight()/itemHeight);i++){
            if (i == selectedIndex){
                try{
                    g.setClip((canvasWidth/2) - (imgMan.getImage(imgHighlighted).getWidth()/2), topOffset + (i * (itemHeight+padding)), 156, itemHeight);
                    g.drawImage(imgMan.getImage(imgHighlighted),(canvasWidth/2) - (imgMan.getImage(imgHighlighted).getWidth()/2),topOffset + (i * (itemHeight+padding)) - (i*itemHeight), g.TOP|g.LEFT);
                }
                catch(Exception e){}
            }
            else{
                try{
                    g.setClip((canvasWidth/2) - (imgMan.getImage(imgNormal).getWidth()/2), topOffset + (i * (itemHeight+padding)), 156, itemHeight);
                    g.drawImage(imgMan.getImage(imgNormal),(canvasWidth/2) - (imgMan.getImage(imgNormal).getWidth()/2),topOffset + (i * (itemHeight+padding)) - (i*itemHeight), g.TOP|g.LEFT);
                }
                catch(Exception e){}
            }
        }
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public int getTotalItems(){
        return (imgMan.getImage(imgNormal).getHeight()/itemHeight);
    }

}
