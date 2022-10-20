package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Dude {
    private Location loc;
    private WumpusWorld myWorld;
    private Texture texture;

    public Dude(Location loc, WumpusWorld myWorld) {
        this.loc = loc;
        this.myWorld = myWorld;
        this.texture = new Texture("guy.png");
    }




    public void draw(SpriteBatch spriteBatch){
        Point myPoint = myWorld.convertRowColToCoords(loc);
        spriteBatch.draw(texture, 100,100);
    }

    public void moveRight(){
        if(loc.getCol()+1<10){
            loc.setCol(loc.getCol()+1);

        }
    }

    public void moveLeft(){
        
    }

    public int getNumRows(){
        return myWorld.;
    }

}
