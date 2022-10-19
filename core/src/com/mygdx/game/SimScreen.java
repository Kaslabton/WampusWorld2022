package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;


public class SimScreen implements Screen{

    private static final float WORLD_WIDTH = 800;
    private static final float WORLD_HEIGHT = 600;

    //Object that allows us to draw all our graphics
    private SpriteBatch spriteBatch;

    WumpusWorld myWorld = new WumpusWorld();
    BitmapFont defaultFont = new BitmapFont();

    int currentlySelectedTile = -1;
    //Object that allwos us to draw shapes
    private ShapeRenderer shapeRenderer;

    //Camera to view our virtual world
    private Camera camera;

    //control how the camera views the world
    //zoom in/out? Keep everything scaled?
    private Viewport viewport;

    //runs one time, at the very beginning
    //all setup should happen here
    @Override
    public void show() {
        camera = new OrthographicCamera(); //2D camera
        camera.position.set(WORLD_WIDTH/2, WORLD_HEIGHT/2,0);//move the camera
        camera.update();

        //freeze my view to 800x600, no matter the window size
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        spriteBatch = new SpriteBatch();

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true); //???, I just know that this was the solution to an annoying problem
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public Point convertFromMouseToWorldCoords(int x, int y){
        Point p = new Point();
        p.x = x;

        p.y = 600-y;
        return p;
    }

    //this method runs as fast as it can, repeatedly, constantly looped
    @Override
    public void render(float delta) {
        clearScreen();
        //Ai or
        //all drawing of shapes MUST be in between begin/end
        shapeRenderer.begin();

        shapeRenderer.end();

        //all drawing of graphics MUST be in between begin/end
        spriteBatch.begin();
        myWorld.draw(spriteBatch);
        drawTooBar();
        handleMouseClick();
        drawDebug();
        spriteBatch.end();
    }

    public void handleMouseClick(){
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            //spider is (650, 140) to (700, 190)
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();
            //if(mouseX>= 650 && mouseX<= 700 && mouseY >= 90 && mouseY<= 140){
            //    currentlySelectedTile = WumpusWorld.GROUND;
            //}
            if(mouseX>= 650 && mouseX<= 700 && mouseY > 140 && mouseY<= 190){
                currentlySelectedTile = WumpusWorld.SPIDER;
            }
            else if(mouseX>= 650 && mouseX<= 700 && mouseY > 190 && mouseY<= 240){
                currentlySelectedTile = WumpusWorld.PIT;
            }
            else if(mouseX>= 650 && mouseX<= 700 && mouseY > 240 && mouseY<= 290){
                currentlySelectedTile = WumpusWorld.WUMPUS;
            }
            else if(mouseX>= 650 && mouseX<= 700 && mouseY > 290 && mouseY<= 340){
                currentlySelectedTile = WumpusWorld.GOLD;
            }
            else if(currentlySelectedTile != -1){
                Location worlLoc = myWorld.convertCoordsToRowCol(mouseX,mouseY);
                myWorld.placeTile(currentlySelectedTile, worlLoc);
                currentlySelectedTile = -1;
            }

        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    public void drawDebug(){
        defaultFont.draw(spriteBatch, "x: "+ Gdx.input.getX(), 650, 200);
        defaultFont.draw(spriteBatch, "y: "+ Gdx.input.getY(), 650, 150);
    }

    public void drawTooBar(){
        defaultFont.draw(spriteBatch, "Toolbar", 650, 550);
        spriteBatch.draw(myWorld.getGroundTile(), 650, 460);
        spriteBatch.draw(myWorld.getSpiderTile(), 650, 410);
        spriteBatch.draw(myWorld.getPitTile(), 650, 360);
        spriteBatch.draw(myWorld.getWumpusTile(), 650, 310);
        spriteBatch.draw(myWorld.getGoldTile(), 650, 260);

        if(currentlySelectedTile != -1) {
            Point p = convertFromMouseToWorldCoords(Gdx.input.getX(), Gdx.input.getY());
            p.x -= myWorld.getSpiderTile().getWidth() / 2;
            p.y -= myWorld.getSpiderTile().getHeight() / 2;
            if (currentlySelectedTile == WumpusWorld.SPIDER) {
                spriteBatch.draw(myWorld.getSpiderTile(), p.x, p.y);
            }//end inner if
            if(currentlySelectedTile == WumpusWorld.PIT){
                spriteBatch.draw(myWorld.getPitTile(),p.x, p.y);
            }
            if(currentlySelectedTile == WumpusWorld.WUMPUS){
                spriteBatch.draw(myWorld.getWumpusTile(),p.x, p.y);
            }
            if(currentlySelectedTile == WumpusWorld.GOLD){
                spriteBatch.draw(myWorld.getGoldTile(),p.x, p.y);
            }
            //if(currentlySelectedTile == WumpusWorld.GROUND){
            //    spriteBatch.draw(myWorld.getGroundTile(),p.x, p.y);
            //}
        }//end outer if
    }//end method drawTooBar

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }
}
