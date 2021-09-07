package entites;

import java.awt.Graphics;

import ressources.Constantes;
public class GroupeAliens {
    private Alien tabAlien[][] = new Alien[5][10];

    public GroupeAliens() {
        this.initTableauAlien();
    }

    private void initTableauAlien() {
        for (int colonne=0; colonne<10; colonne++) {
            this.tabAlien[0][colonne] = new Alien(Constantes.X_POS_INIT_ALIEN + (
                Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN)*colonne, Constantes.ALT_INIT_ALIEN, "/images/alienHaut1.png", strImg2)
        }
    }
}