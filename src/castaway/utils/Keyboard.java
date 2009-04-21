package castaway.utils;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Font;
/**
 *
 * @author Josh
 */
public class Keyboard {

    public static final String KEY_UP = "up";
    public static final String KEY_DOWN = "down";
    public static final String KEY_LEFT = "left";
    public static final String KEY_RIGHT = "right";
    public static final String KEY_FIRE = "fire";

    private static final String[][] keys = {{"Q","W","E","R","T","Y","U","I","O","P"},{"A","S","D","F","G","H","J","K","L","'"},{"Z","X","C","V","B","N","M",",",".","!"},{"SPACE","DONE"}};

    private boolean hasFocus = false;

    private int focusRow,focusColumn;
    
    public void paint(Graphics g,int width,int height){
        
        Font font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
        int keyboardHeight = (font.getHeight()+4) *keys.length;
        int keyboardTop = height - keyboardHeight;
        
        g.setColor(0x00ffffff);
        g.fillRoundRect(5, keyboardTop, width-10, keyboardHeight, 30, 30);

        int keyWidth = (width-10) / 10;
        int keyHeight = font.getHeight()+4;

        g.setFont(font);
        
        for (int i=0;i<keys.length-1;i++){
            for (int f=0;f<keys[i].length;f++){
                int paintx = 5 + (f*keyWidth) + (keyWidth/2) - (font.stringWidth(keys[i][f])/2);
                int painty = keyboardTop + (i*keyHeight) + (keyHeight/2) - ((font.getHeight()+4)/2);

                if (i == focusRow && f == focusColumn)
                    g.setColor(0x00ffaa99);
                else
                    g.setColor(0x00000000);

                g.drawString(keys[i][f], paintx, painty, g.TOP|g.LEFT);
            }
        }


        //draw 'space'
        if (keys.length-1 == focusRow && focusColumn <5)
            g.setColor(0x00ffaa99);
        else
            g.setColor(0x00000000);
        g.drawString(keys[keys.length-1][0], 30, keyboardTop + (keyboardHeight/4)*3, g.TOP|g.LEFT);

        //draw 'done'
        if (keys.length-1 == focusRow && focusColumn >=5)
            g.setColor(0x00ffaa99);
        else
            g.setColor(0x00000000);
        g.drawString(keys[keys.length-1][1], width - 30 - font.stringWidth(keys[keys.length-1][1]), keyboardTop + (keyboardHeight/4)*3, g.TOP|g.LEFT);


        for (int i=0;i<keys[keys.length-1].length;i++){

        }
    }

    public void setFocus(boolean focus){
        this.hasFocus=focus;
    }

    public boolean hasFocus(){
        return this.hasFocus;
    }

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

}
