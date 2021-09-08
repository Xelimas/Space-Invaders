package entites;

import java.awt.Graphics;

import ressources.Chrono;
import ressources.Constantes;

public class GroupeAliens {

    /**** VARIABLES ****/
    private Alien tabAlien[][] = new Alien[5][10];
    private boolean vaADroite, pos1;
    private int vitesse;

    private int[] tabAlienMort = { -1, -1 }; // Emplacement alien mort dans le tableau aliens

    /**** CONSTRUCTEUR ****/
    public GroupeAliens() {
        this.initTableauAlien();
        this.vaADroite = true;
        this.pos1 = true;
        this.vitesse = Constantes.VITESSE_ALIEN;
    }

     /**** METHODES ****/
    private void initTableauAlien() {
        for (int colonne = 0; colonne < 10; colonne++) {
            this.tabAlien[0][colonne] = new Alien(
                    Constantes.X_POS_INIT_ALIEN
                            + (Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN) * colonne,
                    Constantes.ALT_INIT_ALIEN, "/images/alienHaut1.png", "/images/alienHaut2.png");
            for (int ligne = 1; ligne < 3; ligne++) {
                this.tabAlien[ligne][colonne] = new Alien(
                        Constantes.X_POS_INIT_ALIEN
                                + (Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN) * colonne,
                        Constantes.ALT_INIT_ALIEN + Constantes.ECART_LIGNES_ALIEN * ligne, "/images/alienMilieu1.png",
                        "/images/alienMilieu2.png");
            }
            for (int ligne = 3; ligne < 5; ligne++) {
                this.tabAlien[ligne][colonne] = new Alien(
                        Constantes.X_POS_INIT_ALIEN
                                + (Constantes.LARGEUR_ALIEN + Constantes.ECART_COLONNES_ALIEN) * colonne,
                        Constantes.ALT_INIT_ALIEN + Constantes.ECART_LIGNES_ALIEN * ligne, "/images/alienBas1.png",
                        "/images/alienBas2.png");
            }
        }
    }

    // dessin des Aliens contenus dans le tableau d'aliens
    public void dessinAliens(Graphics g) {
        if (Chrono.compteTours % (100 - 10 * this.vitesse) == 0) {
            this.deplacementAliens();
        }
        for (int colonne = 0; colonne < 10; colonne++) {
            for (int ligne = 0; ligne < 5; ligne++) {
                if (this.tabAlien[ligne][colonne] != null) {
                    this.tabAlien[ligne][colonne].choixImage(pos1);
                    g.drawImage(this.tabAlien[ligne][colonne].getImg(), this.tabAlien[ligne][colonne].getxPos(),
                            this.tabAlien[ligne][colonne].getyPos(), null);
                }
            }
        }

    }

    // methode qui détecte le bord gauche
    public boolean toucheBordGauche() {
        boolean reponse = false;
        for (int colonne = 0; colonne < 10; colonne++) {
            for (int ligne = 0; ligne < 5; ligne++) {
                if (this.tabAlien[ligne][colonne] != null) {
                    if (this.tabAlien[ligne][colonne].getxPos() < Constantes.MARGE_FENETRE) {
                        reponse = true;
                        break;
                    }
                }
            }
        }
        return reponse;
    }

    // methode qui détecte le bord droit
    public boolean toucheBordDroit() {
        boolean reponse = false;
        for (int colonne = 0; colonne < 10; colonne++) {
            for (int ligne = 0; ligne < 5; ligne++) {
                if (this.tabAlien[ligne][colonne] != null) {
                    if (this.tabAlien[ligne][colonne].getxPos() > Constantes.LARGEUR_FENETRE - Constantes.LARGEUR_ALIEN
                            - Constantes.DX_ALIEN - Constantes.MARGE_FENETRE) {
                        reponse = true;
                        break;
                    }
                }
            }
        }
        return reponse;
    }

    // méthode qui change le sens de déplacement de l'alien et le descend d'un cran
    public void alienTourneEtDescend() {
        if (this.toucheBordDroit() == true) {
            for (int colonne = 0; colonne < 10; colonne++) {
                for (int ligne = 0; ligne < 5; ligne++) {
                    if (this.tabAlien[ligne][colonne] != null) {
                        this.tabAlien[ligne][colonne]
                                .setyPos(this.tabAlien[ligne][colonne].getyPos() + Constantes.DY_ALIEN);
                    }
                }
            }
            this.vaADroite = false;
            if (this.vitesse < 9) {
                this.vitesse++;
            }
        } else {
            if (this.toucheBordGauche() == true) {
                for (int colonne = 0; colonne < 10; colonne++) {
                    for (int ligne = 0; ligne < 5; ligne++) {
                        if (this.tabAlien[ligne][colonne] != null) {
                            this.tabAlien[ligne][colonne]
                                    .setyPos(this.tabAlien[ligne][colonne].getyPos() + Constantes.DY_ALIEN);
                        }
                    }
                }
                this.vaADroite = true;
                if (this.vitesse < 9) {
                    this.vitesse++;
                }
            }
        }
    }

    public void deplacementAliens() {
        if (this.tabAlienMort[0] != -1) { // élimination de l'alien mort si nécessaire
            elimineAlienMort(tabAlienMort);
            tabAlienMort[0] = -1; // réinitialisation du tableau d'alien mort
        }
        if (this.vaADroite == true) {
            for (int colonne = 0; colonne < 10; colonne++) {
                for (int ligne = 0; ligne < 5; ligne++) {
                    if (this.tabAlien[ligne][colonne] != null) {
                        this.tabAlien[ligne][colonne]
                                .setxPos(this.tabAlien[ligne][colonne].getxPos() + Constantes.DX_ALIEN);
                    }
                }
            }
        } else {
            for (int colonne = 0; colonne < 10; colonne++) {
                for (int ligne = 0; ligne < 5; ligne++) {
                    if (this.tabAlien[ligne][colonne] != null) {
                        this.tabAlien[ligne][colonne]
                                .setxPos(this.tabAlien[ligne][colonne].getxPos() - Constantes.DX_ALIEN);
                    }
                }

            }
        }
        if (this.pos1 == true) {
            this.pos1 = false;
        } else {
            this.pos1 = true;
        }
        this.alienTourneEtDescend();
    }

    // Détection contact du tir du vaisseau avec l'alien
    public void tirVaisseauToucheAlien(TirVaisseau tirVaisseau) {
        for (int colonne = 0; colonne < 10; colonne++) {
            for (int ligne = 0; ligne < 5; ligne++) {
                if (this.tabAlien[ligne][colonne] != null) {
                    if (tirVaisseau.tueAlien(this.tabAlien[ligne][colonne]) == true) {
                        this.tabAlien[ligne][colonne].vivant = false; // on tue l'alien
                        tirVaisseau.yPos = -1; // on fait disparaitre le tir
                        // on enregistre la position de l'alien mort ds le tableau
                        this.tabAlienMort[0] = ligne;
                        this.tabAlienMort[1] = colonne;
                        break;
                    }
                }
            }
        }
    }

    // Méthode qui enlève l'alien mort du tableau (case à null)
    private void elimineAlienMort(int[] tabAlienMort) {
        this.tabAlien[tabAlienMort[0]][tabAlienMort[1]] = null;
    }

}