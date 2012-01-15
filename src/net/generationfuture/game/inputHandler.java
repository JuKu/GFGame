/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.generationfuture.game;

import org.newdawn.slick.*;

/**
 *
 * @author Martin
 */
public class inputHandler {
    
    public void main() {
        //adf
    }
    
    public void check(GameContainer gc,Player player) {
        if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            player.moveLeft();
        }
        else if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            player.moveRight();
        }
        else if (gc.getInput().isKeyDown(Input.KEY_UP)) {
            player.moveUp();
        }
        else if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            player.moveDown();
        }
    }
}
