package ressources;

import java.awt.event.*;

import jeu.spaceInvadersMain;

public class Clavier implements KeyListener{



    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
        
        
    }


    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        if (e.getKeyCode() == 68) {
            spaceInvadersMain.scene.vaisseau.setDx(Constantes.DX_VAISSEAU);
        }
        else if (e.getKeyCode() == 81) {
            spaceInvadersMain.scene.vaisseau.setDx(-Constantes.DX_VAISSEAU);
        }
        
    }


    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        
        spaceInvadersMain.scene.vaisseau.setDx(0);
    }

    
    
}