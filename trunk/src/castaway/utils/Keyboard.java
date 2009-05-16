package castaway.utils;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Font;
/**
 * This class is used to print an on-screen keyboard 
 * and return keypresses one at a time.
 * Termination of the on screen keyboard is up to 
 * the class that owns the keyboard, but should be done
 * when the keycode "DONE" is returned.
 *
 * @author Josh
 * @version 1.0
 */
public class Keyboard {

    public static final String KEY_UP = "up";
    public static final String KEY_DOWN = "down";
    public static final String KEY_LEFT = "left";
    public static final String KEY_RIGHT = "right";
    public static final String KEY_FIRE = "fire";

    /**
     * specifies the QWERTY keyboard layout.
     */
    public static final String[][] LAYOUT_QWERTY = {{"Q","W","E","R","T","Y","U","I","O","P"},{"A","S","D","F","G","H","J","K","L","<<"},{"Z","X","C","V","B","N","M",",",".","!"},{"SPACE","DONE"}};
    /**
     * specifies an alphabetic keyboard layout
     */
    public static final String[][] LAYOUT_ALPHA = {{"A","B","C","D","E","F","G","H","I","J"},{"K","L","M","N","O","P","Q","R","S","<<"},{"T","U","V","W","X","Y","Z",",",".","!"},{"SPACE","DONE"}};
    /**
     * maintains a reference to the currently used leyout
     */
    private String[][] keys = LAYOUT_QWERTY;

    private boolean hasFocus = false;

    // row and column of currently focused letter
    private int focusRow,focusColumn;

    // defaults for keyboard colours
    private int background = 0x00ffffff;
    private int text = 0x00000000;
    private int textHighlight = 0x00ff0000;

    public Keyboard() {}

    /**
     * create keyboard with pre-defined attributes
     *
     * @param layout specify keyboard layout. either LAYOUT_ALPHA or LAYOUT_QWERTY
     * @param backgroundColor keyboard background colour
     * @param textColor keyboard text colour
     * @param textHighlight keyboard text highlight colour
     */
    public Keyboard(String[][] layout,int backgroundColor,int textColor,int textHighlight)throws Exception {
        this.keys = layout;
        this.background = backgroundColor;
        this.text = textColor;
        this.textHighlight = textHighlight;
    }

    /**
     * paints the keyboard on the lower part of the graphics object passed in
     */
    public void paint(Graphics g,int width,int height){
        
        Font font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
        int keyboardHeight = (font.getHeight()+4) *keys.length;
        int keyboardTop = height - keyboardHeight;
        
        g.setColor(background);
        g.fillRoundRect(5, keyboardTop, width-10, keyboardHeight, 20, 20);

        int keyWidth = (width-10) / 10;
        int keyHeight = font.getHeight()+4;

        g.setFont(font);
       
        //draw letters/symbols
        for (int i=0;i<keys.length-1;i++){
            for (int f=0;f<keys[i].length;f++){
                int paintx = 5 + (f*keyWidth) + (keyWidth/2) - (font.stringWidth(keys[i][f])/2);
                int painty = keyboardTop + (i*keyHeight) + (keyHeight/2) - ((font.getHeight()+4)/2);

                if (i == focusRow && f == focusColumn)
                    g.setColor(textHighlight);
                else
                    g.setColor(text);

                g.drawString(keys[i][f], paintx, painty, g.TOP|g.LEFT);
            }
        }


        //draw 'space'
        if (keys.length-1 == focusRow && focusColumn <5)
            g.setColor(textHighlight);
        else
            g.setColor(text);
        g.drawString(keys[keys.length-1][0], 30, keyboardTop + (keyboardHeight/4)*3, g.TOP|g.LEFT);

        //draw 'done'
        if (keys.length-1 == focusRow && focusColumn >=5)
            g.setColor(textHighlight);
        else
            g.setColor(text);
        g.drawString(keys[keys.length-1][1], width - 30 - font.stringWidth(keys[keys.length-1][1]), keyboardTop + (keyboardHeight/4)*3, g.TOP|g.LEFT);
    }

    /**
     * flag used to give/remove the keyboards input focus
     */
    public void setFocus(boolean focus){
        this.hasFocus=focus;
    }

    /**
     * flag that registers that the keyboard currently has input focus
     */
    public boolean hasFocus(){
        return this.hasFocus;
    }

    /**
     * fires when the owning class passes a keypress to the keyboard
     */
    public String gotKeypress(String key){

        if (key.equals(KEY_UP)){
            if (focusRow == 0){
                focusRow = keys.length-1;
            }
            else{
                focusRow--;
            }
        }
        else if (key.equals(KEY_DOWN)){
            if (focusRow == keys.length-1){
                focusRow =0;
            }
            else{
                focusRow ++;
            }
        }
        else if (key.equals(KEY_LEFT)){
            if (focusRow == keys.length-1){
                focusColumn = (focusColumn <5)? 9 : 4;
            }
            else{
                focusColumn --;
                if (focusColumn == -1)
                    focusColumn = keys[focusRow].length-1;
            }
            
        }
        else if (key.equals(KEY_RIGHT)){
            if (focusRow == keys.length-1){
                focusColumn = (focusColumn <5)? 5 : 0;
            }
            else{
                focusColumn ++;
                if (focusColumn == keys[focusRow].length)
                    focusColumn = 0;
            }
        }
        else if (key.equals(KEY_FIRE)){
            if (focusRow == keys.length-1)
                return keys[focusRow][(focusColumn <5)?0:1];
            else
                return keys[focusRow][focusColumn];
        }

        return "";
    }

    /**
     * set the background fill colour of the keyboard. refreshes on next paint
     */
    public void setBackgroundColour(int background) {
        this.background = background;
    }

    /**
     * sets the colour for the keyboard text. refreshes on next paint
     */
    public void setTextColour(int text) {
        this.text = text;
    }

    /**
     * sets the highlight colour used to denote the selected key. refreshes on next paint
     */
    public void setTextHighlightColour(int textHighlight) {
        this.textHighlight = textHighlight;
    }

    /**
     * specify either the QWERTY or ALPHA layout
     */
    public void setKeyLayout(String[][] layout){
        if (layout[0][0].equals("A"))
            keys = LAYOUT_ALPHA;
        else
            keys = LAYOUT_QWERTY;
    }
}
