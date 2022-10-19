package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
            windTile,glitterTile,stinkTile,blackTile;
    public static final int GROUND = 0, SPIDER = 1, PIT = 2, WUMPUS = 3, GOLD = 4,
            WEB = 11, WIND = 12, STINK = 13, GLITTER = 14;

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
        tileWidth = blackTile.getWidth();

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

    public boolean isValid(Location loc){
        return loc.getRow()>= 0 && loc.getCol()< world.length&&
                loc.getCol()>= 0 && loc.getCol()< world[0].length;
    }

    public void placeTile(int tileId, Location loc){
        if(isValid(loc)){
            world[loc.getRow()][loc.getCol()] = tileId;
        }
    }

    public void draw(SpriteBatch spriteBatch){
        for(int row = 0; row<world.length; row ++){
            for( int col = 0; col< world[row].length; col++){
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
