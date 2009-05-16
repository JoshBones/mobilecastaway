package castaway.ui.mainmenu;

import castaway.canvas.CastawayGameCanvas;
import castaway.events.Event;
import castaway.events.EventListener;
import castaway.utils.*;

/**
 *
 * @author Josh
 * @version 3.0
 */
public class MainMenu extends CastawayGameCanvas{

    //Menu Constants
    private final String MENU_MAIN = "main";
    private final String MENU_NEW = "new";
    private final String MENU_LOAD = "load";
    private final String MENU_OPTIONS = "options";

    //Menus
    private Map menus = new Map();
    private String currentMenu = "";
    private String nextMenu = MENU_MAIN;

    //Images
    private ImageManager imgMan;
    private String imgSand = "sand.jpg",imgWave ="wave.gif";

    //Wave Controls
    private boolean rigNextWave = false;
    private Wave wave;

    //Events
    String action=""; //pending action. executed when wave is finished

    public MainMenu(EventListener el,String name,ImageManager imgMan) {
        super(el,name,imgMan);

        this.setKeyPressIgnore(2);

        wave = new Wave(g,false,HEIGHT,imgMan.getImage(imgWave));

        this.imgMan=imgMan;

        setupMenus();
    }

    private void setupMenus(){
        menus.add(MENU_MAIN, new VerticalMenu(40,"MainMenu.gif","MainMenuH.gif",0,g,WIDTH,HEIGHT,imgMan));
        menus.add(MENU_NEW, new NewGameMenu(HEIGHT,WIDTH,g));
        //menus.add(MENU_OPTIONS, new VerticalMenu(25,"menu","menuH",0,g,WIDTH,HEIGHT,imgMan));
        //etc.
    }

    protected void prePaint(){
        g.drawImage(imgMan.getImage(imgSand), 0, 0, g.TOP|g.LEFT);

        resetGraphics();

        if (wave.isDone()){
            
            if (rigNextWave){
                wave = new Wave(g,true,HEIGHT,imgMan.getImage(imgWave));
                rigNextWave = false;
            }
            else{
                if (!action.equals("")){
                    doEvent(action);
                    this.isRunning(false);
                }
                wave = new Wave(g,false,HEIGHT,imgMan.getImage(imgWave));
            } 
        }
        
        resetGraphics();

        if (wave.advanceMenu()){
            if (action.equals("")){
                currentMenu =nextMenu;
            }
            else{
                currentMenu="";
            }
            
        }

        if (wave.isRigged()){
            if (! currentMenu.equals("")){
                ((Menu) menus.get(currentMenu)).updateMenu();
                resetGraphics();
            }
            wave.updateWave();
        }
        else{
            wave.updateWave();
            resetGraphics();
            if (! currentMenu.equals("")){
                ((Menu) menus.get(currentMenu)).updateMenu();
            }
        }

    }

    protected void firePressed(){
        if (! currentMenu.equals("")){
            int selectedIndex = ((VerticalMenu) menus.get(currentMenu)).getSelectedIndex();
        
            if (currentMenu.equals(MENU_MAIN)){

                switch(selectedIndex){
                    case 0:
                        nextMenu = MENU_NEW;
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        action = Event.EVENT_GAME_EXIT;
                        break;
                }

            }
            else if (currentMenu.equals(MENU_LOAD)){

            }
            else if (currentMenu.equals(MENU_OPTIONS)){

            }
            else if (currentMenu.equals(MENU_NEW)){

            }
        }        

        rigNextWave = true;
    }

    protected void upPressed(){
        int selectedIndex = ((VerticalMenu) menus.get(currentMenu)).getSelectedIndex();
        
        if (currentMenu.equals(MENU_MAIN)){
            if (selectedIndex == 0)
                selectedIndex=3;
            else
                selectedIndex--;
        }
        else if (currentMenu.equals(MENU_LOAD)){

        }
        else if (currentMenu.equals(MENU_OPTIONS)){

        }
        else if (currentMenu.equals(MENU_NEW)){
            return;
        }

        ((VerticalMenu) menus.get(currentMenu)).setSelectedIndex(selectedIndex);
    }

    protected void downPressed(){
        int selectedIndex = ((VerticalMenu) menus.get(currentMenu)).getSelectedIndex();
        
        if (currentMenu.equals(MENU_MAIN)){
            if (selectedIndex == 3)
                selectedIndex=0;
            else
                selectedIndex++;
        }
        else if (currentMenu.equals(MENU_LOAD)){

        }
        else if (currentMenu.equals(MENU_OPTIONS)){

        }
        else if (currentMenu.equals(MENU_NEW)){
            return;
        }

        ((VerticalMenu) menus.get(currentMenu)).setSelectedIndex(selectedIndex);
    }

    public void destroy(){
        imgSand = null;
        imgWave = null;
    }
}
