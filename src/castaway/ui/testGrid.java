package castaway.ui;

import castaway.canvas.CastawayGameCanvas;
import castaway.utils.ImageManager;
import castaway.events.EventListener;
import castaway.grid.IsometricEngine;
import castaway.grid.GridSquare;

import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * tests grid package
 *
 * @author Josh
 */
public class testGrid extends CastawayGameCanvas{

    private IsometricEngine gameGrid = new IsometricEngine(20,20,10);
    private GridSquare[][] grid = gameGrid.getGrid();
    private ImageManager imgMan;

    private Image background;
    
    private Random rnd = new Random();

    public testGrid(EventListener el,String name,ImageManager imgMan) {
        super(el,name,imgMan);

        this.imgMan = imgMan;
        this.showFrame(true);

        this.background = Image.createImage(20*gameGrid.getGridHeight(), 20*gameGrid.getGridWidth());

        Graphics gBack = background.getGraphics();

        boolean land[][] = makeIsland();

        for (int x=0;x<gameGrid.getGridHeight();x++){

            for (int y=0;y<gameGrid.getGridWidth();y++){
                resetGraphics();

                gBack.setClip(grid[x][y].getLocation().getLeft(),grid[x][y].getLocation().getTop(),20,20);

                if (land[x][y] == true){
                    gBack.drawImage(imgMan.getImage("testTile.gif"), grid[x][y].getLocation().getLeft(),grid[x][y].getLocation().getTop(), g.TOP|g.LEFT);
                }
                else{
                    gBack.drawImage(imgMan.getImage("testTile.gif"), grid[x][y].getLocation().getLeft() - 20,grid[x][y].getLocation().getTop(), g.TOP|g.LEFT);
                }
                
                //gBack.drawImage(imgMan.getImage("testTile.gif"), grid[x][y].getLocation().getLeft() - (rnd.nextInt(2) * 20),grid[x][y].getLocation().getTop(), g.TOP|g.LEFT);

            }

        }

    }

    private boolean[][] makeIsland(){
        //true = land, false = water
        boolean land[][] = new boolean[gameGrid.getGridHeight()][gameGrid.getGridWidth()];

        //set all to water
        for (int i=0;i<land.length;i++){
            for (int x=0;x<land[i].length;x++){
                land[i][x]=false;
            }
        }

        //choose starting square for shoreline,setting to default of (1,1)
        int x=1,y=1;
        
        //randomly determine width of row 1
        
        Random r= new Random();
        int length = r.nextInt(gameGrid.getGridWidth() - y - 1);//-1 ensures at least one water tile
        for (int i=y;i<length;i++){
            land[x][i]=true;
        }

        int yChange=1; //change in y pos 0=-- 1=1 2=++

        for (int i=1;i<land.length;i++){
            switch (r.nextInt(2)){
                case 0:
                    break;
                case 1:
                    y += ((i%2==1)? -1 : +1);
                    yChange+=((i%2==1)? -1 : +1);
                    if (y==0){
                        y++;
                        yChange++;
                    }
                    else if (y==gameGrid.getGridWidth()){
                        y--;
                        yChange--;
                    }
                    break;
            }

            switch (r.nextInt(2)){
                case 0:
                    break;
                case 1:
                    length += ((i%2==1)? -1 : +1);
                    if (length < 3)
                        length=3;
                    else if (y + length >=gameGrid.getGridWidth())
                        length = gameGrid.getGridWidth()-y-1;
                    break;
            }

            if (yChange == 0)
                length --;
            else if (yChange == 2)
                length ++;

            for (int f=0;f<length;f++){
                land[i][y+f] =true;
            }
        }

        /*for (int i=1;i<land.length;i++){

        if (x%2==0 && y > 1){
        y -= r.nextInt(2);
        }
        else if (y<gameGrid.getGridWidth() -1){
        y += r.nextInt(2);
        }
        else{
        y--;
        }

        length = r.nextInt(gameGrid.getGridWidth() - y - 1);//-1 ensures at least one water tile

        for (int f=y;f<length;f++){
        land[i][f]=true;
        }

        }*/

        return land;
    }

    protected void prePaint() {
        g.drawImage(background, 0, 0, g.TOP|g.LEFT);
        resetGraphics();
    }

}
