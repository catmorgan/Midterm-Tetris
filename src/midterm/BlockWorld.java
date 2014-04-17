/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm;

import java.applet.AudioClip;
import java.awt.Color;
import javalib.colors.*;
import javalib.impworld.*;
import javalib.worldimages.*;
import java.awt.event.*;
import java.util.List;
import java.util.Timer;
import java.net.URL;

/**
 *
 * @author camorgan
 */
public class BlockWorld extends World {

    int width = 240;
    int height = 320;
    Block block;
    int DELAY = 1000;
    Rows fallen = new Rows();
    /*
     * I had an over world theme to play during the game, but I couldn't figure 
     * out a way to implement it, since there is no init() run method, and the
     * world is continously redrawn, so it looped about every 5 seconds 
     * 
     * private URL themeurl = getClass().getResource("/TetrisTheme.wav");
     * private AudioClip themeclip = java.applet.Applet.newAudioClip(themeurl);
     * themeclip.play();     */
    /*
     * ending theme when the game is lost
     */
    private URL endurl = getClass().getResource("/endworld.wav");
    private AudioClip endclip = java.applet.Applet.newAudioClip(endurl);

    public BlockWorld() {
        super();
        this.block = new Block(new Posn(110, 10), 20, 20, new Red());
        this.fallen = new Rows();
    }

    /**
     * creates the background rectangle
     *
     * @return a WorldImage of a white rectangle for the background
     */
    public WorldImage background() {
        return new OverlayImages(
                new OverlayImages(
                new RectangleImage(new Posn(120, 160), width, height, new Black()),
                new RectangleImage(new Posn(120, 160), width - 4, height - 4, new White())),
                score());
    }

    /*
     * makes the score text image 
     * +1 for each block fallen, +10 for each row destroyed
     */
    public WorldImage score() {
        return new TextImage(new Posn(150, 340), ("SCORE: " + fallen.score), Color.black);
    }

    /**
     * draws the blocks on the background
     *
     * @return a WorldImage of the world
     */
    public WorldImage makeImage() {
        return new OverlayImages(
                fallen.drawWorld(background()),
                this.block.blockImage());
    }

//    public WorldImage newBlock() {
//        if (this.block.outsideDown()) {
//            return block.firstBlock();
//        } else {
//            return this.block.blockImage();
//        }
//    }
    /**
     * Can end the world if the user inputs "x" or, if the block isn't at the
     * bottom it will move the block according to input, or create a new block
     *
     * @param ke takes in user input for direction, x will end the world
     */
    public void onKeyEvent(String ke) {
        if (ke.equals("x")) {
            this.endOfWorld("Thanks for Playing!");
        } else if (!this.block.outsideDown() && !fallen.hitBlock(this.block)) {
            this.block.moveBlock(ke);
        } else {
            makeImage();
        }
    }

    /**
     * if the block isn't at the bottom and hasn't hit another block, keep
     * moving that block down If it has hit the bottom or another block, add
     * that block to the collection of fallen blocks, and make a new block at
     * the top of the world
     *
     * once you add the block to the collection of fallen blocks, check to see
     * if a completed row has been finished, if so, delete the row
     */
    public void onTick() {
        boolean finished = false;
        //System.out.println("ontick");
        if (!this.block.outsideDown()
                && !fallen.hitBlock(block)) {
            this.block.moveBlock("s");

        } else {
            finished = fallen.add(this.block);
            //          System.out.println("fallen size = " + fallen.fallenBlocks.size());
            //     System.out.println(block.pin.y);
            if (finished) {
                fallen.delete(this.block);
            }
            this.block = new Block(new Posn(110, 10), 20, 20, new Red());
        }
    }

    /**
     * if the blocks hit the canvas at the top, then end the world otherwise
     * continue the world
     *
     * @return the world status (whether dead or alive)
     */
    public WorldEnd worldEnds() {
        WorldEnd world = new WorldEnd(false, this.makeImage());
        for (Block blocks : fallen.fallenBlocks) {
            if (blocks.outsideUp()) {
                world = new WorldEnd(true,
                        new OverlayImages(this.makeImage(),
                        //background(),
                        new TextImage(new Posn(125, 100),
                        "YOU LOSE",
                        30,
                        1,
                        Color.black)));
                endclip.play();
            } else {
                world = new WorldEnd(false, this.makeImage());
            }
        }
        return world;
    }
    public static Examples examplesInstance = new Examples();
}
