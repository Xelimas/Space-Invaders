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

    // Trouve la colonne du tableau associé au château touché par le tir
    public int trouveColonneChateau(int xMissile) {
        int colonne = -1;
        colonne = (xMissile - this.xPos) / Constantes.DIMENSION_BRIQUE;
        return colonne;
    }

    // trouve la première brique en partant du bas de la colonne du tableau au
    // château ou renvoie -1
    public int trouveBrique(int colonne) {
        int ligne = NBRE_LIGNES - 1;
        while (ligne >= 0 && tabChateau[ligne][colonne] == false) {
            ligne--;
        }
        return ligne;
    }

    // elimination des 6 premières briques de la colonnes en partant du bas
    // si elle existent
    private void enleveBriques(int ligne, int colonne) {
        for (int compteur = 0; compteur < 6; compteur++) {
            if (ligne - compteur >= 0) {
                tabChateau[ligne - compteur][colonne] = false;
                if (colonne < NBRE_COLONNES - 1) {
                    tabChateau[ligne - compteur][colonne + 1] = false;
                }
            }
        }

    }

    // récapitule les 3 méthodes qui précédent
    public void casseBriques(int xTir) {
        int colonne = this.trouveColonneChateau(xTir);
        this.enleveBriques(trouveBrique(colonne), colonne);
    }

}