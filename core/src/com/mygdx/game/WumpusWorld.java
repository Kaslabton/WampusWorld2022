package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;

public class WumpusWorld {
    int world[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    boolean visible[][] = new boolean [10][10];

    private final int xoffset = 20 ;
    private final int yoffest = 500;

    private final int tileWidth;


    private Texture groundTile, spiderTile, pitTile,wumpusTile,goldTile,webTile,
            windTile,glitterTile,stinkTile,blackTile;emptyChest;
    public static final int GROUND = 0, SPIDER = 1, PIT = 2, WUMPUS = 3, GOLD = 4,
            WEB = 11, WIND = 12, STINK = 13, GLITTER = 14, EMPTYCHEST = 15;

    public WumpusWorld(){
        groundTile = new Texture("groundTile.png");
        spiderTile = new Texture("spiderTile.png");
        pitTile = new Texture("pitTile.png");
        wumpusTile = new Texture("wumpusTile.png");
        goldTile = new Texture("goldTile.png");
        webTile = new Texture("webTile.png");
        windTile = new Texture("windTile.png");
        glitterTile = new Texture("glitterTile.png");
        stinkTile = new Texture("stinkTile.png");
        blackTile = new Texture("blackTile.png");
        emptyChest = new Texture("emptyChest.png");
        tileWidth = blackTile.getWidth();

    }

    public void makeVisible(Location loc){
        if(isValid(loc)){

        }
    }
    public Location convertCoordsToRowCol(int x, int y){
        int row;//y
        int col;//x
        //x = 30  col = 0
        col = (x-xoffset)/50;
        //y = 73, row = 0
        //y = 130, row = 1
        row = (y-50)/50;

        System.out.println("col is " + col);
        System.out.println("row is " + row);
        return new Location(col,row);
    }

    public void addHints(ArrayList<Location> loc, int tileId){

    }

    public void placeTile (int tileId, Location loc){
        if(isValid(loc)){
            world[loc.getRow()][loc.getCol()]= tileId;
            if(tileId == GROUND) {
                addHints(getNeighbors(loc), GROUND);
            }
            else
                addHints(getNeighbors(loc), tileId+10);
        }
    }
    public ArrayList<Location> getNeighbors(Location loc){
        Location above = new Location (loc.getRow()-1, loc.getCol());
        Location below = new Location (loc.getRow()+1, loc.getCol());
        Location right = new Location (loc.getRow(), loc.getCol()+1);
        Location left = new Location (loc.getRow(), loc.getCol()-1);

        ArrayList<Location> locs = new ArrayList<>();
        if(isValid(above)){
            locs.add(above);
        }
        if(isValid(below)){
            locs.add(below);
        }
        if(isValid(right)){
            locs.add(right);
        }
        if(isValid(left)){
            locs.add(left);
        }
        return locs;
    }

    public boolean isValid(Location loc){
        return loc.getRow()>= 0 && loc.getCol()< world.length&&
                loc.getCol()>= 0 && loc.getCol()< world[0].length;
    }

    public int getTile(Location loc){
        if(isValid(loc)){
            return world[loc.getRow()][loc.getCol()];
        }
        return -1;//if given loc is not valid
    }


    public Point convertRowColToCoords(Location loc){
        int x = (loc.getCol()*50)+xoffset;
        int y = (loc.getRow()*50)-(600-yoffest);
        return new Point(x,0);
    }

    public void Reset(){
        for(int i= 0; i< world.length;i++){
            for(int j= 0; j< world[i].length;j++){
                world[i][j]= 0;
                visible[i][j] = false;
            }
        }
    }
    public void removeGold(Location loc){
        if (isValid(loc) && world[loc.getRow()][loc.getCol()] == GOLD){
            ArrayList<Location> n = getNeighbors();
            world[loc.getRow()][loc.getCol()]
        }
    }

    public void draw(SpriteBatch spriteBatch, boolean showHidden){
        for(int row = 0; row<world.length; row ++){
            for( int col = 0; col< world[row].length; col++){
                //if(world[row][col]==GROUND && (visible[row][col]||showHidden))
                //    spriteBatch.draw(blackTile);
                if(world[row][col] == GROUND)
                    spriteBatch.draw(groundTile,xoffset+row*50, yoffest-col*50);
                else if(world[row][col] == SPIDER)
                    spriteBatch.draw(spiderTile,xoffset+row*50, yoffest-col*50);
                else if(world[row][col] == WUMPUS)
                    spriteBatch.draw(wumpusTile,xoffset+row*50, yoffest-col*50);
                else if(world[row][col] == PIT)
                    spriteBatch.draw(pitTile,xoffset+row*50, yoffest-col*50);
                else if(world[row][col] == WIND)
                    spriteBatch.draw(windTile,xoffset+row*50, yoffest-col*50);
                else if(world[row][col] == GLITTER)
                    spriteBatch.draw(glitterTile,xoffset+row*50, yoffest-col*50);
                else if(world[row][col] == GOLD)
                    spriteBatch.draw(goldTile,xoffset+row*50, yoffest-col*50);
                else if(world[row][col] == WEB)
                    spriteBatch.draw(webTile,xoffset+row*50, yoffest-col*50);
                else if(world[row][col] == STINK)
                    spriteBatch.draw(stinkTile,xoffset+row*50, yoffest-col*50);
                else if(world[row][col] == EMPTYCHEST && (visible[row][col]||showHidden))
                    spriteBatch.draw(emptychestTile,xoffset+col*tileWidth, yoffest-row*tile);
            }//end inner for
        }//end outer for
    }//end method draw

    public Texture getGroundTile() {
        return groundTile;
    }

    public Texture getSpiderTile() {
        return spiderTile;
    }

    public Texture getPitTile() {
        return pitTile;
    }

    public Texture getWumpusTile() {
        return wumpusTile;
    }

    public Texture getGoldTile() {
        return goldTile;
    }
}//end class
