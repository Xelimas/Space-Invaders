package ressources;

import java.awt.event.*;

import jeu.spaceInvadersMain;

public class Clavier implements KeyListener {

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {

    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        if (spaceInvadersMain.scene.vaisseau.isVivant() == true) {
            if (e.getKeyCode() == 68) { // touche D (droite)
                spaceInvadersMain.scene.vaisseau.setDx(Constantes.DX_VAISSEAU);
            } else if (e.getKeyCode() == 81) { // touche Q (gauche)
                spaceInvadersMain.scene.vaisseau.setDx(-Constantes.DX_VAISSEAU);
            } else if (e.getKeyCode() == 90) { // touche Z (tir du vaisseau)
                if (spaceInvadersMain.scene.tirVaisseau.isVaisseauTire() == false) {
                    Audio.playSound("/sons/sonTirVaisseau.wav");
                    spaceInvadersMain.scene.tirVaisseau
                            .setyPos(Constantes.Y_POS_VAISSEAU - Constantes.HAUTEUR_TIR_VAISSEAU);
                    spaceInvadersMain.scene.tirVaisseau
                            .setxPos(spaceInvadersMain.scene.vaisseau.getxPos() + Constantes.LARGEUR_VAISSEAU / 2 - 1);
                    spaceInvadersMain.scene.tirVaisseau.setVaisseauTire(true);

                }
            }
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {

        spaceInvadersMain.scene.vaisseau.setDx(0);
    }

}