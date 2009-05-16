package castaway.ui.mainmenu;

import castaway.utils.Keyboard;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Font;

/**
 *
 * @author Josh
 */
public class NewGameMenu implements Menu{

    //canvas info
    private int canvasHeight;
    private int canvasWidth;
    private Graphics g;

    //menu
    private Font f= Font.getFont(Font.FACE_MONOSPACE,Font.STYLE_BOLD,Font.SIZE_LARGE);
    private String title = "What is your name?";
    private int titleLength = f.stringWidth(title);
    private int topOffset;
    private int textBoxWidth,textBoxHeight;

    //keyboard
    private Keyboard k = new Keyboard();
    private String name="bob";

    public NewGameMenu(int canvasHeight,int canvasWidth, Graphics g) {
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;
        this.g = g;
        topOffset = canvasHeight/4;
        textBoxWidth = canvasWidth /6 * 5;
        textBoxHeight = f.getHeight() + f.getHeight()/4;

        k.setFocus(true);
    }
    
    public void updateMenu(){
        g.setColor(0x00000000);
        g.drawString(title, (canvasWidth/2) - (titleLength/2), topOffset, g.TOP|g.LEFT);
        g.setColor(0x00ffffff);
        g.fillRoundRect(canvasWidth/2 - textBoxWidth/2, topOffset + f.getHeight() + 10, textBoxWidth, textBoxHeight, 5, 5);
        g.setColor(0x00000000);
        g.drawRoundRect(canvasWidth/2 - textBoxWidth/2, topOffset + f.getHeight() + 10, textBoxWidth, textBoxHeight, 5, 5);
        g.drawString(name, canvasWidth/2 - textBoxWidth/2 + 3, topOffset + f.getHeight() + f.getHeight()/8 + 10, g.TOP|g.LEFT);
        k.paint(g, canvasWidth, canvasHeight);
    }
    
}
