package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Dude {
    private Location loc;
    private WumpusWorld myWorld;
    private Texture texture;
    private boolean hasGold = false;

    private boolean killtheWumpus = false;

    public Dude(Location loc, WumpusWorld myWorld, boolean hasGold) {
        this.loc = loc;
        this.myWorld = myWorld;
        this.texture = new Texture("guy.png");
        this.hasGold = hasGold;
    }

    public boolean HasGold() {
        return hasGold;
    }

    public void setHasGold(boolean hasGold) {
        this.hasGold = hasGold;
    }

    public boolean killedWumpus(){
        return killedWumpus();
    }
    //this method makes 1step
    public void step(){
        int chice = (int)(1+ Math.random()*4);
        if (chice ==1){
            moveDown();
        }
        if (chice ==2){
            moveRight();
        }
        if (chice ==3){
            moveLeft();
        }
        else{
            moveUp();
        }
    }

    public void draw(SpriteBatch spriteBatch){
        Point myPoint = myWorld.convertRowColToCoords(loc);
        spriteBatch.draw(texture, 100,100);
    }

    public int getTotalsteps(){

    }

    public void moveRight(){
        if(loc.getCol()+1<10){
            loc.setCol(loc.getCol()+1);
            myWorld.makeVisible(loc);
                    totalsteps ++;
        }
    }

    public void moveLeft(){
        
    }

    public void moveDown(){

    }

    public void moveUp{

    }

    public int getNumRows(){
        return myWorld.;
    }

    public void reset (Location loc){
        this.loc = loc;
        myWorld.makeVisible(loc);
    }

}
