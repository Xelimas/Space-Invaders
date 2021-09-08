package entites;


import ressources.Constantes;

import java.awt.Color;
import java.awt.Graphics;

public class Chateau extends Entite {

    /**** VARIABLES ****/
    private final int NBRE_LIGNES = Constantes.HAUTEUR_CHATEAU / Constantes.DIMENSION_BRIQUE;
    private final int NBRE_COLONNES = Constantes.LARGEUR_CHATEAU / Constantes.DIMENSION_BRIQUE;

    // tableau contenant les briques du château(la case contient true pour une
    // brique et false pour vide)
    boolean tabChateau[][] = new boolean[NBRE_LIGNES][NBRE_COLONNES];

    /**** CONSTRUCTEUR ****/
    public Chateau(int xPos) {
        super.xPos = xPos; // Abscisse du point le plus à gauche du château
        super.yPos = Constantes.Y_POS_CHATEAU; // Ordonnée du sommet du château

        this.initTabChateau();
    }

    /**** METHODES ****/

    // Création du tableau initial associé au château sans dégat
    // on remplit toutes les cases avec true
    public void initTabChateau() {
        for (int ligne = 0; ligne < NBRE_LIGNES; ligne++) {
            for (int colonne = 0; colonne < NBRE_COLONNES; colonne++) {
                tabChateau[ligne][colonne] = true;
            }
        }
        // On remplit toutes les cases sans brique du tableau avec false
        for (int colonne = 0; colonne < 6; colonne++) {
            for (int ligne = 0; ligne < 2; ligne++) {
                tabChateau[ligne][colonne] = false;
                tabChateau[ligne][NBRE_COLONNES - colonne - 1] = false;
            }
        }
        for (int colonne = 0; colonne < 4; colonne++) {
            for (int ligne = 2; ligne < 4; ligne++) {
                tabChateau[ligne][colonne] = false;
                tabChateau[ligne][NBRE_COLONNES - colonne - 1] = false;
            }
        }
        for (int colonne = 0; colonne < 2; colonne++) {
            for (int ligne = 4; ligne < 6; ligne++) {
                tabChateau[ligne][colonne] = false;
                tabChateau[ligne][NBRE_COLONNES - colonne - 1] = false;
            }
        }
        // Entrée du château
        for (int ligne = 18; ligne < NBRE_LIGNES; ligne++) {
            for (int colonne = 10; colonne < NBRE_COLONNES - 10; colonne++) {
                tabChateau[ligne][colonne] = false;

            }
        }
        // bizeautage entrée du château
        for (int colonne = 12; colonne < NBRE_COLONNES - 12; colonne++) {
            for (int ligne = 16; ligne < 18; ligne++) {
                tabChateau[ligne][colonne] = false;
                tabChateau[ligne][NBRE_COLONNES - colonne - 1] = false;
            }
        }
        for (int colonne = 14; colonne < NBRE_COLONNES - 14; colonne++) {
            for (int ligne = 14; ligne < 16; ligne++) {
                tabChateau[ligne][colonne] = false;
                tabChateau[ligne][NBRE_COLONNES - colonne - 1] = false;
            }
        }
        for (int colonne = 0; colonne < 2; colonne++) {
            for (int ligne = 4; ligne < 6; ligne++) {
                tabChateau[ligne][colonne] = false;
                tabChateau[ligne][NBRE_COLONNES - colonne - 1] = false;
            }
        }
    }

    // Dessin du château
    public void dessinChateau(Graphics g2) {
        for (int ligne = 0; ligne < NBRE_LIGNES; ligne++) {
            for (int colonne = 0; colonne < NBRE_COLONNES; colonne++) {
                if (tabChateau[ligne][colonne] == true) {
                    g2.setColor(Color.GREEN);
                } else {
                    g2.setColor(Color.BLACK);
                }
                g2.fillRect(this.xPos + Constantes.DIMENSION_BRIQUE * colonne,
                        this.yPos + Constantes.DIMENSION_BRIQUE * ligne, Constantes.DIMENSION_BRIQUE,
                        Constantes.DIMENSION_BRIQUE);
            }
        }
    }
}