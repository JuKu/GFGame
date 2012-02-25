package net.generationfuture.game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.ObjectMenu;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

public class GameMouseListener implements MouseListener {

    GFGame gfgame;
    Player player;
    
    ObjectMenu objectmenu;
        
        public GameMouseListener (GFGame gfgame, Player player) {
            this.gfgame = gfgame;
        }

        @Override
        public void mouseWheelMoved(int i) {
            gfgame.zoom = gfgame.zoom + i;//throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseClicked(int i, int i1, int i2, int i3) {
            
            if (gfgame.GameStart == false && !gfgame.init) {
                gfgame.GameStart = true;
            }
            
            if (gfgame.isInput) {
            
            int mouse_x = i1;
            int mouse_y = i2;//System.out.println("i: " + i + ", i1: " + i1 + ", i2: " + i2 + ", i3: " + i3);
            
            objectmenu = gfgame.object_manager.getObjectMenu();
            
            Boolean isClicked_ = false;
            
            GameMenuItem menuItem = gfgame.game_menu.mouseClicked(mouse_x, mouse_y);
            
            if (menuItem != null) {
                isClicked_ = true;
                
            try {
                //System.out.println("Clicked.");
                gfgame.actionPerformed(menuItem.getActionCommand(), menuItem);
            } catch (IOException ex) {
                Logger.getLogger(GameMouseListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            }
            
            if (!isClicked_) {
                isClicked_ = gfgame.questmanager.mouseClicked(mouse_x, mouse_y, isClicked_);//System.out.println("test2");
            }
            
            if (!isClicked_) {
                
                if (objectmenu != null) {
                    isClicked_ = objectmenu.isClicked(mouse_x, mouse_y);
                }
                
            }
            
            //Teste, ob Object angeklickt wurde.
            gfgame.object_manager.isClicked(mouse_x, mouse_y, isClicked_);
            
            } else {
                //Es werden keine Inputs angenommen.
            }
            
        }

        @Override
        public void mousePressed(int i, int i1, int i2) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseReleased(int i, int i1, int i2) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseMoved(int i, int i1, int i2, int i3) {
            int mouse_x = i;
            int mouse_y = i1;
            
            objectmenu = gfgame.object_manager.getObjectMenu();
            
            GameMenuItem menuItem = gfgame.game_menu.mouseOver(mouse_x, mouse_y);
            Boolean isMouseMoved = false;
            
            if (menuItem != null) {
                isMouseMoved = true;
                menuItem.setMouseOver(true);
            }
            
            if (!isMouseMoved) {
                
                if (objectmenu != null) {
                    isMouseMoved = objectmenu.mouseMoved(mouse_x, mouse_y);
                }
                
            }
            
            isMouseMoved = gfgame.questmanager.mouseOver(mouse_x, mouse_y, isMouseMoved);
            
            //Testen, ob Maus über Animals gefahren wurde.
            isMouseMoved = gfgame.animal_manager.isMouseOver(mouse_x, mouse_y, isMouseMoved);
             //Teste, ob Maus über Object "gefahren" wurde.
            isMouseMoved = gfgame.object_manager.isMouseOver(mouse_x, mouse_y, isMouseMoved);
            
        }

        @Override
        public void mouseDragged(int i, int i1, int i2, int i3) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setInput(Input input) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isAcceptingInput() {
            //throw new UnsupportedOperationException("Not supported yet.");
            return true;
        }

        @Override
        public void inputEnded() {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void inputStarted() {
            //throw new UnsupportedOperationException("Not supported yet.");
        }
        //
    
}
