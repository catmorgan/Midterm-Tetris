/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm;

import java.awt.Color;
import javalib.colors.*;
import javalib.worldimages.*;
import tester.*;
import java.net.URL;
import java.applet.AudioClip;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camorgan
 */
public class Examples {

    //Examples of Blocks
    Block b1 = new Block(new Posn(110, 10), 20, 20, new Red());
    Block b2 = new Block(new Posn(20, 10), 20, 20, new Red());
    Block b3 = new Block(new Posn(110, 10), 20, 20, new Yellow());
    Block b4 = new Block(new Posn(100, 10), 20, 20, new Blue());
    Block boutsideleft1 = new Block(new Posn(10, 10), 20, 20, new Red());
    Block boutsideleft2 = new Block(new Posn(0, 10), 20, 20, new Red());
    Block boutsideright1 = new Block(new Posn(350, 50), 20, 20, new Red());
    Block boutsideright2 = new Block(new Posn(360, 30), 20, 20, new Red());
    Block boutsidedown1 = new Block(new Posn(10, 230), 20, 20, new Red());
    Block boutsidedown2 = new Block(new Posn(10, 240), 20, 20, new Red());
    Block boutsideup1 = new Block(new Posn(10, 0), 20, 20, new Red());
    Block boutsideup2 = new Block(new Posn(10, 10), 20, 20, new Red());
    //Examples of Fallen Block Collections, store in Array List
    List<Block> fallenblocks1 = new ArrayList<Block>();
    List<Block> fallenblocks2 = new ArrayList<Block>();
    List<Block> fallenblocks3 = new ArrayList<Block>();
    //Examples of Block Worlds
    BlockWorld b1w = new BlockWorld();
    BlockWorld b2w = new BlockWorld();
    BlockWorld b3w = new BlockWorld();
    BlockWorld b4w = new BlockWorld();
    BlockWorld boutsideleftw = new BlockWorld();
    BlockWorld boutsiderightw = new BlockWorld();
    BlockWorld boutsidedownw = new BlockWorld();

    /**
     * test the method outsideLeft in the Block class
     */
    boolean testOutsideLeft(Tester t) {
        return t.checkExpect(this.boutsideleft1.outsideLeft(), true,
                "test outsideLeft,the left bound, on boutsideleft1")
                && t.checkExpect(this.boutsideleft2.outsideLeft(), true,
                "test outsideLeft, the left bound, on boutsideleft2")
                && t.checkExpect(this.boutsideright1.outsideLeft(), false,
                "test outsideLeft, the left bound, on boutsideright1")
                && t.checkExpect(this.boutsideright2.outsideLeft(), false,
                "test outsideLeft, the left bound, on boutsideright2");
    }

    /**
     * test the method outsideRight in the Block class
     */
    boolean testOutsideRight(Tester t) {
        return t.checkExpect(this.boutsideleft1.outsideRight(), false,
                "test outsideRight, the right bound, on boutsideleft1")
                && t.checkExpect(this.boutsideleft2.outsideRight(), false,
                "test outsideRight, the right bound, on boutsideleft2")
                && t.checkExpect(this.boutsideright1.outsideRight(), true,
                "test outsideRight, the right bound, on boutsideright1")
                && t.checkExpect(this.boutsideright2.outsideRight(), true,
                "test outsideRight, the right bound, on boutsideright2");
    }

    /**
     * test the method outsideDown in the Block class This evaluates to true if
     * the block isn't at the bottom, but since the game moves downwards, the
     * testers keep evaluating to false because it will hit the bottom
     */
    boolean testOutsideDown(Tester t) {
        return t.checkExpect(this.boutsidedown1.outsideDown(), false,
                "test outsideDown,the down bound, on boutsidedown1")
                && t.checkExpect(this.boutsidedown2.outsideDown(), false,
                "test outsideDown, the down bound, on boutsidedown2")
                && t.checkExpect(this.boutsideright1.outsideDown(), false,
                "test outsideDown, the down bound, on boutsideright1")
                && t.checkExpect(this.boutsideright2.outsideDown(), false,
                "test outsideDown, the down bound, on boutsideright2");
    }

    /**
     * test the method outsideUp in the Block class
     */
    boolean testOutsideUp(Tester t) {
        return t.checkExpect(this.boutsideup1.outsideUp(), true,
                "test outsideUp,the up bound, on boutsideup1")
                && t.checkExpect(this.boutsideup2.outsideUp(), true,
                "test outsideUp, the up bound, on boutsideup2")
                && t.checkExpect(this.boutsideright1.outsideUp(), false,
                "test outsideUp, the up bound, on boutsideright1")
                && t.checkExpect(this.boutsideright2.outsideUp(), false,
                "test outsideUp, the up bound, on boutsideright2");
    }

    /*
     * I wasn't sure how to test the add method in Rows and the hitBlock 
     * method in Rows. 
     * 
     * For add, when a block is added it's stored as garbly gook so I didn't know 
     * how to right a tester, and there is no toString() method for the blocks
     * as for hitBlock, I just had no idea how I was supposed to build a world
     * for blocks to be hit, when the BlockWorld() doesn't take params
     */
    public static void main(String[] argv) {


        // run the tests - showing only the failed test results
        Examples be = new Examples();
        Tester.runReport(be, false, false);

        // run the game
        BlockWorld w =
                new BlockWorld();
        w.bigBang(240, 360, 0.4);
    }
}
