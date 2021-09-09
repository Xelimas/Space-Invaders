package entites;

import java.awt.Graphics;
import java.util.Random;

import jeu.spaceInvadersMain;
import ressources.Audio;
import ressources.Chrono;
import ressources.Constantes;

public class GroupeAliens {

    /**** VARIABLES ****/
    private Alien tabAlien[][] = new Alien[5][10];
    private boolean vaADroite, pos1;
    private int vitesse;
    private int compteurSonAlien = 0;

    private int[] tabAlienMort = { -1, -1 }; // Emplacement alien mort dans le tableau aliens

    Random hasard = new Random();

    private int nombreAliens = Constantes.NOMBRE_ALIENS;

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
        this.jouSonAlien();
        this.compteurSonAlien++;
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
                        if (ligne == 0) {
                            spaceInvadersMain.scene.score = spaceInvadersMain.scene.score
                                    + Constantes.VALEUR_ALIEN_HAUT;
                        } else if (ligne > 0 && ligne < 3) {
                            spaceInvadersMain.scene.score = spaceInvadersMain.scene.score
                                    + Constantes.VALEUR_ALIEN_MILIEU;
                        } else {
                            spaceInvadersMain.scene.score = spaceInvadersMain.scene.score + Constantes.VALEUR_ALIEN_BAS;
                        }
                        break;
                    }
                }
            }
        }
    }

    // Méthode qui enlève l'alien mort du tableau (case à null)
    private void elimineAlienMort(int[] tabAlienMort) {
        this.tabAlien[tabAlienMort[0]][tabAlienMort[1]] = null;
        this.nombreAliens--;
    }

    // Renvoie la position d'un alien tiré au hasard dans le tableau en bas de sa
    // colonne (ligne, colonne)
    public int[] choixAlienQuiTire() {
        int positionAlien[] = { -1, -1 };
        if (this.nombreAliens != 0) { // on vérifie qu'il reste des aliens vivants
            do {
                int colonne = hasard.nextInt(10); // On tire au hasard des aliens vivants
                // tableau des aliens
                for (int ligne = 4; ligne > 0; ligne--) {
                    // En partant du bas
                    if (tabAlien[ligne][colonne] != null) {
                        positionAlien[0] = this.tabAlien[ligne][colonne].getxPos();
                        positionAlien[1] = this.tabAlien[ligne][colonne].getyPos();
                        break;
                    }
                }
            } while (positionAlien[0] == -1);
        }
        return positionAlien;
    }

    private void jouSonAlien() {
        int compteur = this.compteurSonAlien % 4;
        if (compteur == 0) {
            Audio.playSound("/sons/sonAlien1.wav");
        } else if (compteur == 1) {
            Audio.playSound("/sons/sonAlien2.wav");
        } else if (compteur == 2) {
            Audio.playSound("/sons/sonAlien3.wav");
        } else {
            Audio.playSound("/sons/sonAlien4.wav");
        }
    }

}