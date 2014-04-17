/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm;

import java.applet.AudioClip;
import javalib.colors.*;
import java.util.Random;
import javalib.worldimages.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author catherinemorgan
 */
public class Rows {

    /**
     * plays the soundclip for when a row is destroyed
     */
    private URL deleteurl = getClass().getResource("/explosion.wav");
    private AudioClip deleteclip = java.applet.Applet.newAudioClip(deleteurl);
    //use THIS when referring to these variables
    List<Block> fallenBlocks;
    Block b;
    int width;
    int height;
    int score = 0;

    public Rows() {
        fallenBlocks = new ArrayList<Block>();
        width = 240;
        height = 320;
    }

    /**
     * adds a fallen block to the collection of already fallen blocks This
     * originally was void, but when I added the delete method and was having
     * trouble with that, I decided to check if add was done, if so, return true
     * so then when that method was done running I could run delete
     *
     * @param b the current block that is falling
     * @return returns true when the method is done running
     *
     */
    public boolean add(Block b) {
//        System.out.println(fallenBlocks.toString());
        this.fallenBlocks.add(b);
        score += 1;
        return true;
    }

    /**
     * delete a row from the world
     *
     * @param c the currently block that is falling
     *
     * Originally for this function, I had two for:each loops, in the place of
     * the now two for loops. I had run into an error called
     * ConcurrentModification exception. I had coach Lily help me, and we think
     * that the delete method which used for:each was trying to run parallel as
     * the add method, which modified the fallenBlocks array. So, Lily helped me
     * and taught me about using the for loops instead, and instead of removing
     * block objects, to remove the indexing of the block
     */
    public void delete(Block c) {
        List<Integer> delete = new ArrayList<Integer>();
        for (int j = 0; j < fallenBlocks.size(); j++) {
            Block block = fallenBlocks.get(j);
            int row_c = c.pin.y;
            int row_b = block.pin.y;
            int i = 0;
            if (row_c == row_b) {
                delete.add(j);
                //              System.out.println("delete size" + delete.size());
            }
        }
        if (delete.size() == width / 20) {
            int size = delete.size();
            for (int x = size - 1; x >= 0; x--) {
                fallenBlocks.remove((int) delete.get(x));
                //               System.out.println("delete size: " + delete.size()
                //                       + ". fallen size: " + fallenBlocks.size());
            }
            moveDown();
        }
    }

    /**
     * after deleting a row move the blocks still on the board down one row
     *
     */
    public void moveDown() {
        for (Block block : fallenBlocks) {
            block.pin.y += 20;
        }
        score += 10;
        //          System.out.println("score:" + score);
        deleteclip.play();
    }

    /**
     * Returns a WorldImage of all the fallen blocks
     *
     * @param background the background canvas that the blocks are drawn on
     * @return the updated world with the background and blocks
     */
    public WorldImage drawWorld(WorldImage background) {
        for (Block block : fallenBlocks) {
            background = new OverlayImages(
                    background,
                    block.blockImage());
        }
        // System.out.println("background: " + background);
        return background;
    }

    /**
     * checks if an active falling block is going to hit a fallen block
     *
     * @param c the current falling block
     * @return a boolean value, true if the block hit another block or false
     * otherwise
     */
    public boolean hitBlock(Block c) {
        boolean answer = false;
        for (Block block : fallenBlocks) {
            int col_c = c.pin.x;
            int row_c = c.pin.y;
            int col_b = block.pin.x;
            int row_b = block.pin.y;
            answer = answer
                    || (((col_c >= col_b) && (col_c < col_b + 20))
                    && ((row_c + 22) > row_b));
            //  System.out.println(col_c + ", " + col_b + ", "
            //+ row_c + ", " + row_b + ", " + answer);
        }
        return answer;

    }
}
