/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm;

import javalib.colors.*;
import java.util.Random;
import javalib.worldimages.*;
import java.awt.event.*;

/**
 *
 * @author camorgan
 */
public class Block {

    int col;    //column number
    int row;    //row number
    Posn pin;   //pin position (col + 1/2 width, row + 1/2 height)
    IColor color;   //color of block
    int size = 20;  //size of block
    public static int SIZE = 20;    //width and height of block
    public static int HALF = (1 / 2) * SIZE;   //half the width/half the height to get pin
    int width = 240;    //width of world
    int height = 320;       //height of world

    Block(Posn pin, int width, int height, IColor color) {
        this.pin = pin;
        this.SIZE = width;
        this.SIZE = height;
        this.color = color;
    }

    /**
     * creates a red block in the world
     *
     * @return a WorldImage of the current block
     */
    WorldImage blockImage() {
        return new OverlayImages(
                new RectangleImage(pin, Block.SIZE, Block.SIZE, new Black()),
                new RectangleImage(pin, Block.SIZE - 4, Block.SIZE - 4, this.color));
    }

    /**
     * I was initially going to use this to create a random new block at the top
     * of the screen but upon further thinking, I wanted each different piece
     * (L, T, Z,S etc) to start at a specific place, each different from the
     * other one so, I wanted the red block to start at the same place each time
     *
     * public Posn randomStart() { int x = new Random().nextInt(230); int y =
     * 10; return new Posn(x, y); }
     *
     *
     *
     * I also originally had a randomInt() method and a first block method
     * because i was thinking that for the first block, I would make a random
     * Posn and place it at the top of the world, but I decided to have each
     * different kind of block (S,Z, L, T, etc) start in their own special
     * location so the square block starts in the middle, the l on the left of
     * something like that
     *
     * /
     *
     * /**
     * moves the block in a one block direction according to the user input
     *
     * @param ke takes in WASD or the arrow keys for down, left, right movement
     * there is no up because the blocks can't move up
     *
     * @return a new block at the position
     *
     */
    public void moveBlock(String ke) {
        if ((ke.equals("right") || ke.equals("d")) && !outsideRight()) {
            this.pin.x += 20;
        } else if ((ke.equals("left") || ke.equals("a")) && !outsideLeft()) {
            this.pin.x -= 20;
        } else if ((ke.equals("down") || ke.equals("s"))
                && !outsideDown()) {
            this.pin.y += 20;
        } else {
        }
    }

    /**
     * Professor Marc Smith used this method in his game, and in the end I
     * decided to use my own pin referencing method for getting Posns
     *
     * @return
     */
//    private Posn getPosn() {
//        int pinholeX = ((this.col * Block.SIZE) + Block.HALF);
//        int pinholeY = ((this.row * Block.SIZE) + Block.HALF);
//        return new Posn(pinholeX, pinholeY);
//    }
    /**
     * boolean check if the block is at the left bound of the world
     *
     * @return a boolean, true if the block is at the left, false otherwise
     */
    public boolean outsideLeft() {
        return pin.x - 20 <= 0;
    }

    /**
     * boolean check if the block is at the right bound of the world
     *
     * @return a boolean, true if the block is at the right, false otherwise
     */
    public boolean outsideRight() {
        return pin.x + 20 >= width;
    }

    /**
     * boolean check if the block has reached the bottom of the world
     *
     * @return a boolean, true if the block is not at the bottom, false
     * otherwise
     */
    public boolean outsideDown() {
        boolean retval = pin.y + 20 > height;
        if (retval == true) {
            //      System.out.print("outsidebound");
        }
        return retval;
    }

    /**
     * boolean check if the block have reached the top of the world
     *
     * @return a boolean, true if the block is at the top false otherwise
     */
    public boolean outsideUp() {
        return pin.y - 20 < 0;
    }
}
